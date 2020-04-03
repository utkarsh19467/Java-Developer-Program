/**
 * 
 */
package com.udacity.jwdnd.course1.cloudstorage.securityconfig;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.udacity.jwdnd.course1.cloudstorage.services.encryptionservices.HashService;
import com.udacity.jwdnd.course1.cloudstorage.services.userservices.UserService;

/**
 * @author utkarsh
 *
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private UserService userService;
	
	@Autowired
	private HashService hashService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String)authentication.getCredentials();
		
		UserDetails user = userService.loadUserByUsername(username);
		password = hashService.getHashedValue(password, "SecretPassword");
		if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }
		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
		return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
