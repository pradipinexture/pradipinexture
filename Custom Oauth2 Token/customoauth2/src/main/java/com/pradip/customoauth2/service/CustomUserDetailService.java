package com.pradip.customoauth2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pradip.customoauth2.dao.UserDao;
import com.pradip.customoauth2.model.CustomUserDetail;
import com.pradip.customoauth2.model.User;



@Service(value = "uu")
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userDao.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("No User");
		}
		System.out.println(user);
		return new CustomUserDetail(user);
	}

}
