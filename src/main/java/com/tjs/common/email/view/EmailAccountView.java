package com.tjs.common.email.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tjs.common.client.view.ClientView;
import com.tjs.common.view.ArchiveView;
import com.tjs.common.view.KeyValueView;

/**
 * This Class is used to represent email object in json/in hospital response
 * @author Nisha.Panchal
 * @Since 12/07/2018
 */
@JsonInclude(Include.NON_NULL)
public class EmailAccountView extends ArchiveView{
	
	private static final long serialVersionUID = 2396198127017769592L;
	
	private String name;
	private String host;
	private Long port;
	private String userName;
	private String password;
	private String replyToEmail;
	private String emailFrom;
	private Long ratePerHour;
	private Long updateRatePerHour;
	private Long dateRatePerHour;
	private Long ratePerDay;
	private Long updateRatePerDay;
	private Long dateRatePerDay;
	private KeyValueView authenticationMethod;
	private KeyValueView authenticationSecurity;
	private Long timeOut;	
	private ClientView hospitalView;
	
	
	public ClientView getHospitalView() {
		return hospitalView;
	}
	public void setHospitalView(ClientView hospitalView) {
		this.hospitalView = hospitalView;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Long getPort() {
		return port;
	}
	public void setPort(Long port) {
		this.port = port;
	}
	public String getUsername() {
		return userName;
	}
	public void setUsername(String username) {
		this.userName = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getReplyToEmail() {
		return replyToEmail;
	}
	public void setReplyToEmail(String replyToEmail) {
		this.replyToEmail = replyToEmail;
	}
	public String getEmailFrom() {
		return emailFrom;
	}
	public void setEmailFrom(String emailForm) {
		this.emailFrom = emailForm;
	}
	public Long getRatePerHour() {
		return ratePerHour;
	}
	public void setRatePerHour(Long ratePerHour) {
		this.ratePerHour = ratePerHour;
	}
	public Long getUpdateRatePerHour() {
		return updateRatePerHour;
	}
	public void setUpdateRatePerHour(Long updateRatePerHour) {
		this.updateRatePerHour = updateRatePerHour;
	}
	public Long getDateRatePerHour() {
		return dateRatePerHour;
	}
	public void setDateRatePerHour(Long dateRatePerHour) {
		this.dateRatePerHour = dateRatePerHour;
	}
	public Long getRatePerDay() {
		return ratePerDay;
	}
	public void setRatePerDay(Long ratePerDay) {
		this.ratePerDay = ratePerDay;
	}
	public Long getUpdateRatePerDay() {
		return updateRatePerDay;
	}
	public void setUpdateRatePerDay(Long updateRatePerDay) {
		this.updateRatePerDay = updateRatePerDay;
	}
	public Long getDateRatePerDay() {
		return dateRatePerDay;
	}
	public void setDateRatePerDay(Long dateRatePerDay) {
		this.dateRatePerDay = dateRatePerDay;
	}
	public KeyValueView getAuthenticationMethod() {
		return authenticationMethod;
	}
	public void setAuthenticationMethod(KeyValueView authenticationMethod) {
		this.authenticationMethod = authenticationMethod;
	}
	public KeyValueView getAuthenticationSecurity() {
		return authenticationSecurity;
	}
	public void setAuthenticationSecurity(KeyValueView authenticationSecurity) {
		this.authenticationSecurity = authenticationSecurity;
	}
	public Long getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(Long timeOut) {
		this.timeOut = timeOut;
	}
}