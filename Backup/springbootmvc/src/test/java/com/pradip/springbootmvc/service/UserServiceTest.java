package com.pradip.springbootmvc.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pradip.springbootmvc.dao.AddressDao;
import com.pradip.springbootmvc.dao.UserDao;
import com.pradip.springbootmvc.model.Address;
import com.pradip.springbootmvc.model.Role;
import com.pradip.springbootmvc.model.User;



@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class UserServiceTest {
	
	@InjectMocks
	private UserServiceImpl userService;
	
	@Mock
	private UserDao userDao;
	
	@Mock
	private AddressDao addressDao;
	
	private User user=initializeUser();
	
	String email="sandip@gmail.com";
	String password="APi0O8IrWRqwcMI8Iojp7w==";

	
	
	User initializeUser() {
		User u=new User();
		u.setId(12);
		u.setName("Sandip");
		u.setEmail("sandip@gmail.com");
		u.setMobile("9966225511");
		u.setBirthdate("2000-04-21");
		u.setGender("male");
		u.setHobby("Cricket");
	
		Address ad=new Address();
		ad.setId(1);
		ad.setGeneral("general");
		ad.setCity("city");		
		ad.setState("fvd");
		ad.setPincode("sdfsdf");
		ad.setUser(u);
		
		Address ad1=new Address();
		ad1.setId(2);
		ad1.setGeneral("general");
		ad.setCity("city");	
		ad1.setState("fvd");
		ad1.setPincode("sdfsdf");
		ad1.setUser(u);

		Address ad3=new Address();
		ad1.setId(0);
		ad1.setGeneral("");
		ad.setCity("");	
		ad1.setState("");
		ad1.setPincode("");
		ad1.setUser(u);
		
		List<Address> addl=new ArrayList<>();
		addl.add(ad);
		addl.add(ad1);
		addl.add(ad3);
		
		u.setAddress(addl);
		
		Role r=new Role();
		r.setId(1);
		r.setRoleType(1);
		r.setUser(u);
		
		u.setRole(r);
		return u;
	}
	
	@Test
	@DisplayName("This test case for login request and is true")
	void userLoginServiceIfTest() throws Exception {
		
		when(userDao.existsByEmailAndPassword(user.getEmail(),user.getPassword())).thenReturn(true);	
		
		boolean actual=userService.userLoginService(user.getEmail(),user.getPassword());		
		
		assertThat(actual).isTrue();
		
		verify(userDao,atLeast(1)).existsByEmailAndPassword(user.getEmail(),user.getPassword());
	}
	
	@Test
	@DisplayName("This test case for login request and is false")
	void userLoginServiceElseTest() throws Exception {
	
		when(userDao.existsByEmailAndPassword(user.getEmail(),user.getPassword())).thenReturn(false);	
		
		boolean actual=userService.userLoginService(user.getEmail(),user.getPassword());		
		
		assertThat(actual).isFalse();
		
		verify(userDao,atLeast(1)).existsByEmailAndPassword(user.getEmail(),user.getPassword());
	}
	
	@Test
	@DisplayName("This test case for getAllUsers that return user list")
	void getAllUsersTest() throws Exception {
		
		List<User> userList=new ArrayList<>();
		userList.add(user);
		userList.add(user);
		userList.add(user);
		when(userDao.findAll()).thenReturn(userList);	
		
		List<User> actual = userService.getAllUsers();
		
		assertThat(actual).isNotNull();
		
		verify(userDao,atLeast(1)).findAll();
	}
	
	@Test
	@DisplayName("This test case for ")
	void existsByEmailTest() throws Exception {
		
		
		when(userDao.existsByEmail(user.getEmail())).thenReturn(true);	
		
		boolean actual = userService.existsByEmail(user.getEmail());
		
		assertThat(actual).isTrue();
		
		verify(userDao,atLeast(1)).existsByEmail(user.getEmail());
	}
	
	@Test
	@DisplayName("This test case for ")
	void updatePasswordIfTest() throws Exception {
		
		when(userDao.updatePassword(user.getEmail(),user.getPassword())).thenReturn(1);	

		boolean actual = userService.updatePassword(user.getEmail(),user.getPassword());

		assertThat(actual).isTrue();
		
		verify(userDao,atLeast(1)).updatePassword(user.getEmail(),user.getPassword());
	}
	
	@Test
	@DisplayName("This test case for ")
	void updatePasswordElseTest() throws Exception {
		
		when(userDao.updatePassword(user.getEmail(),user.getPassword())).thenReturn(0);	
		
		boolean actual = userService.updatePassword(user.getEmail(),user.getPassword());
		
		assertThat(actual).isFalse();
		
		verify(userDao,atLeast(1)).updatePassword(user.getEmail(),user.getPassword());
	}
	
	@Test
	@DisplayName("This test case for ")
	void getUserByEmailIfTest() throws Exception {
		
		List<User> userList=new ArrayList<>();
		
		userList.add(user);
		
		when(userDao.findByEmail(user.getEmail())).thenReturn(userList);
		
		User actual = userService.getUserByEmail(user.getEmail());
		
		
		assertThat(actual).isNotNull();
		
		verify(userDao,atLeast(1)).findByEmail(user.getEmail());
	}
	
	@Test
	@DisplayName("This test case for ")
	void getUserByEmailElseTest() throws Exception {
		List<User> userList=new ArrayList<>();
		
		when(userDao.findByEmail(user.getEmail())).thenReturn(userList);
		
		User actual = userService.getUserByEmail(user.getEmail());
		
		assertThat(actual).isNull();
		
		verify(userDao,atLeast(1)).findByEmail(email);
		
	}
	
	@Test
	@DisplayName("This test case for insert user")
	void addUserInsertTest() throws Exception {
		
		user.setId(0);
		user.getAddress().get(0).setId(0);
		user.getAddress().get(1).setId(0);
		user.getRole().setId(0);
		
		when(userDao.save(user)).thenReturn(user);	
	
		boolean actual = userService.userSave(user);
		
		assertThat(actual).isTrue();
		
		verify(userDao,atLeast(1)).save(user);
		
	}
	
	@Test
	@DisplayName("This test case for update user")
	void addUserUpdateTest() throws Exception {

		user.getAddress().get(0).setId(0);

		when(userDao.save(user)).thenReturn(null);	
	
		boolean actual = userService.userSave(user);
		
		assertThat(actual).isFalse();
		
		verify(userDao,atLeast(1)).save(user);
	}

	@Test
	@DisplayName("This test case for ")
	void deleteByIdTest() throws Exception {
		
		userService.deleteById(12);
		
		verify(userDao,atLeast(1)).deleteById(12);
	}

}
