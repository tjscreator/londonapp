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

/**
 * This is Setting config class which contains static keys.
 * 
 * @author Nirav
 * @since 24/11/2018
 *
 */
public interface SettingConfig {
	String FILE_PATH = "FILE_PATH";
	String TWO_FACTOR_AUTHENTICATION_ENABLED = "TWO_FACTOR_AUTHENTICATION_ENABLED";
	String DEVICE_COOKIE_TIME_IN_SECONDS = "DEVICE_COOKIE_TIME_IN_SECONDS";
	String SESSION_INACTIVE_TIME_IN_MINUTES = "SESSION_INACTIVE_TIME_IN_MINUTES";
	String MAX_ALLOWED_DEVICE = "MAX_ALLOWED_DEVICE";
	String DEFAULT_TIME_ZONE_ID = "DEFAULT_TIME_ZONE_ID";
	String RESET_PASSWORD_TOKEN_VALID_MINUTES = "RESET_PASSWORD_TOKEN_VALID_MINUTES";
	String PASSWORD_USED_VALIDATION_ENABLED = "PASSWORD_USED_VALIDATION_ENABLED";
	String MAX_PASSWORD_STORE_COUNT_PER_USER = "MAX_PASSWORD_STORE_COUNT_PER_USER";
	String RESET_PASSWORD_SESSION_VALID_MINUTES = "RESET_PASSWORD_SESSION_VALID_MINUTES";
	String CAPTCHA_IMAGE_PATH = "CAPTCHA_IMAGE_PATH";
	String WEBSITE_URL = "WEBSITE_URL";
}
