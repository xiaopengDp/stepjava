package com.love.step.controller;

import com.alibaba.fastjson.JSON;
import com.love.step.config.RunConfig;
import com.love.step.utils.ResultUtil;
import com.love.step.vo.ResultVO;
import com.love.step.vo.RunVo;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private RunConfig runConfig;

    @PostMapping("/login")
    public ResultVO login(@RequestParam("code")String code){

        CloseableHttpClient httpCilent = HttpClients.createDefault();//Creates CloseableHttpClient instance with default configuration.
        HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/sns/jscode2session?appid="+runConfig.getAppid()+"&secret="+runConfig.getAppsecret()+"&js_code="+code+"&grant_type=authorization_code");
        try {
            String srtResult = "";
            HttpResponse httpResponse = httpCilent.execute(httpGet);
            if(httpResponse.getStatusLine().getStatusCode() == 200){
                srtResult = EntityUtils.toString(httpResponse.getEntity());//获得返回的结果

                RunVo runVo = JSON.parseObject(srtResult,RunVo.class);

                System.out.println(JSON.toJSON(runVo).toString());

                //sessionkey = runVo.getSession_key();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                httpCilent.close();//释放资源
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return ResultUtil.success();
    }
}
