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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emotome.common.aop.AccessLog;
import com.emotome.common.aop.Authorization;
import com.emotome.common.enums.ResponseCode;
import com.emotome.common.response.PageResultResponse;
import com.emotome.common.response.Response;
import com.emotome.common.user.enums.ModuleEnum;
import com.emotome.common.user.enums.RightsEnum;
import com.emotome.common.user.view.ModuleView;
import com.emotome.harbor.exception.HarborException;

/**
 * This controller maps all Role related APIs.
 * 
 * @author Nirav.Shah
 * @since 08/02/2018
 */
@Controller
@RequestMapping("/private/module")
public class ModuleControllerImpl implements ModuleController {

	@Override
	@AccessLog
	@Authorization(modules = ModuleEnum.ROLE, rights = RightsEnum.UPDATE)
	public Response all() throws HarborException {
		List<ModuleView> moduleViews = new ArrayList<>();
		for (Map.Entry<Integer, ModuleEnum> map : ModuleEnum.MAP.entrySet()) {
			ModuleView moduleView = new ModuleView();
			moduleView.setId(map.getKey());
			moduleView.setName(map.getValue().getName());
			moduleViews.add(moduleView);
		}
		return PageResultResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(),
				moduleViews.size(), moduleViews);
	}
}
