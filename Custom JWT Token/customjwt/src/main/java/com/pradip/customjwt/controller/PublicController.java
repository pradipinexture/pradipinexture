package com.pradip.customjwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
	
	@GetMapping("/")
	public String getHome() {
		return "This is home page";
	}
	
	@GetMapping("/register")
	public String getRegister() {
		return "This is register page";
	}
	
	@GetMapping("/login")
	public String getLogin() {
		return "This is login page";
	}
}
