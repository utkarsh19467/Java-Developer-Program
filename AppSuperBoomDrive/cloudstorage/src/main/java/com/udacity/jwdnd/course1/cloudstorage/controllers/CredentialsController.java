/**
 * 
 */
package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.udacity.jwdnd.course1.cloudstorage.models.dtos.CredentialsDto;
import com.udacity.jwdnd.course1.cloudstorage.services.credentialsservices.CredentialsService;

/**
 * @author utkarsh
 *
 */
@Controller
public class CredentialsController {

	@Autowired
	private CredentialsService credentialsService;
	
	@ModelAttribute("credentials")
	public CredentialsDto getCredentialsDto() {
		return new CredentialsDto();
	}
	
	@PostMapping(value = "/addCredentials")
	public String addCredentials(@ModelAttribute("credentials") CredentialsDto credentialsDto) {
		try {
			credentialsService.addCredentials(credentialsDto);
		} catch(Exception exception) {
			return "redirect:/result?failure";
		}
		return "redirect:/result?success";
	}
	
	@GetMapping(value = "/deleteCredential/{credentialId}")
	public String deleteCredential(@PathVariable(name = "credentialId") String credentialID) {
		Long credentialId = Long.parseLong(credentialID);
		credentialsService.deleteCredential(credentialId);
		return "redirect:/result?deleteSuccess";
	}
	
	@GetMapping(value = "/getCredential/{credentialId}")
	@ResponseBody
	public CredentialsDto getCredential(@PathVariable(name = "credentialId") String credentialID) {
		Long credentialId = Long.parseLong(credentialID);
		return credentialsService.getCredential(credentialId);
	}
}
