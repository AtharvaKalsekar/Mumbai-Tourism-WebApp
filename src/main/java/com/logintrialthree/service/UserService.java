package com.logintrialthree.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.codec.Base64;
//import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.logintrialthree.model.Role;
import com.logintrialthree.model.User;
import com.logintrialthree.repository.RoleRepository;
import com.logintrialthree.repository.UserRepository;

import java.io.IOException;
//import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

@Service("userService")
public class UserService implements IUserService
{
	@Autowired
	 private UserRepository userRepository;
	 
	 @Autowired
	 private RoleRepository roleRespository;
	 
	 @Autowired
	 private BCryptPasswordEncoder bCryptPasswordEncoder;

	 @Override
	 public User findUserByEmail(String email) {
	  return userRepository.findByEmail(email);
	 }

	 @Override
	 public void saveUser(User user, MultipartFile multipartFile) 
	 {
		  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		  user.setActive(1);
		  String encodedString = null;
		  try 
		  {
			  encodedString = new String(Base64.encode(multipartFile.getBytes()));
		  } 
		  catch (IOException e) 
		  {
			encodedString = "image_absent";
			e.printStackTrace();
		  }
		  user.setPicture(encodedString);
		  //Role userRole = roleRespository.findByRole("ADMIN");
		  Role userRole = roleRespository.findByRole("USER");
		  user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		  userRepository.save(user);
	 }

	@Override
	public void updateBalance(int id, float balance) 
	{
		userRepository.updateUserBalance(id, balance);	
	}
}
