package com.logintrialthree.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logintrialthree.model.Driver;


@Repository("driverRepository")
public interface DriverRepository extends JpaRepository<Driver, Integer>
{
	Driver findFirstByStatusIgnoreCase(String status);
	Driver findFirstByDriverNameIgnoreCase(String driverName);
}
