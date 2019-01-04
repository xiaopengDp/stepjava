package com.love.step.constant;

public interface RedisConstant {

    String TOKEN_PREFIX = "token_%s";

    /** 时效性 2个小时 **/
    Integer EXPIRE = 7200;

    String SESSION_KEY_PREFIX = "session_%s";

    String OPENID_PREFIX = "openid_%s";


}
