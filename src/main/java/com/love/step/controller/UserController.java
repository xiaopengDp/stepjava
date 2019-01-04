package com.love.step.controller;

import com.love.step.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Object getUserList(){
        return userService.getUserList();
    }


    @GetMapping("/info")
    public Object getUserInfo(@RequestParam("openid")String openid){
        return userService.getUserInfoByOpenid(openid);
    }
}
