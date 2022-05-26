package com.pradip.springbootmvc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pradip.springbootmvc.dao.AddressDao;
import com.pradip.springbootmvc.dao.UserDao;
import com.pradip.springbootmvc.model.Address;
import com.pradip.springbootmvc.model.Role;
import com.pradip.springbootmvc.model.User;
import com.pradip.springbootmvc.util.EncryDecryAES;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AddressDao addressDao;
	
//	@Autowired
//	private User user;
	
	@Override
	public boolean userSave(User user) {
		// Below while loop for remove null addresses from arraylist
		int i=0;
		while(user.getAddress().size()-1 != i) {
			String h=user.getAddress().get(i).getCity();
			if(h != null) {
				i++;
			}
			else {
				user.getAddress().remove(i);
			}        	
		}
		// Below loop for add userid column in a address table as a foreignkey
		for(Address address:user.getAddress()) {
			address.setUser(user);
		}
		/*
		 * 0 for user
		 * 1 for admin
		 * 2 for superadmin
		 * */
		// Below code for add userid column in a Role table as a foreignkey
		if(user.getId() == 0  ) {
			Role role=new Role();
			System.out.println("\n\nTHis is 0");
			role.setRoleType(0);
			role.setUser(user);
			user.setRole(role);			
		}
		
		
		// Store address id in arraylist
		List<Integer> addIdArr=new ArrayList<Integer>(); 
		if(user.getId() != 0 ) {
			
			for(Address address : user.getAddress()) {
				addIdArr.add(address.getId());
			}
			// Delete other address before update user.
			addressDao.deleteByIdIn(addIdArr,user);
		}
		
		// Now update the user 
		User insertedUser=userDao.save(user);
		if(insertedUser != null) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean userLoginService(String email, String password) {
		if(userDao.existsByEmailAndPassword(email, EncryDecryAES.encDecryAESAlgo(password, "encrypt"))) {
			System.out.println("User email and password matched.");
			return true;
		}
		else {
			System.out.println("User email and password not matched.");
			return false;			
		}
		
	}

	@Override
	public User getUserByEmail(String email) {
		List<User> findUser=userDao.findByEmail(email);
		if(findUser.size() != 0) {
			return findUser.get(0);
		}
		else {
			System.out.println("\n=> User not available from getByEmail. ");
			return null;
		}
	}

	@Override
	public List<User> getAllUsers() {
		Iterable<User> findAll = userDao.findAll();
		List<User> result = new ArrayList<>();
	    findAll.forEach(result::add);
		return result;
	}

//	@Override
//	public boolean existsByEmailAndPassword(String email, String password) {
//		return userDao.existsByEmailAndPassword(email, password);
//	}

	@Override
	public boolean existsByEmail(String email) {
		return userDao.existsByEmail(email);
	}

	@Override
	public void deleteById(int id) {
		userDao.deleteById(id);
		
	}

	@Override
	public boolean updatePassword(String email, String password) {
		if(userDao.updatePassword(email, EncryDecryAES.encDecryAESAlgo(password, "encrypt")) != 0) {
			return true;
		}
		else {
			return false;
		}
	}

}
