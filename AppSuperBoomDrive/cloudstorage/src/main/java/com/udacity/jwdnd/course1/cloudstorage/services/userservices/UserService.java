/**
 * 
 */
package com.udacity.jwdnd.course1.cloudstorage.services.userservices;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.udacity.jwdnd.course1.cloudstorage.models.dtos.UserInfoDto;

/**
 * @author utkarsh
 *
 */
public interface UserService extends UserDetailsService{
	
	UserInfoDto findByUsername(String username);
	
	Integer save(UserInfoDto userInfoDto);
}
