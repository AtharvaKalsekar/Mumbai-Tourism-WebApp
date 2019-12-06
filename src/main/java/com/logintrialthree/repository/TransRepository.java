package com.logintrialthree.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logintrialthree.model.Trans;

@Repository("transRepository")
public interface TransRepository extends JpaRepository<Trans, Integer> 
{
	List<Trans> findByUserId(int userId);
	
}
