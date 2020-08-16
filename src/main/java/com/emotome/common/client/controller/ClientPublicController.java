package com.emotome.common.client.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emotome.common.aop.AccessLog;
import com.emotome.common.client.view.ClientView;
import com.emotome.common.controller.Controller;
import com.emotome.common.response.Response;
import com.emotome.harbor.exception.HarborException;

public interface ClientPublicController extends Controller {

	/**
	 * this api for client register.
	 * 
	 * @param clientView
	 * @return
	 * @throws HarborException
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	@AccessLog
	Response register(@RequestBody ClientView clientView) throws HarborException;
}
