package com.logintrialthree.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.logintrialthree.model.Driver;
import com.logintrialthree.model.Role;
import com.logintrialthree.model.Site;
import com.logintrialthree.model.Trans;
import com.logintrialthree.model.User;
import com.logintrialthree.service.IDriverService;
import com.logintrialthree.service.ISiteService;
import com.logintrialthree.service.ITransService;
import com.logintrialthree.service.IUserService;


@Controller
public class UserController 
{
	 @Autowired
	 private IUserService userService;
	 
	 @Autowired
	 private ISiteService siteService;
	 
	 @Autowired
	 private IDriverService driverService;
	 
	 @Autowired 
	 private ITransService transService;
	 
	 @RequestMapping(value= {"/", "/login"}, method=RequestMethod.GET)
	 public ModelAndView login() 
	 {
		  ModelAndView model = new ModelAndView();
		  model.setViewName("user/login");
		  return model;
	 }
	 
	 @RequestMapping(value= {"/signup"}, method=RequestMethod.GET)
	 public ModelAndView signup() 
	 {
		  ModelAndView model = new ModelAndView();
		  User user = new User();
		  model.addObject("user", user);
		  model.setViewName("user/signup");
		  
		  return model;
	 }
	 
	 @RequestMapping(value= {"/signup"}, method=RequestMethod.POST)
	 public ModelAndView createUser(@Valid User user, BindingResult bindingResult, @RequestParam("multipartfile") MultipartFile multipartFile) throws IOException 
	 {
		  ModelAndView model = new ModelAndView();
		  User userExists = userService.findUserByEmail(user.getEmail());
		  
		  if(userExists != null) {
		   bindingResult.rejectValue("email", "error.user", "This email already exists!");
		  }
		  if(bindingResult.hasErrors()) {
		   model.setViewName("user/signup");
		  } else {
		   userService.saveUser(user,multipartFile);
		   model.addObject("msg", "User has been registered successfully!");
		   model.addObject("user", new User());
		   model.setViewName("user/signup");
		  }
		  
		  return model;
	 }
	 
	 @RequestMapping(value= {"/home/index"}, method=RequestMethod.GET)
	 public ModelAndView index() 
	 {
		  ModelAndView model = new ModelAndView();
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		  if (auth instanceof AnonymousAuthenticationToken) 
		  {
			  model.addObject("isLogin",false);
			  model.addObject("role", "guest");
		  }
		  else
		  {
			  User user = userService.findUserByEmail(auth.getName());
			  model.addObject("userName", user.getFirstname() + " " + user.getLastname());
			  model.addObject("isLogin",true);
			  Set<Role> roles = user.getRoles();
			  String role = "";
			  for(Role r:roles)
			  {
				  role = r.getRole();  
			  }
			  model.addObject("role", role);
		  }
		  
		  model.setViewName("home/index");
		  return model;
	 }
	 
	 @RequestMapping(value= {"/user/profile"}, method = RequestMethod.GET)
	 public ModelAndView showProfile()
	 {
		 ModelAndView model = new ModelAndView();
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 User user = userService.findUserByEmail(auth.getName());
		 model.addObject("user", user);
//		 model.addObject("userFirstName", user.getFirstname());
//		 model.addObject("userLastName", user.getLastname());
//		 model.addObject("userWallet", user.getWallet());
//		 model.addObject("userPicture", user.getPicture());
		 model.setViewName("user/profile");
		 return model;
	 }
	 
	 @RequestMapping(value= {"/user/updateBalance"}, method=RequestMethod.POST)
	 public ModelAndView updateBalance(@RequestParam(name = "amount")float amount)
	 {
		 ModelAndView model = new ModelAndView();
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 User user = userService.findUserByEmail(auth.getName());
		 float newBalance = amount + user.getWallet();
		 userService.updateBalance(user.getId(), newBalance);
		 model.addObject("user", user);
		 model.setViewName("user/profile");
		 return model;
	 }
	 
	 @RequestMapping(value = {"/user/bookSite"}, method = RequestMethod.GET)
	 public ModelAndView bookSite(@RequestParam(name = "siteName")String siteName, @RequestParam(name = "userId")int id,@RequestParam(name = "userWallet")float userWallet)
	 {
		 ModelAndView model = new ModelAndView();
		 Site site = siteService.findSiteByName(siteName);
		 float newBalance = userWallet - site.getSiteCost();
		 userService.updateBalance(id, newBalance);
		 
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 User user = userService.findUserByEmail(auth.getName());
		 Driver driver = driverService.findDriverByStatus("FREE");
		 transService.saveTrans(user, driver, site);
		 model.addObject("user",user);
		 model.addObject("site",site);
		 model.addObject("booking",true);
		 model.addObject("msg","Booking successful!");
		 List<Trans> trans = transService.findTransByUserId(id);
		 model.addObject("trans",trans);
		 model.setViewName("user/profile");
		 return model;
	 }
	 
