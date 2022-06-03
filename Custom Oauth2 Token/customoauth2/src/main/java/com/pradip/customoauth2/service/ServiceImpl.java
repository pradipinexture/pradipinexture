package com.pradip.customoauth2.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pradip.customoauth2.dao.UserDao;
import com.pradip.customoauth2.model.User;


@Service
public class ServiceImpl implements ServiceInf{

	@Autowired
	private UserDao userDao;
	
	@Override
	public User insertUser(User user) {
		return userDao.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.findAll();
	}

	@Override
	public User getUserById(int id) {
		Optional<User> findById = userDao.findById(id);
		if(findById != null) {
			return findById.stream().collect(Collectors.toList()).get(0);			
		}
		else {
			return null;
		}

	}

	@Override
	public void deleteUserById(int id) {
		userDao.deleteById(id);
	}


}
