package com.logintrialthree.service;


import com.logintrialthree.model.Driver;


public interface IDriverService 
{
	public Driver findDriverByStatus(String status);
	public void saveDriver(Driver driver);
	public Driver findDriverByName(String driverName);
}
