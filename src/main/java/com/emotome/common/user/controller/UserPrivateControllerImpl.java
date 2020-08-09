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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.emotome.common.aop.AccessLog;
import com.emotome.common.aop.Authorization;
import com.emotome.common.controller.AbstractController;
import com.emotome.common.enums.ResponseCode;
import com.emotome.common.operation.BaseOperation;
import com.emotome.common.response.CommonResponse;
import com.emotome.common.response.Response;
import com.emotome.common.threadlocal.Auditor;
import com.emotome.common.user.enums.ModuleEnum;
import com.emotome.common.user.enums.RightsEnum;
import com.emotome.common.user.operation.UserOperation;
import com.emotome.common.user.view.UserView;
import com.emotome.common.util.Constant;
import com.emotome.common.util.CookieUtility;
import com.emotome.common.validation.DataType;
import com.emotome.common.validation.InputField;
import com.emotome.common.validation.Validator;
import com.emotome.harbor.exception.HarborException;

/**
 * This controller maps all user related apis.
 * 
 * @author Dhruvang.Joshi
 * @since 24/11/2018
 */
@Controller
@RequestMapping("/private/user")
public class UserPrivateControllerImpl extends AbstractController<UserView> implements UserPrivateController {

	@Autowired
	private UserOperation userOperation;

	@Override
	public BaseOperation<UserView> getOperation() {
		return userOperation;
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.USER, rights = RightsEnum.ADD)
	public Response add() throws HarborException {
		return CommonResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage());
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.USER, rights = RightsEnum.ADD)
	public Response save(@RequestBody UserView userView) throws HarborException {
		throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.USER, rights = RightsEnum.UPDATE)
	public Response update(@RequestBody UserView userView) throws HarborException {
		if (userView == null || (userView != null && userView.getId() == null)) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		return userOperation.doUpdate(userView);
	}

	@Override
	public void isValidSaveData(UserView userView) throws HarborException {
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.USER, rights = RightsEnum.UPDATE)
	public Response edit(@RequestParam(name = "id", required = true) Long id) throws HarborException {
		if (id == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		return userOperation.doEdit(id);
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.USER, rights = RightsEnum.DELETE)
	public Response delete(@RequestParam(name = "id", required = true) Long id) throws HarborException {
		if (id == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		if (!Auditor.getAuditor().getId().equals(id)) {
			throw new HarborException(ResponseCode.CAN_NOT_DELETE.getCode(), ResponseCode.CAN_NOT_DELETE.getMessage());
		}
		return userOperation.doDelete(id);
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.USER, rights = RightsEnum.LIST)
	public Response search(@RequestBody UserView userView, @RequestParam(name = "start", required = true) Integer start,
			@RequestParam(name = "recordSize", required = true) Integer recordSize) throws HarborException {
		return userOperation.doSearch(userView, start, recordSize);
	}

	@Override
	@AccessLog
	public Response logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws HarborException {
		Cookie cookies[] = httpServletRequest.getCookies();
		if (cookies == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		String session = null;
		for (Cookie cookie : cookies) {
			if (Constant.AUTH_TOKEN.equals(cookie.getName())) {
				session = cookie.getValue();
			}
		}
		if (StringUtils.isBlank(session)) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		CookieUtility.setCookie(httpServletResponse, Constant.AUTH_TOKEN, session, 0,
				httpServletRequest.getContextPath());
		return userOperation.doLogout(session);
	}

	@Override
	@AccessLog
	public Response changePassword(@RequestBody UserView userView, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws HarborException {
		validateResetPasswordRequest(userView);
		Validator.USER_PASSWORD.isValid(new InputField(userView.getOldPassword(), DataType.STRING, 16));
		return userOperation.doChangePassword(userView, httpServletRequest, httpServletResponse);
	}

	private void validateResetPasswordRequest(UserView userView) throws HarborException {
		if (userView == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		Validator.USER_PASSWORD.isValid(new InputField(userView.getPassword(), DataType.STRING, 16));
		Validator.USER_PASSWORD.isValid(new InputField(userView.getConfirmPassword(), DataType.STRING, 16));
		if (!userView.getPassword().equals(userView.getConfirmPassword())) {
			throw new HarborException(ResponseCode.PASSWORD_NOT_MATCH.getCode(),
					ResponseCode.PASSWORD_NOT_MATCH.getMessage());
		}
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.USER, rights = RightsEnum.LIST)
	public Response displayGrid(@RequestParam(name = "start", required = true) Integer start,
			@RequestParam(name = "recordSize", required = true) Integer recordSize) throws HarborException {
		throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.USER, rights = RightsEnum.ACTIVATION)
	public Response activeInActive(@RequestParam(name = "id", required = true) Long id) throws HarborException {
		throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
	}

	@Override
	@AccessLog
	public Response isLoggedIn() throws HarborException {
		return userOperation.doIsLoggedIn();
	}

}