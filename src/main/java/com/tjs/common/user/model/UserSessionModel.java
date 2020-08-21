/*******************************************************************************
 * Copyright -2018 @Emotome
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.tjs.common.user.model;

import com.tjs.common.model.IdentifierModel;

/**
 * This is User Session model which maps user session table to class.
 * 
 * @author Vishwa.Shah
 * @since 08/02/2018
 *
 */
public class UserSessionModel extends IdentifierModel {
	private static final long serialVersionUID = -2950420391918498866L;

	private String session;
	private Long createDate;
	private Long updateDate;
	private String browser;
	private String os;
	private String ip;
	private String deviceCookie;
	private Long cookieCreateDate;
	private UserModel userModel;
	private boolean twoFactorSession;
	private boolean resetPasswordSession;
	private boolean otpSession;

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public Long getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Long updateDate) {
		this.updateDate = updateDate;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDeviceCookie() {
		return deviceCookie;
	}

	public void setDeviceCookie(String deviceCookie) {
		this.deviceCookie = deviceCookie;
	}

	public Long getCookieCreateDate() {
		return cookieCreateDate;
	}

	public void setCookieCreateDate(Long cookieCreateDate) {
		this.cookieCreateDate = cookieCreateDate;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public boolean isTwoFactorSession() {
		return twoFactorSession;
	}

	public void setTwoFactorSession(boolean twoFactorSession) {
		this.twoFactorSession = twoFactorSession;
	}

	public boolean isResetPasswordSession() {
		return resetPasswordSession;
	}

	public void setResetPasswordSession(boolean resetPasswordSession) {
		this.resetPasswordSession = resetPasswordSession;
	}

	public boolean isOtpSession() {
		return otpSession;
	}

	public void setOtpSession(boolean otpSession) {
		this.otpSession = otpSession;
	}

}