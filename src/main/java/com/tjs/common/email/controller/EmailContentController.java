package com.tjs.common.email.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjs.common.aop.AccessLog;
import com.tjs.common.controller.BaseController;
import com.tjs.common.email.view.EmailContentView;
import com.tjs.common.response.Response;
import com.tjs.harbor.exception.HarborException;
/**
 * 
 * @author Nisha.Panchal
 * 
 * @since 23/07/2018
 *
 */
public interface EmailContentController extends BaseController<EmailContentView> {
	
	/**
	 * This method is used to prepare dropdown for communication field.
	 * @return
	 * @throws DSTException
	 */
	@RequestMapping(value = "/dropdownCommunicationFields", method = RequestMethod.GET)
	@ResponseBody
	@AccessLog
	public Response dropdownCommunicationFields() throws HarborException;

	/**
	 * This method is used to prepare dropdown for communication triggers.
	 * @return
	 * @throws DSTException
	 */
	@RequestMapping(value = "/dropdownCommunicationTriggers", method = RequestMethod.GET)
	@ResponseBody
	@AccessLog
	public Response dropdownCommunicationTriggers() throws HarborException;
}