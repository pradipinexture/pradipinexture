package com.pradip.customjwt.service;

import java.util.List;

import com.pradip.customjwt.model.User;


public interface ServiceInf {
	
	public User insertUser(User user);
	
	public List<User> getAllUsers();
	
	public User getUserById(int id);
	
	public void deleteUserById(int id);
}
