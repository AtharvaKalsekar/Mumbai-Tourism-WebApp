package com.logintrialthree.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.logintrialthree.model.Site;

public interface ISiteService 
{
	public Site findSiteByName(String siteName);
	public void saveSite(Site site, MultipartFile multipartFile);
	public List<Site> getAllSites();
}
