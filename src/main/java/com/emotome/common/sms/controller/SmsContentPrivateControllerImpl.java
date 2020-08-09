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
package com.emotome.common.sms.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.emotome.common.aop.AccessLog;
import com.emotome.common.aop.Authorization;
import com.emotome.common.controller.AbstractController;
import com.emotome.common.enums.ResponseCode;
import com.emotome.common.operation.BaseOperation;
import com.emotome.common.response.PageResultResponse;
import com.emotome.common.response.Response;
import com.emotome.common.sms.operation.SmsContentOperation;
import com.emotome.common.sms.view.SmsContentView;
import com.emotome.common.user.enums.ModuleEnum;
import com.emotome.common.user.enums.RightsEnum;
import com.emotome.common.view.KeyValueView;
import com.emotome.harbor.email.enums.CommunicationFields;
import com.emotome.harbor.exception.HarborException;

/**
 * This controller maps all sms content related apis.
 * 
 * @author JD
 * @since 28/08/2019
 */
@Controller
@RequestMapping("/private/sms-content")
public class SmsContentPrivateControllerImpl extends AbstractController<SmsContentView>
		implements SmsContentPrivateController {

	@Autowired
	private SmsContentOperation smsContentOperation;

	@Override
	public BaseOperation<SmsContentView> getOperation() {
		return smsContentOperation;
	}

	@Override
	public Response add() throws HarborException {
		throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
	}

	@Override
	public Response save(@RequestBody SmsContentView smsContentView) throws HarborException {
		throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
	}

	@Override
	public Response view(@RequestParam(name = "id", required = true) Long id) throws HarborException {
		throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
	}

	@Override
	public Response delete(@RequestParam(name = "id", required = true) Long id) throws HarborException {
		throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.SMS_CONTENT, rights = RightsEnum.LIST)
	public Response search(@RequestBody SmsContentView smsContentView,
			@RequestParam(name = "start", required = true) Integer start,
			@RequestParam(name = "recordSize", required = true) Integer recordSize) throws HarborException {
		return smsContentOperation.doSearch(smsContentView, start, recordSize);
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.SMS_CONTENT, rights = RightsEnum.UPDATE)
	public Response edit(@RequestParam(name = "id", required = true) Long id) throws HarborException {
		if (id == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		return smsContentOperation.doEdit(id);
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.SMS_CONTENT, rights = RightsEnum.UPDATE)
	public Response update(@RequestBody SmsContentView smsContentView) throws HarborException {
		if (smsContentView == null || (smsContentView != null && smsContentView.getId() == null)) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		isValidSaveData(smsContentView);
		return smsContentOperation.doUpdate(smsContentView);
	}

	@Override
	public void isValidSaveData(SmsContentView smsContentView) throws HarborException {
		if (StringUtils.isBlank(smsContentView.getContent())) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"Content " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		if (StringUtils.isBlank(smsContentView.getSubject())) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"Subject " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.SMS_CONTENT, rights = RightsEnum.LIST)
	public Response communicationFields() throws HarborException {
		List<KeyValueView> keyValueViews = new ArrayList<>();
		CommunicationFields.MAP.forEach((key, value) -> {
			keyValueViews.add(KeyValueView.create(Long.valueOf(key), value.getName()));
		});
		return PageResultResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(),
				keyValueViews.size(), keyValueViews);
	}

}
