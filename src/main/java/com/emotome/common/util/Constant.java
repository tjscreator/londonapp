package com.emotome.common.util;

import java.io.File;

/**
 * @author Dhruvang
 *
 */
public interface Constant {

	String USER_SESSION_KEY = "user";
	String SALT_STRING = "#";
	String USER_AGENT = "Mozilla/5.0";
	String url = "https://www.google.com/recaptcha/api/siteverify";
	String DIGITS = "0123456789";
	String HTTP_URL = "http://";
	String SERVER_URL = HTTP_URL + "portal.a-league.org/#/pages/verify/";

	String USER_ROLE = "userrole";
	String DEVICE_TOKEN = "dToken";
	String AUTH_TOKEN = "aToken";
	String HOPITAL_TOKEN = "hToken";
	String AUTH_TOKEN_REMOVED = "_aTokenRemoved";
	String REGISTRATION_TOKEN = "registrationToken";
	String CONTACT_CAPTCHA_TOKEN = "contactToken";
	String URL_FOR_PUBLIC_UPLOAD_IMAGE = File.separator + "harbor" + File.separator + "hospital-logo" + File.separator
			+ "images"; /* Ubuntu=/ Windows= \ */
	String TOTAL_TIME_TAKEN_BY_JOB = "TOTAL_TIME_TAKEN_BY_JOB";
	Double SGST = .09;
	Double CGST = .09;
	Double IGST = .18;
	Double UPDATE_PROCESS_VALUE = 10.0;

}