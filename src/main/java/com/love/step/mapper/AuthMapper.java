package com.love.step.mapper;

import com.love.step.dto.AuthDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuthMapper {

    @Insert("insert into pr_auth(user_id,user_openid,name,mobile) value(#{auth.userId},#{auth.userOpenid},#{auth.name},#{auth.mobile})")
    public int addAuth(@Param("auth")AuthDTO auth);
}
