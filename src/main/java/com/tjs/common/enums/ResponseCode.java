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
package com.tjs.common.enums;

import java.io.Serializable;

/**
 * This is used to give response code & message to hospital request.
 * 
 * @author Core team.
 * @version 1.0
 * @since 21/09/2016
 */
public enum ResponseCode implements Serializable {

	SUCCESSFUL(1000, "Successful"), SAVE_SUCCESSFULLY(1001, "Data saved successfully."),
	UPDATE_SUCCESSFULLY(1002, "Data updated successfully."), DELETE_SUCCESSFULLY(1003, "Data deleted successfully."),
	ACTIVATION_SUCCESSFUL(1004, "Your account has been activated successfully."),
	CHANGE_PASSWORD_SUCCESSFUL(1005, "You have changed your password successfully. Please login into system using it."),
	FORGET_PASSWORD_SUCCESSFUL(1006,
			"Your request to change the password has been initiated. Please check your registered email id for the same."),
	RESET_PASSWORD_SUCCESSFUL(1007, "You have successfully reset your password. Please login into system using it."),
	FORGET_PASSWORD_VERIFICATION_SUCCESSFUL(1008,
			"Your token for forget password verified successfully, You can now change your password."),
	ALREDAY_LOGGED_IN(1009, "You are already logged in"),
	LOGGED_OUT_SUCCESSFUL(1010, "You have successfully logged out."), OTP_SENT(1011, "Otp sent"),
	REGISTER_SUCCESSFULLY_USERID_PASSWORD_WOULD_BE_MESSEGED(1012,
			"Registration is successful, you will get your login details along with the password on your registered mobile number"),
	// Any code above 2000 is an error code.
	INTERNAL_SERVER_ERROR(2000, "Oops, Something went wrong! Please try again."),
	INVALID_REQUEST(2001, "Invalid request."), DATA_IS_MISSING(2002, "is mandatory."),
	MAX_LENGTH_EXCEED(2003, "characters are allowed"), INVALID_JSON(2004, "Invalid json format."),
	NO_DATA_FOUND(2005, "No data found."), UNABLE_TO_UPLOAD_FILE(2006, "Unable to upload a file."),
	UNABLE_TO_DOWNLOAD_FILE(2007, "Unable to download a file."), DELETED_USER(2008, "Your account had been deleted."),
	PLEASE_VERIFY_YOUR_ACCOUNT(2009, "Please verify your account to continue."),
	DUPLICATE_EMAIL_USER(2010, "You are already registered."), INVALID_TOKEN(2011, "Invalid Token."),
	EXPIRED_TOKEN(2012, "Link is expired or have already been used"),
	DUPLICATE_PASSWORD_USER(2013,
			"The password entered is one of your last used password.Please provide a new password."),
	ALREADY_EXIST(2014, "already exists."), PASSWORD_MUST_NOT_SAME(2015, "Password must not be same."),
	UNABLE_TO_MAKE_TCP_CONNECTION(2016, "Unable to connect."),
	UNABLE_TO_WRITE_IN_TCP_CONNECTION(2017, "Unable to write."), DATA_IS_INVALID(2018, "is invalid."),
	UNABLE_TO_CREATE_THUMBNAIL(2019, "Unable to create thumbnail."),
	FILE_EXIST_WITH_SAME_NAME(2020, "File exist with same name."), USED_TOKEN(2021, "Used Token."),
	VALIDATE_NEW_DEVICE(2022, " Login detected from a new device."),
	NO_SESSION(2023, "Sorry, your session timed out after a long time of inactivity.Please sign in again."),
	UNAUTHORIZED_ACCESS(2024, "You don't have a permission to access it."),
	LINK_EXPIRED(2025, "Activation link is expired or have already been used"),
	INVALID_CAPTCHA(2026, "Captcha is invalid."),
	INVALID_EMAIL(2027, "You haven't created your account with given email id."),
	ALREADY_SUBMITTED(2028, "already submitted"),
	INVALID_FILE_FORMAT(2029, "Invalid file format."), EXCEL_FILE_IS_EMPTY(2030, "Excel file is empty."),
	AUTHENTICATION_REQUIRED(2031, "Authentication is required to access requested resource."),
	CURRENT_PASSWORD_IS_INVALID(2032, "Incorrect Password."), FILE_NOT_FOUND(2033, "File not found."),
	UNABLE_TO_CREATE_ZIP(2034, "Unable to create a zip file."), UNABLE_TO_LOAD_CAPTCHA(2035, "Captcha is not loaded."),
	PASSWORD_NOT_MATCH(2036, "New and Confirm password do not match."),
	PASSWORD_AND_CONFIRM_NOT_MATCH(2037, "Password and Confirm password do not match."),
	CAN_NOT_DELETE(2038, "You can't delete"),
	APP_KEY_IS_NOT_PRESENT(2039, "App key is not present"), OTP_INVALID(2040, "OTP Invalid"),
	OTP_EXPIRED(2041, "OTP Expired"),
	INVALID_EMAIL_OR_MOBILE_NUMBER(2042, "You haven't created your account with given email-id/mobile number."),
	INVALID_LOGINID_OR_PASSWORD(2043, "Invalid login-id or password.");
	private final int code;
	private final String message;

	ResponseCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	/**
	 * This methods is used to fetch Enum base on given id.
	 * 
	 * @param codeenum key
	 * @return ResponseCode enum
	 */
	public static ResponseCode fromId(int code) {
		for (ResponseCode responseCode : values()) {
			if (responseCode.code == code) {
				return responseCode;
			}
		}
		return null;
	}
}