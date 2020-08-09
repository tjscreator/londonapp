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
package com.emotome.common.location.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.emotome.common.aop.AccessLog;
import com.emotome.common.controller.AbstractController;
import com.emotome.common.enums.ResponseCode;
import com.emotome.common.location.operation.CountryOperation;
import com.emotome.common.location.view.CountryView;
import com.emotome.common.operation.BaseOperation;
import com.emotome.common.response.Response;
import com.emotome.common.validation.DataType;
import com.emotome.common.validation.InputField;
import com.emotome.common.validation.Validator;
import com.emotome.harbor.exception.HarborException;

/**
 * This controller maps all city related apis.
 * 
 * @author Dhruvang
 * @since 29/01/2018
 */
@Controller
@RequestMapping("/country")
public class CountryControllerImpl extends AbstractController<CountryView> implements CountryController {

	@Autowired
	private CountryOperation countryOperation;

	@Override
	public BaseOperation<CountryView> getOperation() {
		return countryOperation;
	}

	@Override
	public Response add() throws HarborException {
		throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
	}

	@Override
	public Response search(@RequestBody CountryView countryView, Integer start, Integer recordSize)
			throws HarborException {
		return countryOperation.doSearch(countryView, start, recordSize);
	}

	@Override
	public void isValidSaveData(CountryView countryView) throws HarborException {
		Validator.COUNTRY_NAME.isValid(new InputField(countryView.getName(), DataType.STRING, 150));
		Validator.COUNTRY_SHORT_NAME.isValid(new InputField(countryView.getSortName(), DataType.STRING, 3));
		if (countryView.getPhoneCode() == 0) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"Country Phone Code " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
	}

	@Override
	public Response search(Integer start) throws HarborException {
		return countryOperation.doSearch(null, start, null);
	}

	@Override
	@AccessLog
	public Response update(@RequestBody CountryView countryView) throws HarborException {
		if (countryView == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
		}
		if (countryView.getId() == null) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"Id " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		isValidSaveData(countryView);
		return getOperation().doUpdate(countryView);
	}

	@Override
	@AccessLog
	public Response delete(@RequestParam(name = "id", required = true) Long id) throws HarborException {
		throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
	}

	@Override
	@AccessLog
	public Response activeInActive(@RequestParam(name = "id", required = true) Long id) throws HarborException {
		throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
	}

	@Override
	public Response dropdown() throws HarborException {
		return countryOperation.doDropdown();
	}
}