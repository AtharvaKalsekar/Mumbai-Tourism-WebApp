package com.logintrialthree.service;

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

import com.logintrialthree.model.Driver;
import com.logintrialthree.repository.DriverRepository;

import org.junit.Test;

public class DriverServiceTest {

	@Mock 
	DriverRepository driverRepository;
	
	@Test
	public void test() {
		DriverService driverService=spy(new DriverService());
		OngoingStubbing thing=  when(driverRepository.findFirstByStatusIgnoreCase(anyString()));
		Driver driver1=new Driver();
		driver1.setCarNumber("abcd");
		driver1.setDriverId(2);
		driver1.setDriverName("name");
		driver1.setStatus("string");
		thing.thenReturn(driver1);
		Driver driver=driverService.findDriverByStatus("string");
		assertEquals(driver,driver1);
		System.out.println("Success!");
		
	}

}
