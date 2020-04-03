/**
 * 
 */
package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.udacity.jwdnd.course1.cloudstorage.services.HomeDetailService;

/**
 * @author utkarsh
 *
 */
@Controller
public class HomeController {
	
	@Autowired
	private HomeDetailService detailService;
	
	@GetMapping(value = {"/login"})
	public String login() {
		return "login";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = {"/home","/"})
	public String home(Model model) {
		model.addAttribute("userInfo", detailService.getAllUserInfo());
		return "home";
	}
	
	@GetMapping(value = "/result")
	public String result() {
		return "result";
	}
}
