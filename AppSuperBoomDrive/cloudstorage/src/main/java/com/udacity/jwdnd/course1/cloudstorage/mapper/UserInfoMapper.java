package com.udacity.jwdnd.course1.cloudstorage.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.udacity.jwdnd.course1.cloudstorage.models.entities.UserInfo;

@Mapper
public interface UserInfoMapper {

	@Insert("INSERT INTO USERS(firstname, lastname, username, password) " +
	        " VALUES (#{firstName}, #{lastName}, #{username}, #{password})")
	public int save(UserInfo userInfo);
	
	@Select("SELECT * FROM USERS WHERE username = #{username}")
    public UserInfo findByUsername(String username);

}
