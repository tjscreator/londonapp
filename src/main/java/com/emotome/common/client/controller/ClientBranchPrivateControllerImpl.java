package com.emotome.common.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.emotome.common.aop.AccessLog;
import com.emotome.common.aop.Authorization;
import com.emotome.common.client.operation.ClientBranchOperation;
import com.emotome.common.client.view.ClientBranchView;
import com.emotome.common.controller.AbstractController;
import com.emotome.common.enums.ResponseCode;
import com.emotome.common.operation.BaseOperation;
import com.emotome.common.response.CommonResponse;
import com.emotome.common.response.Response;
import com.emotome.common.user.enums.ModuleEnum;
import com.emotome.common.user.enums.RightsEnum;
import com.emotome.harbor.exception.HarborException;

public class ClientBranchPrivateControllerImpl extends AbstractController<ClientBranchView>
		implements ClientBranchPrivateController {

	@Autowired
	private ClientBranchOperation clientBranchOperation;

	@Override
	public BaseOperation<ClientBranchView> getOperation() {
		return clientBranchOperation;
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.CLIENT_BRANCH, rights = RightsEnum.ADD)
	public Response add() throws HarborException {
		return CommonResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage());
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.CLIENT_BRANCH, rights = RightsEnum.ADD)
	public Response save(@RequestBody ClientBranchView clientBranchView) throws HarborException {
		if (clientBranchView == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		isValidSaveData(clientBranchView);
		return clientBranchOperation.doSave(clientBranchView);
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.CLIENT_BRANCH, rights = RightsEnum.UPDATE)
	public Response update(@RequestBody ClientBranchView clientBranchView) throws HarborException {
		if (clientBranchView == null || (clientBranchView != null && clientBranchView.getId() == null)) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		isValidSaveData(clientBranchView);
		return clientBranchOperation.doUpdate(clientBranchView);
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.CLIENT_BRANCH, rights = RightsEnum.VIEW)
	public Response view(@RequestParam(name = "id") Long id) throws HarborException {
		if (id == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		return clientBranchOperation.doView(id);
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.CLIENT_BRANCH, rights = RightsEnum.UPDATE)
	public Response edit(@RequestParam(name = "id") Long id) throws HarborException {
		if (id == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		return clientBranchOperation.doEdit(id);
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.CLIENT_BRANCH, rights = RightsEnum.DELETE)
	public Response delete(@RequestParam(name = "id") Long id) throws HarborException {
		if (id == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		return clientBranchOperation.doDelete(id);
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.CLIENT_BRANCH, rights = RightsEnum.ACTIVATION)
	public Response activeInActive(@RequestParam(name = "id") Long id) throws HarborException {
		throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.CLIENT_BRANCH, rights = RightsEnum.LIST)
	public Response displayGrid(@RequestParam(name = "start", required = true) Integer start,
			@RequestParam(name = "recordSize", required = true) Integer recordSize) throws HarborException {
		return clientBranchOperation.doDisplayGrid(start, recordSize);
	}

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.CLIENT_BRANCH, rights = RightsEnum.LIST)
	public Response search(@RequestBody ClientBranchView clientBranchView,
			@RequestParam(name = "start", required = true) Integer start,
			@RequestParam(name = "recordSize", required = true) Integer recordSize) throws HarborException {
		return clientBranchOperation.doSearch(clientBranchView, start, recordSize);
	}

	@Override
	public void isValidSaveData(@RequestBody ClientBranchView clientBranchView) throws HarborException {
		ClientBranchView.isValid(clientBranchView);

	}
}
