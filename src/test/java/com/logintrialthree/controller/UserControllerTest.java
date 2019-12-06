package com.logintrialthree.controller;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;

import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.logintrialthree.model.User;
import com.logintrialthree.service.IDriverService;
import com.logintrialthree.service.ISiteService;
import com.logintrialthree.service.ITransService;
import com.logintrialthree.service.IUserService;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;




public class UserControllerTest {

	@Mock
	 private IUserService userService;
	 
	 @Mock
	 private ISiteService siteService;
	 
	 @Mock
	 private IDriverService driverService;
	 
	 @Mock 
	 private ITransService transService;
	
	
	@Test
	public void test() {
		UserController userController=new UserController();
		ModelAndView model= userController.signup();		
		User user=new User();
		user.setActive(2);
		user.setEmail("random");
		user.setFirstname("randomname");
		user.setId(2);
		user.setLastname("lname");
		user.setPassword("any");
		user.setPicture("pic");
		user.setRoles(null);
		user.setWallet((float)2.2);
		BindingResult bindingResult=null;
		MultipartFile multipartFile=null;
		
		try {
		model=userController.createUser(user, bindingResult,  multipartFile);
		}
		catch(Exception e)
		{
			System.out.println("Exception caught!");
		}
		
		
		
		//model=userController.bookSite("string", 2,(float) 2.2);
		
		
	}

}
