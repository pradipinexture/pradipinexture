package com.pradip.springbootmvc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pradip.springbootmvc.model.User;


public interface UserService {
	
	boolean userSave(User user);

	boolean existsByEmailAndPassword(String email,String password);
	
	boolean existsByEmail(String email);
	
	void deleteById(int id);
	
	boolean userLoginService(String email, String password);

	User getUserByEmail(String email);
	
	List<User> getAllUsers();
	
	boolean updatePassword(String email,String password);
}
