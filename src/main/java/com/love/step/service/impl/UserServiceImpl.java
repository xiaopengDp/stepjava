package com.love.step.service.impl;

import com.love.step.dto.UserDTO;
import com.love.step.mapper.UserMapper;
import com.love.step.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDTO> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public UserDTO getUserInfoByOpenid(String openid) {
        return userMapper.getUserByOpenId(openid);
    }
}
