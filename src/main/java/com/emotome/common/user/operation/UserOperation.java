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
package com.emotome.common.user.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emotome.common.operation.BaseOperation;
import com.emotome.common.response.Response;
import com.emotome.common.user.view.UserView;
import com.emotome.harbor.exception.HarborException;

/**
 * @author Dhruvang.Joshi
 * @since 30/11/2018
 */
public interface UserOperation extends BaseOperation<UserView> {

	/**
	 * Validate users credential, session and device to allow him to login into a
	 * system.
	 * 
	 * @param userView
	 * @param request
	 * @param response
	 * @param isLoginThroughEmail
	 * @return
	 * @throws HarborException
	 */
	Response doLogin(UserView userView, HttpServletRequest request, HttpServletResponse response,
			boolean isLoginThroughEmail) throws HarborException;

	/**
	 * This method is used to remove user's auth token.
	 * 
	 * @param request
	 * @return
	 * @throws HarborException
	 */
	Response doLogout(String session) throws HarborException;

	/**
	 * This method is used to send reset password link.
	 * 
	 * @param userView
	 * @param isLoginThroughEmail
	 * @return
	 * @throws HarborException
	 */
	Response doSendResetLink(UserView userView, boolean isLoginThroughEmail) throws HarborException;

	/**
	 * This method validates User's reset password token.
	 * 
	 * @param token
	 * @return
	 * @throws HarborException
	 */
	Response doResetPasswordVerification(String token) throws HarborException;

	/**
	 * This method is used to reset user's password.
	 * 
	 * @param userView
	 * @return
	 * @throws HarborException
	 */
	Response doResetPassword(UserView userView) throws HarborException;

	/**
	 * This method is used to change user's password.
	 * 
	 * @param userView
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws HarborException
	 */
	Response doChangePassword(UserView userView, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws HarborException;

	/**
	 * This method is used to get islogged in.
	 * 
	 * @return
	 * @throws HarborException
	 */
	Response doIsLoggedIn() throws HarborException;

	/**
	 * this method use to send otp for reset password.
	 * 
	 * @param userView
	 * @return
	 * @throws HarborException
	 */
	Response doSendResetOtp(UserView userView) throws HarborException;

	/**
	 * this method for verify otp.
	 * 
	 * @param userView
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws HarborException
	 */
	Response doVerifyOtp(UserView userView, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws HarborException;

	/**
	 * this method for user registration
	 * 
	 * @param userView
	 * @return
	 * @throws HarborException
	 */
	Response doRegister(UserView userView) throws HarborException;

}