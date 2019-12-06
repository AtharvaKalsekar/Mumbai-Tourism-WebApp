package com.logintrialthree.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.logintrialthree.model.Site;
import com.logintrialthree.repository.SiteRepository;

@Service("siteService")
public class SiteService implements ISiteService 
{
	@Autowired
	private SiteRepository siteRepository;
	
	@Override
	public Site findSiteByName(String siteName) {
	
		return siteRepository.findFirstBySiteNameIgnoreCase(siteName);
	}

	@Override
	public void saveSite(Site site, MultipartFile multipartFile) 
	{
		String encodedString = null;
		try 
		{
			encodedString = new String(Base64.encode(multipartFile.getBytes()));
		} 
		catch (IOException e) 
		{
			encodedString = "image_absent";
			e.printStackTrace();
		}
		site.setSitePicture(encodedString);
		siteRepository.save(site);
	}

	@Override
	public List<Site> getAllSites() 
	{
		return siteRepository.findAll();
	}

}
