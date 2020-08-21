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
package com.tjs.common.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjs.common.aop.AccessLog;
import com.tjs.common.controller.BaseController;
import com.tjs.common.response.Response;
import com.tjs.common.user.view.UserView;
import com.tjs.harbor.exception.HarborException;

/**
 * This controller maps all user related apis.
 * 
 * @author Nirav.Shah
 * @since 11/09/2018
 */
public interface UserPrivateController extends BaseController<UserView> {
	/**
	 * Remove user's current session.
	 * 
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws HarborException
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	@AccessLog
	Response logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws HarborException;

	/**
	 * This method is used to set a new password.
	 * 
	 * @param userView
	 * @return
	 * @throws SpaceezAPIException
	 */
	@RequestMapping(value = "/reset-password", method = RequestMethod.POST)
	@ResponseBody
	Response resetPassword(@RequestBody UserView userView) throws HarborException;

	/**
	 * This method is used to change user's password.
	 * 
	 * @param userView
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws HarborException
	 */
	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
	@ResponseBody
	Response changePassword(@RequestBody UserView userView, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws HarborException;

	/**
	 * This method is used to check session of user.
	 * 
	 * @param token
	 * @return
	 * @throws HarborException
	 */
	@RequestMapping(value = "/isloggedin", method = RequestMethod.GET)
	@ResponseBody
	@AccessLog
	Response isLoggedIn() throws HarborException;

}