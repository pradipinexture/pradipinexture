package com.pradip.springbootmvc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pradip.springbootmvc.model.Address;
import com.pradip.springbootmvc.model.User;

@Repository
@Transactional
public interface AddressDao extends JpaRepository<Address, Integer>{
//	int deleteByIdIn(int[] id);
	
	@Modifying
	@Query("delete from Address a where a.id not in (:ids) and a.user=:userObj")
	int deleteByIdIn(@Param("ids") List<Integer> ids,@Param("userObj") User userObj);
	
}
