package com.pradip.customjwt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
	
//	private BCryptPasswordEncoder passEncode=new BCryptPasswordEncoder();
//	
//	@Autowired
//	private ServiceInf serviceInf;
	
	
	@RequestMapping("/loginpage")
	public String getIndexPage() {
//		User u=new User();
//		u.setUsername("sandip@gmail.com");
//		u.setPassword(passEncode.encode("sandip123"));
//		u.setRole("NORMAL");
//		serviceInf.insertUser(u);
		return "index";
	}
}
