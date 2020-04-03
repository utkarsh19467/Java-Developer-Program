package com.udacity.jwdnd.course1.cloudstorage.services.modelconvertors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.udacity.jwdnd.course1.cloudstorage.models.dtos.UserInfoDto;
import com.udacity.jwdnd.course1.cloudstorage.models.entities.UserInfo;
import com.udacity.jwdnd.course1.cloudstorage.services.encryptionservices.HashService;

/**
 * The Class UserConvertor.
 */
@Component
public class UserConvertor {
	
	@Autowired
	private HashService hashService;
	
	public UserInfo userInfoDtoToUserInfo(UserInfoDto userInfoDto) {
		UserInfo userInfo = new UserInfo();
		userInfo.setFirstName(userInfoDto.getFirstName());
		userInfo.setLastName(userInfoDto.getLastName());
		userInfo.setUsername(userInfoDto.getUsername());
		userInfo.setSalt("SecretPassword");
		userInfo.setPassword(hashService.getHashedValue(userInfoDto.getPassword(), userInfo.getSalt()));
		return userInfo;
	}
	
	public UserInfoDto userInfoToUserInfoDto(UserInfo userInfo) {
		UserInfoDto userInfoDto = new UserInfoDto();
		userInfoDto.setFirstName(userInfo.getFirstName());
		userInfoDto.setLastName(userInfo.getLastName());
		userInfoDto.setUsername(userInfo.getUsername());
		userInfoDto.setPassword(userInfo.getPassword());
		return userInfoDto;
	}
}
