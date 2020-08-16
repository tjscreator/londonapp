package com.emotome.common.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emotome.common.aop.AccessLog;
import com.emotome.common.client.operation.ClientOperation;
import com.emotome.common.client.view.ClientView;
import com.emotome.common.enums.ResponseCode;
import com.emotome.common.response.Response;
import com.emotome.harbor.exception.HarborException;

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
		return clientOperation.doSave(clientView);
	}

}
