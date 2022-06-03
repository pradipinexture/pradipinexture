package com.pradip.customoauth2.service;

import java.util.List;

import com.pradip.customoauth2.model.User;



public interface ServiceInf {
	
	public User insertUser(User user);
	
	public List<User> getAllUsers();
	
	public User getUserById(int id);
	
	public void deleteUserById(int id);
}
