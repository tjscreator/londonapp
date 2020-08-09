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
package com.emotome.common.setting.service;

import com.emotome.common.service.BaseService;
import com.emotome.common.setting.model.SettingModel;

/**
 * 
 * @author Nirav
 * @since 24/11/2018
 */
public interface SettingService extends BaseService<SettingModel> {
	String SETTING_MODEL = "settingModel";

	/**
	 * This method used to fetch setting template based on key & hospital id.
	 * 
	 * @param key
	 * @return
	 */
	SettingModel get(String key);
}
