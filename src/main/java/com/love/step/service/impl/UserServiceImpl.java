package com.love.step.service.impl;

import com.love.step.dto.AuthDTO;
import com.love.step.dto.UserDTO;
import com.love.step.mapper.AuthMapper;
import com.love.step.mapper.UserMapper;
import com.love.step.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthMapper authMapper;

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
                resultMap.put("isAuthUser",openIdUser.getIsAuthUser());
                return resultMap;
            }
            if(userMapper.insertUserInfo(userDTO) >= 1){
                resultMap = new HashMap<>();
                resultMap.put("isAuth",0);
                resultMap.put("isRun",0);
                resultMap.put("isAuthUser",0);
                return resultMap;
            }
        }
        return resultMap;

    }

    @Override
    public boolean updateUserInfo(UserDTO userDTO) {
        return userMapper.updateUserInfo(userDTO) > 0;
    }

    @Override
    @Transactional
    public boolean addAuth(String openid,String name,String mobile) {
        UserDTO userDTO = userMapper.getUserByOpenId(openid);
        if(null != userDTO){
            if(userDTO.getIsAuthUser() == 0) {
                AuthDTO authDTO = new AuthDTO();
                authDTO.setMobile(mobile);
                authDTO.setName(name);
                authDTO.setUserId(userDTO.getId());
                authDTO.setUserOpenid(openid);
                if (authMapper.addAuth(authDTO) < 0) {
                    return false;
                }
                userDTO.setIsAuthUser(1);
                if(userMapper.updateUserInfo(userDTO) < 0){
                    return false;
                }
            }
        }
        return true;
    }
}
