package com.pradip.securitydemo1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pradip.securitydemo1.model.User;
import com.pradip.securitydemo1.service.ServiceInf;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private ServiceInf serviceInf;
	
	@GetMapping("/")
	public List<User> getAllUsers(){
		return this.serviceInf.getAllUsers();
	}
	
	@GetMapping("/getuser/{id}")
	public User getUserById(@PathVariable("id") int id) {
		return this.serviceInf.getUserById(id);
	}
	
	@PostMapping("/adduser")
	public User insertUser(@RequestBody User user) {
		return this.serviceInf.insertUser(user);
	}
	
//	@GetMapping("/{id}")
//	public User deleteUserById(@PathVariable("id") int id) {
//		return this.serviceInf.deleteUserById(id);
//	}
}
