package com.love.step.service;

import com.love.step.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getUserList();
    UserDTO getUserInfoByOpenid(String openid);
}
