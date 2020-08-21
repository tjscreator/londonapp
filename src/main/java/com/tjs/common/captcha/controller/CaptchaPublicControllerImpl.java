package com.tjs.common.captcha.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjs.common.aop.AccessLog;
import com.tjs.common.captcha.model.CaptchaModel;
import com.tjs.common.captcha.operation.CaptchaOperation;
import com.tjs.common.enums.ResponseCode;
import com.tjs.common.response.CommonResponse;
import com.tjs.common.response.Response;
import com.tjs.common.setting.model.SettingModel;
import com.tjs.common.util.Constant;
import com.tjs.common.util.CookieUtility;
import com.tjs.common.util.FileUtility;
import com.tjs.common.util.Utility;
import com.tjs.harbor.exception.HarborException;

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