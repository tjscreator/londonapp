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
package com.tjs.common.setting.operation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tjs.common.enums.ResponseCode;
import com.tjs.common.modelenums.CommonStatusEnum;
import com.tjs.common.response.CommonResponse;
import com.tjs.common.response.PageResultResponse;
import com.tjs.common.response.Response;
import com.tjs.common.response.ViewResponse;
import com.tjs.common.setting.model.SettingModel;
import com.tjs.common.setting.service.SettingService;
import com.tjs.common.setting.view.SettingView;
import com.tjs.common.util.DateUtility;
import com.tjs.common.validation.DataType;
import com.tjs.harbor.exception.HarborException;

/**
 * This class used to perform all business operation on city model.
 * 
 * @author Nirav
 * @since 14/11/2018
 */
@Component(value = "settingOperation")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class SettingOperationImpl implements SettingOperation {

	@Autowired
	SettingService settingService;

	@Override
	public SettingService getService() {
		return settingService;
	}

	@Override
	public Response doView(String key) throws HarborException {
		SettingModel settingTemplateModel = settingService.get(key);
		if (settingTemplateModel == null) {
			return CommonResponse.create(ResponseCode.NO_DATA_FOUND.getCode(), ResponseCode.NO_DATA_FOUND.getMessage());
		}
		SettingView settingView = fromModel(settingTemplateModel);
		return ViewResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(),
				settingView);
	}

	@Override
	public Response doEdit(String key) throws HarborException {
		return doView(key);
	}

	@Override
	public Response doUpdate(SettingView settingView) throws HarborException {
		SettingModel settingModel = settingService.get(settingView.getKey());
		if (settingModel == null) {
			return CommonResponse.create(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		toModel(settingView, settingModel);
		settingService.update(settingModel);
		SettingModel.updateValue(settingView.getKey(), settingView.getValue());

		return CommonResponse.create(ResponseCode.UPDATE_SUCCESSFULLY.getCode(),
				ResponseCode.UPDATE_SUCCESSFULLY.getMessage());
	}

	@Override
	public Response doSearch(SettingView settingView, Integer start, Integer recordSize) throws HarborException {
		List<SettingView> settingViews = new ArrayList<>();
		List<SettingModel> settingModels = settingService.findAll();
		for (SettingModel settingModel : settingModels) {
			settingViews.add(fromModel(settingModel));
		}
		return PageResultResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(),
				settingViews.size(), settingViews);
	}

	@Override
	public SettingView fromModel(SettingModel settingModel) {
		SettingView settingView = new SettingView();
		settingView.setKey(settingModel.getKey());
		settingView.setValue(settingModel.getValue());
		convertIntoDisplay(settingView, settingModel.getValue(), settingModel.getDataType());
		return settingView;
	}

	@Override
	public List<SettingView> fromModelList(List<SettingModel> modelList) {
		List<SettingView> settingViews = new ArrayList<>(modelList.size());
		for (SettingModel settingModel : modelList) {
			settingViews.add(fromModel(settingModel));
		}
		return settingViews;
	}

	private void convertIntoDisplay(SettingView settingView, String value, DataType dataType) {
		if (DataType.INT.equals(dataType)) {
			settingView.setDisplayValue(Integer.valueOf(value));
		}
		if (DataType.STRING.equals(dataType)) {
			settingView.setDisplayValue(String.valueOf(value));
		}
		if (DataType.DATE.equals(dataType)) {
			settingView.setDisplayValue(DateUtility.getLocalDateTime(Long.valueOf(value)).toString());
		}
		if (DataType.BOOLEAN.equals(dataType)) {
			if (CommonStatusEnum.YES.equals(CommonStatusEnum.fromId(Integer.valueOf(value)))) {
				settingView.setDisplayValue(true);
			} else {
				settingView.setDisplayValue(false);
			}
		}
	}

	@Override
	public SettingModel toModel(SettingView settingView, SettingModel settingModel) {
		settingModel.setKey(settingView.getKey());
		settingModel.setValue(settingView.getValue());
		return settingModel;
	}
}
