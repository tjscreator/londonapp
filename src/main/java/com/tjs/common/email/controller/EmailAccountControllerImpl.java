package com.tjs.common.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjs.common.aop.AccessLog;
import com.tjs.common.controller.AbstractController;
import com.tjs.common.email.operation.EmailAccountOperation;
import com.tjs.common.email.view.EmailAccountView;
import com.tjs.common.enums.ResponseCode;
import com.tjs.common.operation.BaseOperation;
import com.tjs.common.response.Response;
import com.tjs.common.validation.DataType;
import com.tjs.common.validation.InputField;
import com.tjs.common.validation.RegexEnum;
import com.tjs.common.validation.Validator;
import com.tjs.harbor.email.enums.EmailAuthenticationMethod;
import com.tjs.harbor.email.enums.EmailAuthenticationSecurity;
import com.tjs.harbor.exception.HarborException;

/**
 * This Controller Maps all Email Account related Apis
 * 
 * @author Nisha.Panchal
 * @since 17/07/2018
 *
 */
@Controller
@RequestMapping("/emailAccount")
public class EmailAccountControllerImpl extends AbstractController<EmailAccountView> implements EmailAccountController {

	@Autowired
	EmailAccountOperation emailAccountOperation;

	@Override
	public BaseOperation<EmailAccountView> getOperation() {
		return emailAccountOperation;
	}

	@Override
	public Response add() throws HarborException {
		throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
	}

	@Override
	public Response search(@RequestBody EmailAccountView emailAccountView, Integer start, Integer recordSize)
			throws HarborException {
		return emailAccountOperation.doSearch(emailAccountView, start, recordSize);
	}

	@Override
	public void isValidSaveData(EmailAccountView emailAccountView) throws HarborException {
		Validator.EMAIL_ACCOUNT_NAME.isValid(new InputField(emailAccountView.getName(), DataType.STRING, 100));
		if (emailAccountView.getPort() == null) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"Port " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		Validator.USER_NAME.isValid(new InputField(emailAccountView.getUsername(), DataType.STRING, 100, RegexEnum.EMAIL));
		Validator.USER_PASSWORD.isValid(new InputField(emailAccountView.getPassword(), DataType.STRING, 500));

		Validator.REPLY_TO_EMAIL
				.isValid(new InputField(emailAccountView.getReplyToEmail(), DataType.STRING, 100, RegexEnum.EMAIL));
		Validator.EMAIL_FROM
				.isValid(new InputField(emailAccountView.getEmailFrom(), DataType.STRING, 500, RegexEnum.EMAIL));
		if (emailAccountView.getRatePerHour() == null) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"Rate Per Hour " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		if (emailAccountView.getUpdateRatePerHour() == null) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"Update Rate Per Hour " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		if (emailAccountView.getRatePerDay() == null) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"Rate Per Day " + ResponseCode.DATA_IS_MISSING.getMessage());
		}

		if (emailAccountView.getUpdateRatePerDay() == null) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"Update Rate Per Day " + ResponseCode.DATA_IS_MISSING.getMessage());
		}

		if (emailAccountView.getAuthenticationMethod() == null
				|| emailAccountView.getAuthenticationMethod().getKey() == null) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"Authentication Method " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		if (EmailAuthenticationMethod
				.fromId(Integer.parseInt(String.valueOf(emailAccountView.getAuthenticationMethod().getKey()))) == null) {
			throw new HarborException(ResponseCode.DATA_IS_INVALID.getCode(),
					"Authentication Method " + ResponseCode.DATA_IS_INVALID.getMessage());
		}
		if (emailAccountView.getAuthenticationSecurity() == null
				|| emailAccountView.getAuthenticationSecurity().getKey() == null) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"Authentication Security " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		if (EmailAuthenticationSecurity.fromId(
				Integer.parseInt(String.valueOf(emailAccountView.getAuthenticationSecurity().getKey()))) == null) {
			throw new HarborException(ResponseCode.DATA_IS_INVALID.getCode(),
					"Authentication Security " + ResponseCode.DATA_IS_INVALID.getMessage());
		}
		if (emailAccountView.getTimeOut() == null) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"Time Out " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
	}

	@ResponseBody
	@AccessLog
	public Response update(@RequestBody EmailAccountView emailAccountView) throws HarborException {
		if (emailAccountView == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
		}
		if (emailAccountView.getId() == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
		}
		isValidSaveData(emailAccountView);
		return getOperation().doUpdate(emailAccountView);
	}
}