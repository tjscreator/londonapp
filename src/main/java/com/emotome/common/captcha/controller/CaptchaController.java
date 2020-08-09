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
package com.emotome.common.captcha.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emotome.common.controller.Controller;
import com.emotome.common.response.Response;
import com.emotome.harbor.exception.HarborException;

/**
 * 
 * @author Nirav.Shah
 * @since 17/12/2018
 *
 */
public interface CaptchaController extends Controller {
	/**
	 * This method is used to generate Captcha and store it in session.
	 * 
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws HarborException
	 */
	@RequestMapping(value = "/generate-registration-captcha", method = RequestMethod.GET)
	@ResponseBody
	Response generateRegistrationCaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws HarborException;

	/**
	 * This method is used to download generated captcha image.
	 * @param captchaId
	 * @param httpServletResponse
	 * @return
	 * @throws HarborException
	 */
	@RequestMapping(value = "/download-captcha", method = RequestMethod.GET)
	@ResponseBody
	Response downloadCaptcha(@RequestParam(value = "captchaId") String captchaId, HttpServletResponse httpServletResponse)
			throws HarborException;
}