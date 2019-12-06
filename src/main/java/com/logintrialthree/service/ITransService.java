package com.logintrialthree.service;

import java.util.List;

import com.logintrialthree.model.Driver;
import com.logintrialthree.model.Site;
import com.logintrialthree.model.Trans;
import com.logintrialthree.model.User;

public interface ITransService 
{
	public List<Trans> findTransByUserId(int userId);
	public void saveTrans(User user,Driver driver,Site site);
}
