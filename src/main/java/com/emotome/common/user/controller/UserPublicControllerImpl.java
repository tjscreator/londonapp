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
package com.emotome.common.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emotome.common.aop.AccessLog;
import com.emotome.common.enums.ResponseCode;
import com.emotome.common.response.Response;
import com.emotome.common.user.operation.UserOperation;
import com.emotome.common.user.view.UserView;
import com.emotome.common.util.Utility;
import com.emotome.common.validation.RegexEnum;
import com.emotome.harbor.exception.HarborException;

/**
 * This controller maps all user related apis.
 * 
 * @author Dhruvang.Joshi
 * @since 24/11/2018
 */
@Controller
@RequestMapping("/public/user")
public class UserPublicControllerImpl implements UserPublicController {

	@Autowired
	private UserOperation userOperation;

	@Override
	@AccessLog
	public Response login(@RequestBody UserView userView, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws HarborException {
		boolean isLoginThroughEmail = validateLoginDetails(userView);
		return userOperation.doLogin(userView, httpServletRequest, httpServletResponse, isLoginThroughEmail);
	}

	private boolean validateLoginDetails(UserView userView) throws HarborException {
		if (StringUtils.isBlank(userView.getLoginId())) {
			throw new HarborException(ResponseCode.INVALID_EMAIL_OR_MOBILE_NUMBER.getCode(),
					ResponseCode.INVALID_EMAIL_OR_MOBILE_NUMBER.getMessage());
		}
		boolean isLoginThroughEmail = false;
		if (Utility.isValidPattern(userView.getLoginId(), RegexEnum.EMAIL.getValue())) {
			isLoginThroughEmail = true;
		}
		if (!isLoginThroughEmail) {
			if (!Utility.isValidPattern(userView.getLoginId(), RegexEnum.PHONE_NUMBER.getValue())) {
				throw new HarborException(ResponseCode.INVALID_EMAIL_OR_MOBILE_NUMBER.getCode(),
						ResponseCode.INVALID_EMAIL_OR_MOBILE_NUMBER.getMessage());
			}
		}
		return isLoginThroughEmail;
	}

	@Override
	@AccessLog
	public Response register(@RequestBody UserView userView) throws HarborException {
		if (userView == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		UserView.isValidRegistrationDetails(userView);
		return userOperation.doRegister(userView);
	}
}
