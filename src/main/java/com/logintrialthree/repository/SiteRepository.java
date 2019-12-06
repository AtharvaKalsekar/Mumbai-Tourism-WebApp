package com.logintrialthree.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logintrialthree.model.Site;

@Repository("siteRepository")
public interface SiteRepository extends JpaRepository<Site, Integer>
{
	Site findFirstBySiteNameIgnoreCase(String name);	
}
