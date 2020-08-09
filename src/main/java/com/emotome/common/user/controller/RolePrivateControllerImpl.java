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
package com.emotome.common.user.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.emotome.common.aop.AccessLog;
import com.emotome.common.aop.AccessedByClient;
import com.emotome.common.aop.Authorization;
import com.emotome.common.controller.AbstractController;
import com.emotome.common.enums.ResponseCode;
import com.emotome.common.operation.BaseOperation;
import com.emotome.common.response.CommonResponse;
import com.emotome.common.response.Response;
import com.emotome.common.user.enums.ModuleEnum;
import com.emotome.common.user.enums.RightsEnum;
import com.emotome.common.user.operation.RoleOperation;
import com.emotome.common.user.view.RoleModuleRightsView;
import com.emotome.common.user.view.RoleView;
import com.emotome.common.validation.DataType;
import com.emotome.common.validation.InputField;
import com.emotome.common.validation.RegexEnum;
import com.emotome.common.validation.Validator;
import com.emotome.harbor.exception.HarborException;

/**
 * This controller maps all Role related APIs.
 * 
 * @author Nirav.Shah
 * @since 08/02/2018
 */
@Controller
@RequestMapping("/private/role")
public class RolePrivateControllerImpl extends AbstractController<RoleView> implements RolePrivateController {

	@Autowired
	private RoleOperation roleOperation;

	@Override
	public BaseOperation<RoleView> getOperation() {
		return roleOperation;
	}

	@Override
	@AccessLog
	@AccessedByClient
	@Authorization(modules = ModuleEnum.ROLE, rights = RightsEnum.ADD)
	public Response add() throws HarborException {
		return CommonResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage());
	}

	@Override
	@AccessLog
	@AccessedByClient
	@Authorization(modules = ModuleEnum.ROLE, rights = RightsEnum.ADD)
	public Response save(@RequestBody RoleView roleView) throws HarborException {
		if (roleView == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		isValidSaveData(roleView);
		return roleOperation.doSave(roleView);
	}

	@Override
	@AccessLog
	@AccessedByClient
	@Authorization(modules = ModuleEnum.ROLE, rights = RightsEnum.UPDATE)
	public Response update(@RequestBody RoleView roleView) throws HarborException {
		if (roleView == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		if (roleView.getId() == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		isValidSaveData(roleView);
		return roleOperation.doUpdate(roleView);
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.ROLE, rights = RightsEnum.LIST)
	public Response search(@RequestBody RoleView roleView, Integer start, Integer recordSize) throws HarborException {
		return roleOperation.doSearch(roleView, start, recordSize);
	}

	@Override
	public void isValidSaveData(RoleView roleView) throws HarborException {
		Validator.ROLE_NAME
				.isValid(new InputField(roleView.getName(), DataType.STRING, 30, RegexEnum.ALPHABETS_WITH_SPACE));
		if (!StringUtils.isBlank(roleView.getDescription())) {
			Validator.ROLE_DESCRIPTION.isValid(new InputField(roleView.getDescription(), DataType.STRING, 256));
		}
		validateGroup(roleView);
		validateModuleRights(roleView);
	}

	private void validateGroup(RoleView roleView) throws HarborException {
		if (roleView.getGroupView() == null
				|| (roleView.getGroupView() != null && roleView.getGroupView().getId() == null)) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"Group  " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
	}

	/**
	 * This method is used to validate module & rights data.
	 * 
	 * @param roleView
	 * @throws HarborException
	 */
	private void validateModuleRights(RoleView roleView) throws HarborException {
		if (roleView.getRoleModuleRightsView() == null
				|| (roleView.getRoleModuleRightsView() != null && roleView.getRoleModuleRightsView().isEmpty())) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"Module & Rights  " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		for (RoleModuleRightsView roleModuleRightsView : roleView.getRoleModuleRightsView()) {
			if (roleModuleRightsView.getModuleView() == null) {
				throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
						"Module " + ResponseCode.DATA_IS_MISSING.getMessage());
			}
			if (roleModuleRightsView.getRightsView() == null) {
				throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
						"Rights " + ResponseCode.DATA_IS_MISSING.getMessage());
			}
			if (ModuleEnum.fromId(roleModuleRightsView.getModuleView().getId()) == null) {
				throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
						"Module " + ResponseCode.DATA_IS_INVALID.getMessage());
			}
			if (RightsEnum.fromId(roleModuleRightsView.getRightsView().getId()) == null) {
				throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
						"Rights " + ResponseCode.DATA_IS_INVALID.getMessage());
			}
		}
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.ROLE, rights = RightsEnum.VIEW)
	public Response view(@RequestParam(name = "id", required = true) Long id) throws HarborException {
		if (id == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		return getOperation().doView(id);
	}

	@Override
	@AccessLog
	@AccessedByClient
	@Authorization(modules = ModuleEnum.ROLE, rights = RightsEnum.UPDATE)
	public Response edit(@RequestParam(name = "id", required = true) Long id) throws HarborException {
		if (id == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		return getOperation().doEdit(id);
	}

	@Override
	@AccessLog
	@AccessedByClient
	@Authorization(modules = ModuleEnum.ROLE, rights = RightsEnum.DELETE)
	public Response delete(@RequestParam(name = "id", required = true) Long id) throws HarborException {
		if (id == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		return getOperation().doDelete(id);
	}

	@Override
	@AccessLog
	@AccessedByClient
	@Authorization(modules = ModuleEnum.ROLE, rights = RightsEnum.ACTIVATION)
	public Response activeInActive(@RequestParam(name = "id", required = true) Long id) throws HarborException {
		if (id == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		return getOperation().doActiveInActive(id);
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.ROLE, rights = RightsEnum.LIST)
	public Response displayGrid(@RequestParam(name = "start", required = true) Integer start,
			@RequestParam(name = "recordSize", required = true) Integer recordSize) throws HarborException {
		return getOperation().doDisplayGrid(start, recordSize);
	}
}