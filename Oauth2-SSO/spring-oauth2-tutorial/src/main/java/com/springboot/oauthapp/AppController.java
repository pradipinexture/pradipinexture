package com.springboot.oauthapp;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AppController {

	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/classified")
	public String classified(@AuthenticationPrincipal OAuth2User principal, Model model) {
		model.addAttribute("name", principal.getAttribute("name"));
		model.addAttribute("authorities", principal.getAuthorities());
		return "classified";
	}
	
	@GetMapping("/home")
	@ResponseBody
	public String homeR() {
		return "this is home";
	}
	
}
