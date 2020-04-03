/**
 * 
 */
package com.udacity.jwdnd.course1.cloudstorage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.AllDetailsMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.entities.UserInfo;
import com.udacity.jwdnd.course1.cloudstorage.services.userservices.AuthenticatedUserUtility;

/**
 * @author utkarsh
 *
 */
@Service
public class HomeDetailService {

	@Autowired
	private AllDetailsMapper allDetailsMapper;

	@Autowired
	private AuthenticatedUserUtility userUtility;

	public UserInfo getAllUserInfo() {
		UserInfo userInfo = userUtility.getAuthenticatedUser();
		if(userInfo != null) {
			return allDetailsMapper
					.findByUsernameWithAllDetails(userUtility.getAuthenticatedUser().getUsername());
		}
		return new UserInfo();
	}
}
