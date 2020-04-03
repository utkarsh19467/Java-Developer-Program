/**
 * 
 */
package com.udacity.jwdnd.course1.cloudstorage.services.userservices;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserInfoMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.dtos.UserInfoDto;
import com.udacity.jwdnd.course1.cloudstorage.models.entities.UserInfo;
import com.udacity.jwdnd.course1.cloudstorage.services.modelconvertors.UserConvertor;

/**
 * @author utkarsh
 *
 */
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private UserConvertor userConvertor;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserInfo userInfo = userInfoMapper.findByUsername(username);
		
		if (userInfo == null) {
			throw new UsernameNotFoundException("Inavlid Username or passwordI");
		}
		return new User(userInfo.getUsername(), userInfo.getPassword(), 
				new ArrayList<>());
	}

	@Override
	public UserInfoDto findByUsername(String username) {
		UserInfo userInfo = userInfoMapper.findByUsername(username);
		UserInfoDto userInfoDto = null;
		if(userInfo != null) {
			userInfoDto = userConvertor.userInfoToUserInfoDto(userInfo);
		}
		return userInfoDto;
	}
	
	@Override
	public Integer save(UserInfoDto userInfoDto) {
		return userInfoMapper.save(userConvertor.userInfoDtoToUserInfo(userInfoDto));
	}
	
//	private Collection < ? extends GrantedAuthority > mapRolesToAuthorities(Collection < Role > roles) {
//        return roles.stream()
//            .map(role -> new SimpleGrantedAuthority(role.getName()))
//            .collect(Collectors.toList());
//    }
}
