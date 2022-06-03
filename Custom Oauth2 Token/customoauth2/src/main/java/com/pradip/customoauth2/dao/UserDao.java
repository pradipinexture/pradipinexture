package com.pradip.customoauth2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pradip.customoauth2.model.User;


@Repository
public interface UserDao extends JpaRepository<User, Integer>{

	User findByUsername(String username);
}
