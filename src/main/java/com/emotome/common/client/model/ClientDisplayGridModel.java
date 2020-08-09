package com.emotome.common.client.model;

import com.emotome.common.model.Model;

/**
 * This is hospital display grid model which maps hospital table to class.
 * 
 * @author Jaydip
 * @since 25/04/2019
 */
public class ClientDisplayGridModel implements Model {

	private static final long serialVersionUID = -6278686864103038197L;
	private Long id;
	private String name;
	private String email;
	private String mobile;
	private String registrationNumber;
	private String licensekey;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getLicensekey() {
		return licensekey;
	}

	public void setLicensekey(String licensekey) {
		this.licensekey = licensekey;
	}

}
