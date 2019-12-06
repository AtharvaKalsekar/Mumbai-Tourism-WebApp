package com.logintrialthree.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logintrialthree.model.Driver;
import com.logintrialthree.model.Site;
import com.logintrialthree.model.Trans;
import com.logintrialthree.model.User;
import com.logintrialthree.repository.TransRepository;

@Service("transService")
public class TransService implements ITransService 
{
	@Autowired
	private TransRepository transRepository;
	
	@Override
	public List<Trans> findTransByUserId(int userId) {
		return transRepository.findByUserId(userId);
	}

	@Override
	public void saveTrans(User user, Driver driver, Site site) 
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		String bookingDate = dtf.format(now);
		Trans trans = new Trans();
		//trans.setTransId(0);
		trans.setUserId(user.getId());
		trans.setSiteId(site.getSite_id());
		trans.setSiteName(site.getSiteName());
		trans.setPayment(site.getSiteCost());
		trans.setDriverId(driver.getDriverId());
		trans.setDriverName(driver.getDriverName());
		trans.setCarNumber(driver.getCarNumber());
		trans.setBookingDate(bookingDate);
		transRepository.save(trans);
	}

}
