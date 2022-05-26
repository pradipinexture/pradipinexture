package com.pradip.springbootmvc.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.pradip.springbootmvc.model.Address;
import com.pradip.springbootmvc.model.Role;
import com.pradip.springbootmvc.model.User;
import com.pradip.springbootmvc.service.UserService;

//@WebMvcTest(UserController.class)
//@SpringBootTest
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
//	@Autowired
//	private MockMvc mockmvc;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
   @Autowired
	private MockMvc mockmvc;
	
	private User user;
	
	@MockBean
	private UserService userService;
	
	
//    @Before(value = "")
//    public void setup() throws Exception {
//        mockmvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//                .addFilters(new GeneralFilter()).build();
//    }
	
	User getUser() {
		user=new User();
		user.setId(12);
		user.setName("Sandip");
		user.setEmail("sandip@gmail.com");
		user.setMobile("9966225511");
		user.setBirthdate("2000-04-21");
		user.setGender("male");
		user.setHobby("Cricket");
		user.setImageBase64("");
		user.setPassword("this is password");
	
		Address ad=new Address();
		ad.setId(1);
		ad.setGeneral("general");
		ad.setCity("city");		
		ad.setState("fvd");
		ad.setPincode("sdfsdf");
		ad.setUser(user);
		
		Address ad1=new Address();
		ad1.setId(2);
		ad1.setGeneral("general");
		ad.setCity("city");	
		ad1.setState("fvd");
		ad1.setPincode("sdfsdf");
		ad1.setUser(user);

		List<Address> addl=new ArrayList<>();
		addl.add(ad);
		addl.add(ad1);
		
		user.setAddress(addl);
		
		Role r=new Role();
		r.setId(1);
		r.setRoleType(1);
		r.setUser(user);
		user.setRole(r);
		
		return user;
	}
	
	@Test
	public void indexPageTest() throws Exception {
		user=getUser(); 
        ResultActions perform = mockmvc.perform(MockMvcRequestBuilders.get("/").sessionAttr("sessionUser", user));
		perform.andExpect(status().isOk());
		assertThat(perform.andReturn().getModelAndView().getViewName()).isEqualTo("index");
	}
	
	@Test
	public void registerPageTest() throws Exception {
		user=getUser(); 
        ResultActions perform = mockmvc.perform(MockMvcRequestBuilders.get("/register").sessionAttr("sessionUser", user));
		perform.andExpect(status().isOk());
		assertThat(perform.andReturn().getModelAndView().getViewName()).isEqualTo("register");
	}

	@Test
	public void forgotPageTest() throws Exception {
		user=getUser(); 
	    ResultActions perform = mockmvc.perform(MockMvcRequestBuilders.get("/forgot").sessionAttr("sessionUser", user));
		perform.andExpect(status().isOk());
		assertThat(perform.andReturn().getModelAndView().getViewName()).isEqualTo("forgot");
	}

	@Test
	public void errorPageTest() throws Exception {
		user=getUser(); 
	    ResultActions perform = mockmvc.perform(MockMvcRequestBuilders.get("/error").sessionAttr("sessionUser", user));
		perform.andExpect(status().isOk());
		assertThat(perform.andReturn().getModelAndView().getViewName()).isEqualTo("test");
	}
	
	@Test
	public void viewusersPageTest() throws Exception {
		
		user=getUser(); 
		List<User> userList=new ArrayList<>();
		userList.add(user);
		userList.add(user);
		when(userService.getAllUsers()).thenReturn(userList);
		
		ResultActions perform = mockmvc.perform(MockMvcRequestBuilders.get("/viewusers").sessionAttr("sessionUser", user));
		perform.andExpect(status().isOk());
		assertThat(perform.andReturn().getModelAndView().getViewName()).isEqualTo("viewusers");
		verify(userService,atLeast(1)).getAllUsers();
	}
	
	@Test
	public void profilePageTest() throws Exception {
		user=getUser(); 
		when(userService.getUserByEmail("sandip@gmail.com")).thenReturn(user);
		ResultActions perform = mockmvc.perform(MockMvcRequestBuilders.get("/profile?profileEmail=sandip@gmail.com").sessionAttr("sessionUser", user));
		perform.andExpect(status().isOk());
		assertThat(perform.andReturn().getModelAndView().getViewName()).isEqualTo("profile");
		verify(userService,atLeast(1)).getUserByEmail("sandip@gmail.com");
	}
	
	@Test
	public void deleteUserTest() throws Exception {
		user=getUser(); 
		userService.deleteById(12);
		ResultActions perform = mockmvc.perform(MockMvcRequestBuilders.get("/DeleteUser?id=12").sessionAttr("sessionUser", user));
	}
	
	
	@Test
	public void emailCheckIfTest() throws Exception {
		user=getUser(); 
		when(userService.existsByEmail("sandip@gmail.com")).thenReturn(true);
		mockmvc.perform(MockMvcRequestBuilders.get("/EmailCheck?cuEmail=sandip@gmail.com").sessionAttr("sessionUser", user));
	}
	@Test
	public void emailCheckElseTest() throws Exception {
		user=getUser(); 
		when(userService.existsByEmail("sandip@gmail.com")).thenReturn(false);
		mockmvc.perform(MockMvcRequestBuilders.get("/EmailCheck?cuEmail=sandip@gmail.com").sessionAttr("sessionUser", user));
	}
	
	@Test
	public void processForgotTest() throws Exception {
		user=getUser(); 
		when(userService.updatePassword("sandip@gmail.com",user.getPassword())).thenReturn(true);

		mockmvc.perform(MockMvcRequestBuilders.get("/ProcessForgot")
				.param("email", user.getEmail())
				.param("password", user.getPassword())
				.sessionAttr("sessionUser", user)).andExpect(status().is(200));
		verify(userService,atLeast(1)).updatePassword("sandip@gmail.com",user.getPassword());
	}
	
	@Test
	public void processForgotFalseTest() throws Exception {
		user=getUser(); 		
		when(userService.updatePassword("sandip@gmail.com",user.getPassword())).thenReturn(false);

		mockmvc.perform(MockMvcRequestBuilders.get("/ProcessForgot")
				.param("email", user.getEmail())
				.param("password", user.getPassword())
				.sessionAttr("sessionUser", user)).andExpect(status().is(200));
		
		verify(userService,atLeast(1)).updatePassword("sandip@gmail.com",user.getPassword());
	}
	
	@Test
	public void generateCSVTest() throws Exception {
		user=getUser();
		mockmvc.perform(MockMvcRequestBuilders.get("/GenerateCSV")
				.sessionAttr("sessionUser", user)).andExpect(redirectedUrl("/viewusers"));
	}
	
	@Test
	public void logoutTest() throws Exception {
		user=getUser();
		mockmvc.perform(MockMvcRequestBuilders.get("/Logout")
				.sessionAttr("sessionUser", user)).andExpect(redirectedUrl("/"));
	}
	
	@Test
	public void processLoginAdminTest() throws Exception {
		user=getUser();
		when(userService.userLoginService(user.getEmail(), user.getPassword())).thenReturn(true);
		when(userService.getUserByEmail(user.getEmail())).thenReturn(user);
		
		mockmvc.perform(MockMvcRequestBuilders.get("/ProcessLogin")
				.param("email", user.getEmail())
				.param("password", user.getPassword())
				.sessionAttr("sessionUser", user)).andExpect(redirectedUrl("/viewusers"));
		
		verify(userService,atLeast(1)).userLoginService(user.getEmail(), user.getPassword());
		verify(userService,atLeast(1)).getUserByEmail(user.getEmail());
	}
	
	@Test
	public void processLoginUserTest() throws Exception {
		user=getUser();
		user.getRole().setRoleType(0);
		when(userService.userLoginService(user.getEmail(), user.getPassword())).thenReturn(true);
		when(userService.getUserByEmail(user.getEmail())).thenReturn(user);
		
		mockmvc.perform(MockMvcRequestBuilders.get("/ProcessLogin")
				.param("email", user.getEmail())
				.param("password", user.getPassword())
				.sessionAttr("sessionUser", user)).andExpect(redirectedUrl("/profile?profileEmail=sandip@gmail.com"));
		
		verify(userService,atLeast(1)).userLoginService(user.getEmail(), user.getPassword());
		verify(userService,atLeast(1)).getUserByEmail(user.getEmail());
	}
	
	@Test
	public void processLoginFalseTest() throws Exception {
		user=getUser();
		when(userService.userLoginService(user.getEmail(), user.getPassword())).thenReturn(false);
		
		mockmvc.perform(MockMvcRequestBuilders.get("/ProcessLogin")
				.param("email", user.getEmail())
				.param("password", user.getPassword())
				.sessionAttr("sessionUser", user)).andExpect(status().isOk());
		verify(userService,atLeast(1)).userLoginService(user.getEmail(), user.getPassword());
	}
	
	@Test
	public void editProfileTest() throws Exception {
		user=getUser();
		when(userService.getUserByEmail(user.getEmail())).thenReturn(user);
		mockmvc.perform(MockMvcRequestBuilders.get("/EditProfile").param("email", user.getEmail()).sessionAttr("sessionUser", user)).andExpect(status().isOk());
		verify(userService,atLeast(1)).getUserByEmail(user.getEmail());
	}
	
	@Test
	public void adduserHasErrorTest() throws Exception {
		user=getUser();
		mockmvc.perform(MockMvcRequestBuilders.post("/AddUser")
				.sessionAttr("sessionUser", user)).andExpect(status().is(200));
	}
	
	@Test
	public void adduserTest() throws Exception {
		user=getUser();
		user.setId(13);
		
		when(userService.userSave(user)).thenReturn(true);
		
		mockmvc.perform(MockMvcRequestBuilders.post("/AddUser")
				.flashAttr("userObj", user)
				.sessionAttr("sessionUser", user)).andExpect(status().is(302));
		
		verify(userService,atLeast(1)).userSave(user);
	}
	
}
