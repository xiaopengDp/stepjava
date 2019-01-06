package com.love.step.controller;

import com.alibaba.fastjson.JSON;
import com.love.step.config.RunConfig;
import com.love.step.constant.RedisConstant;
import com.love.step.dto.UserDTO;
import com.love.step.service.UserService;
import com.love.step.utils.RedisUtils;
import com.love.step.utils.ResultUtil;
import com.love.step.vo.ResultVO;
import com.love.step.vo.RunVo;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private RunConfig runConfig;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResultVO login(@RequestParam("code") String code){

        Map<String,Object> resultMap = new HashMap<>();

        // 通过code 获取sessionkey  和 openid
        CloseableHttpClient httpCilent = HttpClients.createDefault();//Creates CloseableHttpClient instance with default configuration.
        HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/sns/jscode2session?appid="+runConfig.getAppid()+"&secret="+runConfig.getAppsecret()+"&js_code="+code+"&grant_type=authorization_code");
        try {
            String srtResult = "";
            HttpResponse httpResponse = httpCilent.execute(httpGet);
            if(httpResponse.getStatusLine().getStatusCode() == 200){
                srtResult = EntityUtils.toString(httpResponse.getEntity());//获得返回的结果
                //System.out.println(srtResult);
                RunVo runVo = JSON.parseObject(srtResult,RunVo.class);
                System.out.println(JSON.toJSON(runVo).toString());
                if(null != runVo && StringUtils.isNotBlank(runVo.getSession_key()) && StringUtils.isNotBlank(runVo.getOpenid())){
                    UserDTO userDTO = new UserDTO();
                    userDTO.setOpenid(runVo.getOpenid());
                    // 保存到数据库
                    resultMap = userService.addUserInfo(userDTO);
                    if(null != resultMap){
                        // 生成Token
                        String token = UUID.randomUUID().toString();
                        resultMap.put("token",token);
                        // 保存到redis中 openid sessionkey
                        redisUtils.set(String.format(RedisConstant.TOKEN_PREFIX,token),runVo.getOpenid());
                        redisUtils.set(String.format(RedisConstant.SESSION_KEY_PREFIX,token),runVo.getSession_key());
                        System.out.println(redisUtils.get(String.format(RedisConstant.TOKEN_PREFIX,token)));

                    }else{
                        return ResultUtil.error();
                    }
                }
            }else{
                return ResultUtil.error();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error();
        }finally {
            try {
                httpCilent.close();//释放资源
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResultUtil.success(resultMap);
    }
}
