/**
 * 
 */
package com.udacity.jwdnd.course1.cloudstorage.services.credentialsservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.dtos.CredentialsDto;
import com.udacity.jwdnd.course1.cloudstorage.models.entities.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.services.modelconvertors.CredentialsConvertor;

/**
 * @author utkarsh
 *
 */
@Service
public class CredentialsService {

	@Autowired
	private CredentialsMapper credentialsMapper;

	@Autowired
	private CredentialsConvertor credentialsConvertor;

	public void addCredentials(CredentialsDto credentialsDto) {
		Credentials credentials = credentialsMapper.getCredentialById(credentialsDto.getCredentialId());
		if(credentials != null) {
			credentials = credentialsConvertor.credentialDataUpdate(credentials, credentialsDto);
			credentialsMapper.updateCredentialById(credentials);
		} else {
			credentialsMapper.save(credentialsConvertor.credentialsDtoToCredentials(credentialsDto));
		}
	}

	public void deleteCredential(Long credentialId) {
		credentialsMapper.deleteCredentialById(credentialId);
	}

	public CredentialsDto getCredential(Long credentialId) {
		return credentialsConvertor.credentialsToCredentialDto(credentialsMapper.getCredentialById(credentialId));
	}
}
