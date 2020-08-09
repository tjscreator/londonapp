package com.emotome.common.email.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.emotome.common.aop.AccessLog;
import com.emotome.common.aop.Authorization;
import com.emotome.common.controller.AbstractController;
import com.emotome.common.email.operation.EmailContentOperation;
import com.emotome.common.email.view.EmailContentView;
import com.emotome.common.enums.ResponseCode;
import com.emotome.common.operation.BaseOperation;
import com.emotome.common.response.CommonResponse;
import com.emotome.common.response.PageResultResponse;
import com.emotome.common.response.Response;
import com.emotome.common.user.enums.ModuleEnum;
import com.emotome.common.user.enums.RightsEnum;
import com.emotome.common.validation.DataType;
import com.emotome.common.validation.InputField;
import com.emotome.common.validation.Validator;
import com.emotome.common.view.KeyValueView;
import com.emotome.harbor.email.enums.CommunicationFields;
import com.emotome.harbor.email.enums.CommunicationTriggerEnum;
import com.emotome.harbor.exception.HarborException;

/**
 * This controller maps all emailcontent related apis
 * 
 * @author Nisha.Panchal
 * 
 * @since 23/07/2018
 *
 */
@Controller
@RequestMapping("/emailContent")
public class EmailContentControllerImpl extends AbstractController<EmailContentView> implements EmailContentController {

	@Autowired
	EmailContentOperation emailContentOperation;

	@Override
	public BaseOperation<EmailContentView> getOperation() {
		return emailContentOperation;
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.COMMUNICATION, rights = RightsEnum.ADD)
	public Response add() throws HarborException {
		return CommonResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage());
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.COMMUNICATION, rights = RightsEnum.ADD)
	public Response save(@RequestBody EmailContentView emailContentView) throws HarborException {
		if (emailContentView == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
		}
		isValidSaveData(emailContentView);
		return emailContentOperation.doSave(emailContentView);
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.COMMUNICATION, rights = RightsEnum.VIEW)
	public Response view(@RequestParam(name = "id") Long id) throws HarborException {
		if (id == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
		}
		return emailContentOperation.doView(id);
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.COMMUNICATION, rights = RightsEnum.UPDATE)
	public Response edit(@RequestParam(name = "id") Long id) throws HarborException {
		if (id == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
		}
		return emailContentOperation.doEdit(id);
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.COMMUNICATION, rights = RightsEnum.UPDATE)
	public Response update(@RequestBody EmailContentView emailContentView) throws HarborException {
		if (emailContentView == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
		}
		isValidSaveData(emailContentView);
		return emailContentOperation.doUpdate(emailContentView);
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.COMMUNICATION, rights = RightsEnum.DELETE)
	public Response delete(@RequestParam(name = "id") Long id) throws HarborException {
		if (id == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
		}
		return emailContentOperation.doDelete(id);
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.COMMUNICATION, rights = RightsEnum.UPDATE)
	public Response activeInActive(@RequestParam(name = "id") Long id) throws HarborException {
		if (id == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
		}
		return emailContentOperation.doActiveInActive(id);
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.COMMUNICATION, rights = RightsEnum.VIEW)
	public Response displayGrid(@RequestParam(name = "start", required = true) Integer start,
			@RequestParam(name = "recordSize", required = true) Integer recordSize) throws HarborException {
		return emailContentOperation.doDisplayGrid(start, recordSize);
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.COMMUNICATION, rights = RightsEnum.VIEW)
	public Response search(EmailContentView view, Integer start, Integer recordSize) throws HarborException {
		return emailContentOperation.doSearch(view, start, recordSize);
	}

	@Override
	public void isValidSaveData(EmailContentView emailContentView) throws HarborException {
		if (emailContentView.getEmailAccountView() == null) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"Email Acoount id " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		if (emailContentView.getEmailAccountView() != null && emailContentView.getEmailAccountView().getId() == null) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"Email Acoount id " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		Validator.EMAIL_CONTENT_NAME.isValid(new InputField(emailContentView.getName(), DataType.STRING, 100));
		Validator.CONTENT.isValid(new InputField(emailContentView.getContent(), DataType.STRING));
		Validator.SUBJECT.isValid(new InputField(emailContentView.getSubject(), DataType.STRING, 1000));
		if (emailContentView.getTrigger() == null) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"Email Trigger id " + ResponseCode.DATA_IS_MISSING.getMessage());
		}

		if (emailContentView.getTrigger() != null && emailContentView.getTrigger().getKey() == null) {
			throw new HarborException(ResponseCode.DATA_IS_INVALID.getCode(),
					"Email Trigger " + ResponseCode.DATA_IS_INVALID.getMessage());
		}
		if (CommunicationTriggerEnum.fromId(emailContentView.getTrigger().getKey().intValue()) == null) {
			throw new HarborException(ResponseCode.DATA_IS_INVALID.getCode(),
					"Email Trigger " + ResponseCode.DATA_IS_INVALID.getMessage());
		}
	}

	@Override
	@AccessLog
	public Response dropdownCommunicationFields() throws HarborException {
		List<KeyValueView> keyValueViews = new ArrayList<>();
		CommunicationFields.MAP.forEach((key, value) -> {
			keyValueViews.add(KeyValueView.create(Long.valueOf(key), value.getName()));
		});
		return PageResultResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(),
				keyValueViews.size(), keyValueViews);
	}

	@Override
	@AccessLog
	public Response dropdownCommunicationTriggers() throws HarborException {
		List<KeyValueView> keyValueViews = new ArrayList<>();
		CommunicationTriggerEnum.MAP.forEach((key, value) -> {
			keyValueViews.add(KeyValueView.create(Long.valueOf(key), value.getName()));
		});
		return PageResultResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(),
				keyValueViews.size(), keyValueViews);
	}
}