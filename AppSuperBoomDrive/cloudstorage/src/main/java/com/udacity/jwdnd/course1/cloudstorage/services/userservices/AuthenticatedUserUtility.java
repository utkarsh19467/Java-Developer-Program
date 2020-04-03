/**
 * 
 */
package com.udacity.jwdnd.course1.cloudstorage.services.userservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserInfoMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.entities.UserInfo;

/**
 * @author utkarsh
 *
 */
@Component
public class AuthenticatedUserUtility {
	
	@Autowired 
	private UserInfoMapper userInfoMapper;
	
	public UserInfo getAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return userInfoMapper.findByUsername(authentication.getName());
	}
	
}
