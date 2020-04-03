/**
 * 
 */
package com.udacity.jwdnd.course1.cloudstorage.services.modelconvertors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.udacity.jwdnd.course1.cloudstorage.models.dtos.CredentialsDto;
import com.udacity.jwdnd.course1.cloudstorage.models.entities.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.services.encryptionservices.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.userservices.AuthenticatedUserUtility;

/**
 * @author utkarsh
 *
 */
@Component
public class CredentialsConvertor {

	@Autowired
	private AuthenticatedUserUtility userUtility;

	@Autowired
	private EncryptionService encryptionService;

	public Credentials credentialsDtoToCredentials(CredentialsDto credentialsDto) {
		Credentials credentials = new Credentials();
		credentials.setUrl(credentialsDto.getUrl());
		credentials.setUserId(userUtility.getAuthenticatedUser().getUserId());
		credentials.setUsername(credentialsDto.getUsername());
		credentials.setKey(encryptionService.getSecureKey());
		credentials.setPassword(encryptionService.encryptValue(credentialsDto.getPassword(), credentials.getKey()));
		return credentials;
	}
	
	public CredentialsDto credentialsToCredentialDto(Credentials credentials) {
		CredentialsDto credentialsDto = new CredentialsDto();
		credentialsDto.setUrl(credentials.getUrl());
		credentialsDto.setUsername(credentials.getUsername());
		credentialsDto.setCredentialId(credentials.getCredentialId());
		credentialsDto.setPassword(encryptionService.decryptValue(credentials.getPassword(), credentials.getKey()));
		return credentialsDto;
	}
	
	public Credentials credentialDataUpdate(Credentials credentials, CredentialsDto credentialsDto) {
		credentials.setUrl(credentialsDto.getUrl());
		credentials.setUsername(credentialsDto.getUsername());
		credentials.setPassword(encryptionService.encryptValue(credentialsDto.getPassword(), credentials.getKey()));
		return credentials;
	}
}
