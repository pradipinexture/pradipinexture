package com.pradip.springbootmvc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pradip.springbootmvc.model.User;

@Transactional
@Repository
public interface UserDao extends JpaRepository<User, Integer>{
	
	boolean existsByEmailAndPassword(String email,String password);
	
	boolean existsByEmail(String email);
	
	List<User> findByEmail(String email);
	
	List<User> findByEmailAndPassword(String email,String password);
	
	@Modifying
	@Query("UPDATE User u SET u.password =:password WHERE u.email =:email")
	int updatePassword(@Param("email") String email, @Param("password") String password); 

}
