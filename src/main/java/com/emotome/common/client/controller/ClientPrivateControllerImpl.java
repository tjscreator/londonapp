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
package com.emotome.common.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.emotome.common.aop.AccessLog;
import com.emotome.common.aop.Authorization;
import com.emotome.common.client.operation.ClientOperation;
import com.emotome.common.client.view.ClientView;
import com.emotome.common.controller.AbstractController;
import com.emotome.common.enums.ResponseCode;
import com.emotome.common.operation.BaseOperation;
import com.emotome.common.response.CommonResponse;
import com.emotome.common.response.Response;
import com.emotome.common.user.enums.ModuleEnum;
import com.emotome.common.user.enums.RightsEnum;
import com.emotome.harbor.exception.HarborException;

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
		return clientOperation.doSave(clientView);
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
		return clientOperation.doUpdate(clientView);
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