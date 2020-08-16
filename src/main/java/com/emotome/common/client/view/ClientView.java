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
package com.emotome.common.client.view;

import org.apache.commons.lang3.StringUtils;

import com.emotome.common.enums.ResponseCode;
import com.emotome.common.file.view.FileView;
import com.emotome.common.user.view.UserView;
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
 * This is hospital model which maps hospital table to class.
 * 
 * @author Jaydip
 * @since 22/04/2019
 */
@JsonInclude(Include.NON_NULL)
public class ClientView extends ArchiveView {

	private static final long serialVersionUID = -5764068071467332650L;

	private String name;
	private String countryCode;
	private String mobile;
	private String apiKey;
	private FileView logoFileView;
	private String address;
	private String pincode;
	private IdNameView cityView;
	private IdNameView stateView;
	private IdNameView countryView;
	private UserView userView;
	private boolean isRegistration;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public FileView getLogoFileView() {
		return logoFileView;
	}

	public void setLogoFileView(FileView logoFileView) {
		this.logoFileView = logoFileView;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
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

	public UserView getUserView() {
		return userView;
	}

	public void setUserView(UserView userView) {
		this.userView = userView;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public boolean isRegistration() {
		return isRegistration;
	}

	public void setRegistration(boolean isRegistration) {
		this.isRegistration = isRegistration;
	}

	public String getShortFormOfName() {
		if (this.name != null) {
			String name = this.name.trim().replaceAll(" +", " ");
			if (name.contains(" ")) {
				String tempFirstWord = name.substring(0, name.lastIndexOf(' '));
				String tempSecondWord = name.substring(tempFirstWord.length() + 1, name.length());
				return tempFirstWord.substring(0, 1).toUpperCase() + tempSecondWord.substring(0, 1).toUpperCase();
			} else {
				return name.substring(0, 1).toUpperCase() + name.substring(1, 2).toUpperCase();
			}
		}
		return null;
	}

	public static void isValid(ClientView clientView) throws HarborException {
		Validator.CLIENT_NAME.isValid(new InputField(clientView.getName(), DataType.STRING, 300,
				RegexEnum.ALPHA_NUMERIC_WITH_SPECIFIC_SPECIAL_CHARACTER));
		Validator.USER_MOBILE.isValid(new InputField(clientView.getMobile(), DataType.STRING, RegexEnum.PHONE_NUMBER));
		if (StringUtils.isBlank(clientView.getCountryCode())) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"Country code " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		if (clientView.getLogoFileView() != null) {
			if (clientView.getLogoFileView().getId() == null)
				throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
						"Logo file " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		isValidPlaceDetails(clientView);
		isValidUserDetails(clientView);
	}

	public static void isValidPlaceDetails(ClientView clientView) throws HarborException {
		if (StringUtils.isBlank(clientView.getAddress())) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"Address " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		if (StringUtils.isBlank(clientView.getPincode())) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"Pincode " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		if (clientView.getCityView() == null
				|| (clientView.getCityView() != null && clientView.getCityView().getId() == null)) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"City " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		if (clientView.getStateView() == null
				|| (clientView.getStateView() != null && clientView.getStateView().getId() == null)) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"State " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		if (clientView.getCountryView() == null
				|| (clientView.getCountryView() != null && clientView.getCountryView().getId() == null)) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"Country " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
	}

	public static void isValidUserDetails(ClientView clientView) throws HarborException {
		if (clientView.getUserView() == null) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"User details " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		if (StringUtils.isBlank(clientView.getUserView().getName())) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"User name " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		if (StringUtils.isBlank(clientView.getUserView().getMobile())) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"User mobile " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		if (StringUtils.isBlank(clientView.getUserView().getEmail())) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"User email " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
	}
}