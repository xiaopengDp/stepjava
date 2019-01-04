package com.love.step.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class UserDTO implements Serializable {

    private int id;

    private String username;

    private String openid;

    private int isAuth;

}
