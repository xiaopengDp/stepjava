package com.love.step.mapper;

import com.love.step.dto.UserDTO;
import com.love.step.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserMapperTest {

    @Autowired
    private UserService userService;


   /* @Test
    public void getUserByOpenId() {
        UserDTO result = userMapper.getUserByOpenId("test");
        log.info("result==",result);
    }*/

    @Test
    public void getUserList(){
        List<UserDTO> userDTOS = userService.getUserList();
        log.info("result=",userDTOS);
    }
}