package com.love.step.controller;

import com.love.step.constant.RedisConstant;
import com.love.step.dto.UserDTO;
import com.love.step.service.UserService;
import com.love.step.utils.RedisUtils;
import com.love.step.utils.ResultUtil;
import com.love.step.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/list")
    public Object getUserList(){
        return userService.getUserList();
    }


    @GetMapping("/info")
    public Object getUserInfo(@RequestParam("openid")String openid){
        return userService.getUserInfoByOpenid(openid);
    }

    @PostMapping("/update")
    public ResultVO updateUser(@RequestParam(value = "isAuth",required = false)Integer isAuth,
                                @RequestParam(value = "isRun",required = false) Integer isRun,
                                @RequestParam("token") String token,
                                @RequestParam(value = "username",required = false) String username,
                                @RequestParam(value = "photo",required = false)String photo){
        String openid = redisUtils.get(String.format(RedisConstant.TOKEN_PREFIX,token));
        UserDTO userDTO = new UserDTO();
        userDTO.setOpenid(openid);
        userDTO.setIsAuth(isAuth);
        userDTO.setIsRun(isRun);
        userDTO.setPhoto(photo);
        userDTO.setUsername(username);
        System.out.println(openid);
        if(userService.updateUserInfo(userDTO)){
            return ResultUtil.success();
        }
        return ResultUtil.error();
    }

}
