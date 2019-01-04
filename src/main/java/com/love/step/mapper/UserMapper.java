package com.love.step.mapper;

import com.love.step.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT id,username,openid,is_auth as isAuth FROM pe_user WHERE openid = #{openid}")
    public UserDTO getUserByOpenId(@Param("openid")String openid);

    public List<UserDTO> getUserList();
}
