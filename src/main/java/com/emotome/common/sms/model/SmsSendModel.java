package com.emotome.common.sms.model;

public class SmsSendModel {

	private String smsContent;
	private String groupId;
	private String routeId;
	private String mobileNumbers;
	private String senderId;
	private String signature;
	private String smsContentType;
	// private String authKey;

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getMobileNumbers() {
		return mobileNumbers;
	}

	public void setMobileNumbers(String mobileNumbers) {
		this.mobileNumbers = mobileNumbers;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getSmsContentType() {
		return smsContentType;
	}

	public void setSmsContentType(String smsContentType) {
		this.smsContentType = smsContentType;
	}

	/*
	 * public String getAuthKey() { return authKey; }
	 * 
	 * public void setAuthKey(String authKey) { this.authKey = authKey; }
	 */

}
