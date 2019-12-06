package com.logintrialthree.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logintrialthree.model.Driver;
import com.logintrialthree.repository.DriverRepository;

@Service("driverService")
public class DriverService implements IDriverService 
{
	@Autowired
	private DriverRepository driverRepository;
	
	@Override
	public Driver findDriverByStatus(String status) 
	{
		return driverRepository.findFirstByStatusIgnoreCase(status);
	}

	@Override
	public void saveDriver(Driver driver) 
	{
		driver.setStatus("FREE");
		driverRepository.save(driver);
	}

	@Override
	public Driver findDriverByName(String driverName) {
		return driverRepository.findFirstByDriverNameIgnoreCase(driverName);
	}

}
