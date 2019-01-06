package com.love.step.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class UserDTO implements Serializable {

    private Integer id;

    private String username;

    private String openid;

    private Integer isAuth;

    private Integer isRun;

    private String photo;

}
