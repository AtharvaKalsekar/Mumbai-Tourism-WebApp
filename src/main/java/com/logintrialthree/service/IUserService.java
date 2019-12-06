package com.logintrialthree.service;

import org.springframework.web.multipart.MultipartFile;

import com.logintrialthree.model.User;

public interface IUserService 
{
	public User findUserByEmail(String email);
	public void saveUser(User user, MultipartFile multipartFile);
	public void updateBalance(int id, float balance);
}
