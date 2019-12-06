package com.logintrialthree.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trans")
public class Trans 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int transId;
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "site_id")
	private int siteId;
	
	@Column(name = "site_name")
	private String siteName;
	
	@Column(name = "payment")
	private float payment;
	
	@Column(name = "driver_id")
	private int driverId;
	
	@Column(name = "driver_name")
	private String driverName;
	
	@Column(name = "car_number")
	private String carNumber;
	
	@Column(name = "booking_date")
	private String bookingDate;

	public int getTransId() {
		return transId;
	}

	public void setTransId(int transId) {
		this.transId = transId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public float getPayment() {
		return payment;
	}

	public void setPayment(float payment) {
		this.payment = payment;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	

}
