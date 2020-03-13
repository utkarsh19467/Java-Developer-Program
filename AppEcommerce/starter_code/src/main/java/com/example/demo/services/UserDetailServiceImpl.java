/**
 * 
 */
package com.example.demo.services;

import static java.util.Collections.emptyList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.persistence.EcommerceUser;
import com.example.demo.model.persistence.repositories.UserRepository;

/**
 * The Class UserDetailServiceImpl.
 *
 * @author utkarsh
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

	/** The user repo. */
	@Autowired
	private UserRepository userRepo;

	/**
	 * Load user by username.
	 *
	 * @param username the username
	 * @return the user details
	 * @throws UsernameNotFoundException the username not found exception
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		EcommerceUser ecommerceUser = userRepo.findByUsername(username);
		if (ecommerceUser == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(ecommerceUser.getUsername(), ecommerceUser.getPassword(), emptyList());
	}
}
