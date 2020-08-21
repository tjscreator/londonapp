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
package com.tjs.common.setting.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.tjs.common.model.IdentifierModel;
import com.tjs.common.validation.DataType;

/**
 * This is Setting model which maps setting list table to class.
 * 
 * @author Nirav
 * @since 24/11/2018
 *
 */
public class SettingModel extends IdentifierModel {

	private static final long serialVersionUID = 6653648434546572167L;
	private String key;
	private Integer dataType;
	private String value;

	private static Map<String, String> MAP = new ConcurrentHashMap<>();

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public DataType getDataType() {
		return DataType.getFromId(this.dataType);
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType.getId();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static Map<String, String> getMAP() {
		return MAP;
	}

	public static void setMAP(Map<String, String> map) {
		MAP = map;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	public static String getFilePath() {
		return getValue(SettingConfig.FILE_PATH);
	}

	public static String getTwoFactorAuthenticationEnable() {
		return getValue(SettingConfig.TWO_FACTOR_AUTHENTICATION_ENABLED);
	}

	public static String getDeviceCookieTimeInSeconds() {
		return getValue(SettingConfig.DEVICE_COOKIE_TIME_IN_SECONDS);
	}

	public static String getSessionInactiveTimeInMinutes() {
		return getValue(SettingConfig.SESSION_INACTIVE_TIME_IN_MINUTES);
	}

	public static String getMaxAllowedDevice() {
		return getValue(SettingConfig.MAX_ALLOWED_DEVICE);
	}

	public static String getDefaultTimeZoneId() {
		return getValue(SettingConfig.DEFAULT_TIME_ZONE_ID);
	}

	public static String getResetPasswordTokenValidMinutes() {
		return getValue(SettingConfig.RESET_PASSWORD_SESSION_VALID_MINUTES);
	}

	public static String getPasswordUsedValidationEnabled() {
		return getValue(SettingConfig.PASSWORD_USED_VALIDATION_ENABLED);
	}

	public static String getMaxPasswordStoreCountPerUser() {
		return getValue(SettingConfig.MAX_PASSWORD_STORE_COUNT_PER_USER);
	}

	public static String getResetPasswordSessionValidMinutes() {
		return getValue(SettingConfig.RESET_PASSWORD_SESSION_VALID_MINUTES);
	}

	public static String getCaptchaImagePath() {
		return getValue(SettingConfig.CAPTCHA_IMAGE_PATH);
	}

	public static String getWebsiteURL() {
		return getValue(SettingConfig.WEBSITE_URL);
	}

	private static String getValue(String key) {
		return MAP.get(key);
	}

	public static void updateValue(String key, String value) {
		MAP.put(key, value);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SettingModel other = (SettingModel) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}
}
