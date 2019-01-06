package com.love.step.service.impl;

import com.love.step.dto.UserDTO;
import com.love.step.mapper.UserMapper;
import com.love.step.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String,Object> addUserInfo(UserDTO userDTO) {
        Map<String,Object> resultMap = null;
        if(null != userDTO && !StringUtils.isEmpty(userDTO.getOpenid())){
            UserDTO openIdUser = userMapper.getUserByOpenId(userDTO.getOpenid());
            if(null != openIdUser){
                resultMap = new HashMap<>();
                resultMap.put("isAuth",openIdUser.getIsAuth());
                resultMap.put("isRun",openIdUser.getIsRun());
                return resultMap;
            }
            if(userMapper.insertUserInfo(userDTO) >= 1){
                resultMap = new HashMap<>();
                resultMap.put("isAuth",0);
                resultMap.put("isRun",0);
                return resultMap;
            }
        }
        return resultMap;

    }

    @Override
    public boolean updateUserInfo(UserDTO userDTO) {
        return userMapper.updateUserInfo(userDTO) > 0;
    }
}
