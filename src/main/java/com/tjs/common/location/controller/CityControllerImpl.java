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
package com.tjs.common.location.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjs.common.controller.AbstractController;
import com.tjs.common.enums.ResponseCode;
import com.tjs.common.location.operation.CityOperation;
import com.tjs.common.location.view.CityView;
import com.tjs.common.operation.BaseOperation;
import com.tjs.common.response.Response;
import com.tjs.common.validation.DataType;
import com.tjs.common.validation.InputField;
import com.tjs.common.validation.Validator;
import com.tjs.harbor.exception.HarborException;

/**
 * This controller maps all city related apis.
 * @author Nirav
 * @since 14/11/2017
 */
@Controller
@RequestMapping("/city")
public class CityControllerImpl extends AbstractController<CityView> implements CityController {

	@Autowired
	CityOperation cityOperation;
	
	@Override
	public BaseOperation<CityView> getOperation() {
		return cityOperation;
	}

	@Override
	public Response add() throws HarborException {
		throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
	}

	@Override
	public Response search(@RequestBody CityView cityView, Integer start, Integer recordSize) throws HarborException {
		if(cityView.getStateId() == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
		}
		return cityOperation.doSearch(cityView, start, recordSize);
	}

	@Override
	public Response save(@RequestBody CityView cityView) throws HarborException {
		if (cityView == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
		}
		isValidSaveData(cityView);
		return getOperation().doSave(cityView);
	}
	
	@Override
	public Response update(@RequestBody CityView cityView) throws HarborException {
		if (cityView == null || cityView.getId() == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
		}
		isValidSaveData(cityView);
		return getOperation().doUpdate(cityView);
	}
	
	@Override
	public Response delete(@RequestParam(name="id", required=true) Long id) throws HarborException{
		throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
	}
	
	@Override
	public Response activeInActive(@RequestParam(name="id", required=true) Long id) throws HarborException{
		throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
	}
	
	@Override
	public Response displayGrid(@RequestParam(name="start", required=true) Integer start, @RequestParam(name="recordSize", required=true) Integer recordSize) throws HarborException{
		throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
	}
	
	@Override
	public void isValidSaveData(CityView cityView) throws HarborException {
		Validator.CITY_NAME.isValid(new InputField(cityView.getName(), DataType.STRING, 30));
		if(cityView.getStateId() == null) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),  "State "+ResponseCode.DATA_IS_MISSING.getMessage());
		}
	}

	@Override
	public Response dropdown(@RequestParam(name="stateId", required=true) Long stateId) throws HarborException {
		return cityOperation.doDropdown(stateId);
	}
}
