/**
 * 
 */
package com.udacity.jwdnd.course1.cloudstorage.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.course1.cloudstorage.models.dtos.UserInfoDto;
import com.udacity.jwdnd.course1.cloudstorage.services.userservices.UserService;

/**
 * @author utkarsh
 *
 */
@Controller
@RequestMapping(value = "/register")
public class UserRegistrationController {

	@Autowired
	private UserService userService;
	
	@ModelAttribute("user")
	public UserInfoDto getUserInfoDto() {
		return new UserInfoDto();
	}
	
	@GetMapping
	public String showRegistrationForm(Model model) {
		return "register";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") @Valid UserInfoDto userInfoDto, BindingResult result) {
		UserInfoDto userInfo = userService.findByUsername(userInfoDto.getUsername());
		if(userInfo != null) {
			result.rejectValue("username", null, "There is already a username registered with this");
		}
		if(result.hasErrors()) {
			return "register";
		}
		
		userService.save(userInfoDto);
		return "redirect:/register?success";
	}	
}
