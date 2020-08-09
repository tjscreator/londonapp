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
package com.emotome.common.user.view;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.emotome.common.captcha.view.CaptchaView;
import com.emotome.common.client.view.ClientView;
import com.emotome.common.enums.ResponseCode;
import com.emotome.common.validation.DataType;
import com.emotome.common.validation.InputField;
import com.emotome.common.validation.RegexEnum;
import com.emotome.common.validation.Validator;
import com.emotome.common.view.ArchiveView;
import com.emotome.common.view.IdNameView;
import com.emotome.harbor.exception.HarborException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * This class is used to represent user object in json/in hospital response.
 * 
 * @author Vishwa.Shah
 * @since 08/02/2018
 */
@JsonInclude(Include.NON_NULL)
public class UserView extends ArchiveView {

	private static final long serialVersionUID = -4444717308537621033L;

	private String name;
	private String email;
	private String countryCode;
	private String mobile;
	private String password;
	private String confirmPassword;
	private String oldPassword;
	private String token;
	private CaptchaView captchaView;
	private List<RoleView> roleViews;
	private Boolean completeProfile;
	private boolean hasLoggedIn;
	private Boolean loggedIn;
	private String shortName;
	private GroupView groupView;
	private String pincode;
	private String address;
	private String loginId;
	private Integer otp;
	private List<ClientView> clientViews;
	private IdNameView cityView;
	private IdNameView stateView;
	private IdNameView countryView;

	public UserView() {

	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public CaptchaView getCaptchaView() {
		return captchaView;
	}

	public void setCaptchaView(CaptchaView captchaView) {
		this.captchaView = captchaView;
	}

	public Boolean getCompleteProfile() {
		return completeProfile;
	}

	public void setCompleteProfile(Boolean completeProfile) {
		this.completeProfile = completeProfile;
	}

	public boolean isHasLoggedIn() {
		return hasLoggedIn;
	}

	public void setHasLoggedIn(boolean hasLoggedIn) {
		this.hasLoggedIn = hasLoggedIn;
	}

	public Boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getShortName() {
		return shortName;
	}

	public GroupView getGroupView() {
		return groupView;
	}

	public void setGroupView(GroupView groupView) {
		this.groupView = groupView;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		if (!StringUtils.isBlank(loginId)) {
			this.loginId = loginId.trim();
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (!StringUtils.isBlank(email)) {
			this.email = email.toLowerCase().trim();
		} else {
			this.email = email;
		}
	}

	public static String getShortName(String name) {
		if (name != null) {
			if (name.contains(" ")) {
				String names[] = name.split(" ");
				String firstname = names[0].trim().replaceAll(" +", " ");
				String lastname = names[1].trim().replaceAll(" +", " ");
				String tempFirstWord = firstname.substring(0, 1);
				String tempSecondWord = lastname.substring(0, 1);
				return tempFirstWord.toUpperCase() + tempSecondWord.toUpperCase();
			} else {
				String tempFirstWord = name.substring(0, 1);
				String tempSecondWord = name.substring(1, 2);
				return tempFirstWord.toUpperCase() + tempSecondWord.toUpperCase();
			}
		}
		return null;
	}

	public List<RoleView> getRoleViews() {
		return roleViews;
	}

	public void setRoleViews(List<RoleView> roleViews) {
		this.roleViews = roleViews;
	}

	public Integer getOtp() {
		return otp;
	}

	public void setOtp(Integer otp) {
		this.otp = otp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ClientView> getClientViews() {
		return clientViews;
	}

	public void setClientViews(List<ClientView> clientViews) {
		this.clientViews = clientViews;
	}

	public IdNameView getCityView() {
		return cityView;
	}

	public void setCityView(IdNameView cityView) {
		this.cityView = cityView;
	}

	public IdNameView getStateView() {
		return stateView;
	}

	public void setStateView(IdNameView stateView) {
		this.stateView = stateView;
	}

	public IdNameView getCountryView() {
		return countryView;
	}

	public void setCountryView(IdNameView countryView) {
		this.countryView = countryView;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public static void isValidRegistrationDetails(UserView userView) throws HarborException {
		Validator.USER_NAME
				.isValid(new InputField(userView.getName(), DataType.STRING, 200, RegexEnum.ALPHABETS_WITH_SPACE));
		if (StringUtils.isBlank(userView.getCountryCode())) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					ResponseCode.DATA_IS_MISSING.getMessage());
		}
		Validator.USER_MOBILE.isValid(new InputField(userView.getMobile(), DataType.STRING, RegexEnum.PHONE_NUMBER));
		if (!StringUtils.isBlank(userView.getEmail())) {
			Validator.USER_EMAIL_ID.isValid(new InputField(userView.getEmail(), DataType.STRING, 200, RegexEnum.EMAIL));
		}
		if (StringUtils.isBlank(userView.getPassword())) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"Password " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		if (StringUtils.isBlank(userView.getConfirmPassword())) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"Confirm Password " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		if (!userView.getPassword().equals(userView.getConfirmPassword())) {
			throw new HarborException(ResponseCode.PASSWORD_AND_CONFIRM_NOT_MATCH.getCode(),
					ResponseCode.PASSWORD_AND_CONFIRM_NOT_MATCH.getMessage());
		}
	}
}
