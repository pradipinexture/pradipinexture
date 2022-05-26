package com.pradip.springbootmvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pradip.springbootmvc.model.User;
import com.pradip.springbootmvc.service.UserService;
import com.pradip.springbootmvc.util.GenerateCSV;

@Controller
public class UserController {
	@Autowired
	private User user;

	@Autowired
	private UserService userService;

	HttpSession session;

	@GetMapping(value = {"/","index"})
	public String getIndexPage(){
		return "index";	
	}

	@GetMapping("/register")
	public String getRegisterPage(Model model){

		model.addAttribute("userObj", new User());
		return "register";
	}

	@GetMapping("/forgot")
	public String getforgotPage(){
		return "forgot";
	}

	@GetMapping("/error")
	public String getErrorPage(){
		return "test";
	}

	@GetMapping("/viewusers")
	public String getViewUsersPage(Model model){
		List<User> userList=userService.getAllUsers();
		System.out.println("users are : "+userList.size());
		model.addAttribute("AllUserList", userList);
		return "viewusers";
	}

	@GetMapping("/profile")
	public String getProfilePage(@RequestParam("profileEmail") String email,Model model){
		System.out.println("\n=> Email for profile is : "+userService.getUserByEmail(email));
		model.addAttribute("profileUser",userService.getUserByEmail(email));
		return "profile";
	}

	@PostMapping("/AddUser")
	public String insertNewUser(@Valid @ModelAttribute("userObj") User userData,BindingResult error,HttpServletRequest request) {		
		if(error.hasErrors()) {
			System.out.println("\n\nError from backend\n\n"+error);
			return "register";
		}

		session=request.getSession();

		// Get current session user object
		User user=(User)session.getAttribute("sessionUser");
		// we check image available or not in edit form data 
		if(userData.getImageBase64().length() == 0) {
			// First we get Image byte array from session that setted in EditProfile Controller
			System.out.println("hello");
			String imageBase64= (String) session.getAttribute("editUserImage");
			// Now we don't need editProfileImage attribute we remove that image.
			session.removeAttribute("editUserImage");
			userData.setImageBase64(imageBase64);
			System.out.println("\n=> Image not selected, So old image selected default.");
		}

		// Now add or update user form data
		userService.userSave(userData);
		if(user != null) {
			System.out.println("session id : "+user.getId()+" data id : "+userData.getId());
			if(user.getId() == userData.getId()) {
				user.setEmail(userData.getEmail());
			}	
		}



		return "redirect:/";		
	}
	@RequestMapping("/EditProfile")
	public String getEditProfilePage(@RequestParam("email") String email,Model model,HttpServletRequest request) {
		user =userService.getUserByEmail(email);
		session=request.getSession();
		session.setAttribute("editUserImage", user.getImageBase64());
		model.addAttribute("userObj", user);
		return "register";
	}
	@RequestMapping(value = "/ProcessLogin")
	public String processLogin(@RequestParam("email") String email, @RequestParam("password") String password,Model model,HttpServletRequest request) {

		if(userService.userLoginService(email, password)) {
			session=request.getSession();
			User sUser=userService.getUserByEmail(email);
			System.out.println("\n=> Hello from session : "+sUser);
			session.setAttribute("sessionUser", sUser);

			if(sUser.getRole().getRoleType() != 1) {
				return   "redirect:/profile?profileEmail="+email;
			}
			else {
				return  "redirect:/viewusers";
			}
		}
		else {
			model.addAttribute("loginError", "Please enter Valid credentials.");
			return "index";
		}	
	}

	@RequestMapping("/EmailCheck")
	@ResponseBody
	public String getEmailCheck(@RequestParam("cuEmail") String email) {
		if(userService.existsByEmail(email)) {
			System.out.println("Email already exist");
			return "!! Email already exist. ";
		}
		else {
			return "";
		}	
	}	


	@RequestMapping("/DeleteUser")
	@ResponseBody
	public String deleteUser(@RequestParam("id") int id) {
		System.out.println(id);
		userService.deleteById(id);
		return "User Deleted";		
	}


	@RequestMapping("/ProcessForgot")
	public String ProcessForgot(@RequestParam("email") String email,@RequestParam("password") String password) {
		if(userService.updatePassword(email, password)) {
			return "index";
		}
		else {
			return "forgot";
		}
	}	

	@RequestMapping("/GenerateCSV")
	public String generateCSV() {
		GenerateCSV csv=new GenerateCSV();
		csv.printCSV(userService);
		return "redirect:/viewusers";		
	}

	@RequestMapping("/Logout")
	public String logout(HttpServletRequest request) {

		HttpSession session=request.getSession();
		session.invalidate();
		return "redirect:/";		
	}

}
