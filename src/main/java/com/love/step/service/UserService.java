package com.love.step.service;

import com.love.step.dto.UserDTO;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<UserDTO> getUserList();
    UserDTO getUserInfoByOpenid(String openid);
    Map<String,Object> addUserInfo(UserDTO userDTO);
    boolean updateUserInfo(UserDTO userDTO);
    boolean addAuth(String openid,String name,String mobile);
}
