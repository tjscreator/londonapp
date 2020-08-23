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
package com.tjs.common.client.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjs.common.aop.AccessLog;
import com.tjs.common.aop.Authorization;
import com.tjs.common.client.operation.ClientOperation;
import com.tjs.common.client.view.ClientView;
import com.tjs.common.controller.AbstractController;
import com.tjs.common.enums.ResponseCode;
import com.tjs.common.logger.LoggerService;
import com.tjs.common.operation.BaseOperation;
import com.tjs.common.response.CommonResponse;
import com.tjs.common.response.Response;
import com.tjs.common.user.enums.ModuleEnum;
import com.tjs.common.user.enums.RightsEnum;
import com.tjs.harbor.exception.HarborException;

/**
 * This controller maps all hospital related apis.
 * 
 * @author Jaydip
 * @since 22/04/2019
 */
@Controller
@RequestMapping("/private/client")
public class ClientPrivateControllerImpl extends AbstractController<ClientView> implements ClientPrivateController {

	@Autowired
	private ClientOperation clientOperation;

	@Override
	public BaseOperation<ClientView> getOperation() {
		return clientOperation;
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.CLIENT, rights = RightsEnum.ADD)
	public Response add() throws HarborException {
		return CommonResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage());
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.CLIENT, rights = RightsEnum.ADD)
	public Response save(@RequestBody ClientView clientView) throws HarborException {
		if (clientView == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		isValidSaveData(clientView);
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

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.CLIENT, rights = RightsEnum.VIEW)
	public Response view(@RequestParam(name = "id") Long id) throws HarborException {
		if (id == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		return clientOperation.doView(id);
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.CLIENT, rights = RightsEnum.UPDATE)
	public Response edit(@RequestParam(name = "id") Long id) throws HarborException {
		if (id == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		return clientOperation.doEdit(id);
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.CLIENT, rights = RightsEnum.UPDATE)
	public Response update(@RequestBody ClientView clientView) throws HarborException {
		if (clientView == null || (clientView != null && clientView.getId() == null)) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		isValidSaveData(clientView);
		try {
			return clientOperation.doUpdate(clientView);
		} catch (DataIntegrityViolationException dataIntegrityViolationException) {
			LoggerService.exception(dataIntegrityViolationException);
			throw new HarborException(ResponseCode.ALREADY_EXIST.getCode(), "Client name already exists.");
		} catch (ConstraintViolationException constraintViolationException) {
			LoggerService.exception(constraintViolationException);
			throw new HarborException(ResponseCode.ALREADY_EXIST.getCode(), "Client name already exists.");
		}
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.CLIENT, rights = RightsEnum.DELETE)
	public Response delete(@RequestParam(name = "id") Long id) throws HarborException {
		if (id == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		return clientOperation.doDelete(id);
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.CLIENT, rights = RightsEnum.ACTIVATION)
	public Response activeInActive(@RequestParam(name = "id") Long id) throws HarborException {
		throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.CLIENT, rights = RightsEnum.LIST)
	public Response displayGrid(@RequestParam(name = "start", required = true) Integer start,
			@RequestParam(name = "recordSize", required = true) Integer recordSize) throws HarborException {
		return clientOperation.doDisplayGrid(start, recordSize);
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.CLIENT, rights = RightsEnum.LIST)
	public Response search(@RequestBody ClientView clientView,
			@RequestParam(name = "start", required = true) Integer start,
			@RequestParam(name = "recordSize", required = true) Integer recordSize) throws HarborException {
		return clientOperation.doSearch(clientView, start, recordSize);
	}

	@Override
	public void isValidSaveData(ClientView clientView) throws HarborException {
		ClientView.isValid(clientView);

	}
}