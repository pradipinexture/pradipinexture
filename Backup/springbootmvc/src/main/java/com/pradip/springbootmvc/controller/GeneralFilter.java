package com.pradip.springbootmvc.controller;


import java.io.IOException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.pradip.springbootmvc.model.User;

import javax.servlet.*;
import java.util.*;

// Implements Filter class
@Component
public class GeneralFilter extends HttpFilter {
//	@Override
//	public void destroy() {
//		System.out.println("\n=> Filter destroyed\n");
//	}

	@Override
	public void doFilter(HttpServletRequest httpRequest, HttpServletResponse httpResponse, FilterChain chain) 
			throws IOException, ServletException {

		String path=httpRequest.getServletPath();
		System.out.println("path is : "+path);
		httpResponse.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); 

		HttpSession session=httpRequest.getSession(false);
		User userObjec=null;

		boolean notForUser=path.equals("/DeleteUser") || path.startsWith("/viewusers") || path.startsWith("/GenerateCSV" ) || path.startsWith("/register");
		boolean beforeLoginPages=path.equals("/") || path.equals("/index") || path.equals("/ProcessLogin") || path.equals("/forgot") || path.equals("/ProcessForgot") ;
		if(session != null) {
			userObjec=(User) (session.getAttribute("sessionUser"));
			System.out.println("user from filter is : "+userObjec);
		}
		if(userObjec  !=null) {
			if(userObjec.getRole().getRoleType() != 1) {

				if(beforeLoginPages || notForUser) {
					httpResponse.sendRedirect("profile?profileEmail="+userObjec.getEmail());
				}
				else {
					chain.doFilter(httpRequest, httpResponse);
				}        		 
			}
			else {
				if(beforeLoginPages) {
					httpResponse.sendRedirect("viewusers");
				}
				else {
					chain.doFilter(httpRequest, httpResponse);
				}
			}
		}
		else {
			if(beforeLoginPages || path.startsWith("/register")|| path.startsWith("/EmailCheck") || path.startsWith("/AddUser")  || path.endsWith(".css") || path.endsWith(".js")|| path.endsWith(".map")) {
				chain.doFilter(httpRequest, httpResponse);
			}
			else {
				httpResponse.sendRedirect("index");
			}
		}
	}

//	@Override
//	public void init(FilterConfig filterconfig) throws ServletException {
//		System.out.println("\n=> Filter initialized\n");
//	}
}