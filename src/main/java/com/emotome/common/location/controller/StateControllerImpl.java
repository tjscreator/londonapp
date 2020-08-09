/*******************************************************************************
 * Copyright -2017 @Emotome
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
package com.emotome.common.location.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.emotome.common.controller.AbstractController;
import com.emotome.common.enums.ResponseCode;
import com.emotome.common.location.operation.StateOperation;
import com.emotome.common.location.view.StateView;
import com.emotome.common.operation.BaseOperation;
import com.emotome.common.response.Response;
import com.emotome.common.validation.DataType;
import com.emotome.common.validation.InputField;
import com.emotome.common.validation.Validator;
import com.emotome.harbor.exception.HarborException;

/**
 * This controller maps all state related apis.
 * 
 * @author Nirav
 * @since 14/11/2017
 */
@Controller
@RequestMapping("/state")
public class StateControllerImpl extends AbstractController<StateView> implements StateController {

	@Autowired
	StateOperation stateOperation;

	@Override
	public BaseOperation<StateView> getOperation() {
		return stateOperation;
	}

	@Override
	public Response add() throws HarborException {
		throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
	}

	@Override
	public Response search(@RequestBody StateView stateView, Integer start, Integer recordSize) throws HarborException {
		if (stateView.getCountryId() == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
		}
		return stateOperation.doSearch(stateView, start, recordSize);
	}

	@Override
	public Response save(@RequestBody StateView stateView) throws HarborException {
		if (stateView == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
		}
		isValidSaveData(stateView);
		return getOperation().doSave(stateView);
	}

	@Override
	public Response update(@RequestBody StateView stateView) throws HarborException {
		if (stateView == null || stateView.getId() == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
		}
		isValidSaveData(stateView);
		return getOperation().doUpdate(stateView);
	}

	@Override
	public Response delete(@RequestParam(name = "id", required = true) Long id) throws HarborException {
		throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
	}

	@Override
	public Response activeInActive(@RequestParam(name = "id", required = true) Long id) throws HarborException {
		throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
	}

	@Override
	public Response displayGrid(@RequestParam(name = "start", required = true) Integer start,
			@RequestParam(name = "recordSize", required = true) Integer recordSize) throws HarborException {
		throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
	}

	@Override
	public void isValidSaveData(StateView stateView) throws HarborException {
		Validator.STATE_NAME.isValid(new InputField(stateView.getName(), DataType.STRING, 30));
		if (stateView.getCountryId() == null) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"Country " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
	}

	@Override
	public Response dropdown(@RequestParam(name="countryId", required=true) Long countryId) throws HarborException {
		return stateOperation.doDropdown(countryId);
	}
}