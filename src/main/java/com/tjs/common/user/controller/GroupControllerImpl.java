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
package com.tjs.common.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjs.common.aop.AccessLog;
import com.tjs.common.aop.AccessedByClient;
import com.tjs.common.aop.Authorization;
import com.tjs.common.enums.ResponseCode;
import com.tjs.common.response.PageResultResponse;
import com.tjs.common.response.Response;
import com.tjs.common.user.enums.GroupEnum;
import com.tjs.common.user.enums.ModuleEnum;
import com.tjs.common.user.enums.RightsEnum;
import com.tjs.common.user.view.GroupView;
import com.tjs.harbor.exception.HarborException;

/**
 * This controller maps all Group related APIs.
 * 
 * @author Nirav.Shah
 * @since 08/02/2018
 */
@Controller
@RequestMapping("/private/group")
public class GroupControllerImpl implements GroupController {

	@Override
	@AccessLog
	@AccessedByClient
	@Authorization(modules = ModuleEnum.ROLE, rights = RightsEnum.ADD)
	public Response all() throws HarborException {
		List<GroupView> groupViews = new ArrayList<>();
		for (Map.Entry<Integer, GroupEnum> map : GroupEnum.MAP.entrySet()) {
			GroupView groupView = new GroupView();
			groupView.setId(map.getKey());
			groupView.setName(map.getValue().getName());
			groupViews.add(groupView);
		}
		return PageResultResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(),
				groupViews.size(), groupViews);
	}
}
