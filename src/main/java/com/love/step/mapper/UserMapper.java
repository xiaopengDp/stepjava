package com.love.step.mapper;

import com.love.step.dto.UserDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT id,username,openid,is_auth as isAuth,photo,is_run as isRun,is_auth_user as isAuthUser FROM pe_user WHERE openid = #{openid}")
    public UserDTO getUserByOpenId(@Param("openid")String openid);

    @Insert("Insert into pe_user(openid,is_auth,is_run,is_auth_user) value(#{user.openid},0,0,0)")
    public int insertUserInfo(@Param("user")UserDTO userDTO);

    public List<UserDTO> getUserList();

    public int updateUserInfo(@Param("user")UserDTO userDTO);
}
