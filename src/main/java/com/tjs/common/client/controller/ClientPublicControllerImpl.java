package com.tjs.common.client.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjs.common.aop.AccessLog;
import com.tjs.common.client.operation.ClientOperation;
import com.tjs.common.client.view.ClientView;
import com.tjs.common.enums.ResponseCode;
import com.tjs.common.logger.LoggerService;
import com.tjs.common.response.Response;
import com.tjs.harbor.exception.HarborException;

/**
 * This controller maps all client related public apis.
 * 
 * @author Jaydip
 * @since 10/08/2020
 */
@Controller
@RequestMapping("/public/client")
public class ClientPublicControllerImpl implements ClientPublicController {

	@Autowired
	private ClientOperation clientOperation;

	@Override
	@AccessLog
	public Response register(@RequestBody ClientView clientView) throws HarborException {
		if (clientView == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		ClientView.isValid(clientView);
		clientView.setRegistration(true);
		try {
			return clientOperation.doSave(clientView);
		} catch (DataIntegrityViolationException dataIntegrityViolationException) {
			LoggerService.exception(dataIntegrityViolationException);
			throw new HarborException(ResponseCode.ALREADY_EXIST.getCode(), "Client name already exists.");
		} catch (ConstraintViolationException constraintViolationException) {
			LoggerService.exception(constraintViolationException);
			throw new HarborException(ResponseCode.ALREADY_EXIST.getCode(), "Client name already exists.");
		}

	}

}
