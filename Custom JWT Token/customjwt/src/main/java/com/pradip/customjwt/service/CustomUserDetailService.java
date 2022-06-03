package com.pradip.customjwt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pradip.customjwt.dao.UserDao;
import com.pradip.customjwt.model.CustomUserDetail;
import com.pradip.customjwt.model.User;


@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<User> user = this.userDao.findByUsername(username);
		if(user.get(0) == null) {
			throw new UsernameNotFoundException("No User");
		}
		System.out.println(user.get(0));
		return new CustomUserDetail(user.get(0));
	}

}