	 @RequestMapping(value = {"/user/history"}, method=RequestMethod.GET)
	 public ModelAndView showHistory()
	 {
		 ModelAndView model = new ModelAndView();
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 User user = userService.findUserByEmail(auth.getName());
		 model.addObject("user",user);
		 List<Trans> trans = transService.findTransByUserId(user.getId());
		 model.addObject("trans",trans);
		 model.setViewName("user/profile");
		 return model;
	 }
	 
	 @RequestMapping(value= {"/home/home"}, method=RequestMethod.GET)
	 public ModelAndView showHome() 
	 {
		  ModelAndView model = new ModelAndView();
		  model.setViewName("home/home");
		  return model;
	 }
	 
	 @RequestMapping(value= {"/home/sites"}, method=RequestMethod.GET)
	 public ModelAndView showSites() 
	 {
		 ModelAndView model = new ModelAndView();
		 List<Site> sites = siteService.getAllSites();
		 model.addObject("sites",sites);
		 model.setViewName("home/sites");
		 return model;
	 }
	 
	 @RequestMapping(value = {"/home/siteProfile"}, method = RequestMethod.GET)
	 public ModelAndView showSiteProfile(@RequestParam(name = "siteName")String siteName)
	 {
		 ModelAndView model = new ModelAndView();
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 User user = userService.findUserByEmail(auth.getName());
		 Site site = siteService.findSiteByName(siteName);
		 if(user==null)
		 {
			 model.addObject("visitorStatus","GUEST");
		 }
		 else
		 {
			 model.addObject("visitorStatus","NOTGUEST");
		 }
		 model.addObject("site",site);
		 model.addObject("user",user);
		 model.setViewName("home/siteProfile");
		 return model;
	 }
	 
	 
	 @RequestMapping(value= {"/admin/addsite"}, method=RequestMethod.GET)
	 public ModelAndView showAddsite() 
	 {
		  ModelAndView model = new ModelAndView();
		  Site site = new Site();
		  model.addObject("site", site);
		  model.setViewName("admin/addsite");
		  return model;
	 }
	 
	 @RequestMapping(value = {"/admin/addsite"}, method = RequestMethod.POST)
	 public ModelAndView addSite(@Valid Site site, BindingResult bindingResult, @RequestParam("multipartfile") MultipartFile multipartFile) throws IOException
	 {
		 ModelAndView model = new ModelAndView();
		 Site siteExists = siteService.findSiteByName(site.getSiteName());
			  
		 if(siteExists != null) 
		 {
			 bindingResult.rejectValue("siteName", "error.site", "This site already exists!");
		 }
		 if(bindingResult.hasErrors()) 
		 {
			 model.addObject("errmsg", "This site already exists!");
			 model.setViewName("admin/addsite");
		 } 
		  else 
		  {
			  siteService.saveSite(site,multipartFile);
			  model.addObject("msg", "Site has been added successfully!");
			  model.addObject("site", new Site());
			  model.setViewName("admin/addsite");
		  }
		return model;
	 }
	 
	 @RequestMapping(value= {"/admin/adddriver"}, method=RequestMethod.GET)
	 public ModelAndView showAdddriver() 
	 {
		  ModelAndView model = new ModelAndView();
		  Driver driver = new Driver();
		  model.addObject("driver", driver);
		  model.setViewName("admin/adddriver");
		  return model;
	 }
	 
	 @RequestMapping(value = {"/admin/adddriver"}, method = RequestMethod.POST)
	 public ModelAndView addSite(@Valid Driver driver, BindingResult bindingResult) 
	 {
		 ModelAndView model = new ModelAndView();
		 Driver driverExists = driverService.findDriverByName(driver.getDriverName());
			  
		 if(driverExists != null) 
		 {
			 bindingResult.rejectValue("driverName", "error.driver", "This driver already exists!");
		 }
		 if(bindingResult.hasErrors()) 
		 {
			 model.addObject("errmsg", "This driver already exists!");
			 model.setViewName("admin/adddriver");
		 } 
		  else 
		  {
			  driverService.saveDriver(driver);
			  model.addObject("msg", "Driver has been added successfully!");
			  model.addObject("driver", new Driver());
			  model.setViewName("admin/adddriver");
		  }
		return model;
	 }
	 
	 @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
	 public ModelAndView accessDenied() {
	  ModelAndView model = new ModelAndView();
	  model.setViewName("errors/access_denied");
	  return model;
	 }
}
