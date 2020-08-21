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

import java.util.List;

import com.tjs.common.operation.Operation;
import com.tjs.common.response.Response;
import com.tjs.common.setting.model.SettingModel;
import com.tjs.common.setting.service.SettingService;
import com.tjs.common.setting.view.SettingView;
import com.tjs.harbor.exception.HarborException;

/*
 * @author Nirav
 * @since 24/11/2018
 */
public interface SettingOperation extends Operation {
	/**
	 * This method is used to view entity.
	 * 
	 * @param key
	 * @return
	 * @throws HarborException
	 */
	Response doView(String key) throws HarborException;

	/**
	 * This method is used to edit entity.
	 * 
	 * @param key
	 * @return
	 * @throws HarborException
	 */
	Response doEdit(String key) throws HarborException;

	/**
	 * This method is used to update entity
	 * 
	 * @param view
	 * @return
	 * @throws HarborException
	 */
	Response doUpdate(SettingView settingView) throws HarborException;

	/**
	 * This method used for search data base on queries.
	 * 
	 * @param start
	 * @param view
	 * @param recordSize
	 * @return
	 */
	Response doSearch(SettingView settingView, Integer start, Integer recordSize) throws HarborException;

	/**
	 * This method used when need to convert model to view
	 * 
	 * @param model
	 * @return view
	 */
	SettingView fromModel(SettingModel settingModel);

	/**
	 * This method convert list of model to list of view
	 * 
	 * @param settingModels
	 *            list of model
	 * @return list of view
	 */
	List<SettingView> fromModelList(List<SettingModel> settingModels);

	/**
	 * This method use for get Service with respected operation
	 * 
	 * @return SettingService
	 */
	SettingService getService();

	/**
	 * this method for prepare model from view.
	 * 
	 * @param settingView
	 * @param settingModel
	 * @return
	 */
	SettingModel toModel(SettingView settingView, SettingModel settingModel);
}
