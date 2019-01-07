package com.love.step.dto;

import lombok.Data;

@Data
public class AuthDTO {

    private Integer id;

    private Integer userId;

    private String userOpenid;

    private String name;

    private String mobile;
}
