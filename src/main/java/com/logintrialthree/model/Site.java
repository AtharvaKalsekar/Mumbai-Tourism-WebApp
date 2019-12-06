package com.logintrialthree.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "site")
public class Site 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int site_id;
	
	@Column(name = "site_name")
	private String siteName;
	
	@Column(name = "site_address")
	private String siteAddress;
	
	@Column(name = "site_description")
	private String siteDescription;
	
	@Column(name = "site_cost")
	private float siteCost;
	
	@Column(name = "site_picture")
	private String sitePicture;

	public int getSite_id() {
		return site_id;
	}

	public void setSite_id(int site_id) {
		this.site_id = site_id;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteAddress() {
		return siteAddress;
	}

	public void setSiteAddress(String siteAddress) {
		this.siteAddress = siteAddress;
	}

	public String getSiteDescription() {
		return siteDescription;
	}

	public void setSiteDescription(String siteDescription) {
		this.siteDescription = siteDescription;
	}

	public float getSiteCost() {
		return siteCost;
	}

	public void setSiteCost(float siteCost) {
		this.siteCost = siteCost;
	}

	public String getSitePicture() {
		return sitePicture;
	}

	public void setSitePicture(String sitePicture) {
		this.sitePicture = sitePicture;
	}
}
