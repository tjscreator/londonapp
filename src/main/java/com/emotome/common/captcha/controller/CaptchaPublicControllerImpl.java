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

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.emotome.common.aop.AccessLog;
import com.emotome.common.captcha.model.CaptchaModel;
import com.emotome.common.captcha.operation.CaptchaOperation;
import com.emotome.common.enums.ResponseCode;
import com.emotome.common.response.CommonResponse;
import com.emotome.common.response.Response;
import com.emotome.common.setting.model.SettingModel;
import com.emotome.common.util.Constant;
import com.emotome.common.util.CookieUtility;
import com.emotome.common.util.FileUtility;
import com.emotome.common.util.Utility;
import com.emotome.harbor.exception.HarborException;

/**
 * This controller maps all file upload related apis.
 * 
 * @author Dhruvang.Joshi
 * @since 07/12/2017
 */
@Controller
@RequestMapping("/public/captcha")
public class CaptchaPublicControllerImpl implements CaptchaController {

	@Autowired
	CaptchaOperation captchaOperation;

	@Override
	@AccessLog
	public Response generateRegistrationCaptcha(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws HarborException {
		String uuid = Utility.generateUuidWithHash();
		String captcha = setCaptchaCookie(httpServletRequest, httpServletResponse, Constant.REGISTRATION_TOKEN, uuid);
		String captchaFile = FileUtility.createCaptchaImage(captcha, uuid);
		if (captchaFile == null) {
			throw new HarborException(ResponseCode.UNABLE_TO_LOAD_CAPTCHA.getCode(),
					ResponseCode.UNABLE_TO_LOAD_CAPTCHA.getMessage());
		}
		return captchaOperation.doSave(captcha, uuid);
	}

	private String setCaptchaCookie(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			String captchaType, String uuid) {
		String captcha = Utility.generateToken(6).toUpperCase();
		CookieUtility.setCookie(httpServletResponse, captchaType, uuid, 300, httpServletRequest.getContextPath());
		return captcha;
	}

	@Override
	@AccessLog
	public Response downloadCaptcha(@RequestParam(value = "captchaId") String captchaId,
			HttpServletResponse httpServletResponse) throws HarborException {
		if (StringUtils.isBlank(captchaId)) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		CaptchaModel captchaModel = captchaOperation.get(captchaId);
		String filePath = SettingModel.getCaptchaImagePath() + File.separator + captchaModel.getId() + ".png";
		FileUtility.download(filePath, captchaModel.getId() + ".png", httpServletResponse);
		return CommonResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage());
	}
}