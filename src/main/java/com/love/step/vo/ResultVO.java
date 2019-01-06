package com.love.step.vo;

import lombok.Data;

@Data
public class ResultVO<T> {


    /** 返回值的CODE **/
    private Integer code;
    /** 返回值的消息 **/
    private String msg;
    /** 返回值的主体 **/
    private T data;
}
