package com.pradip.customjwt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pradip.customjwt.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{

	List<User> findByUsername(String username);
}
