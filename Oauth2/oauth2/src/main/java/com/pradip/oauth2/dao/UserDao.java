package com.pradip.oauth2.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pradip.oauth2.model.User;


@Repository
public interface UserDao extends JpaRepository<User, Integer>{

	User findByUsername(String username);
}
