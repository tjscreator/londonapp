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
package com.tjs.common.user.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tjs.common.enums.EnumType;

/**
 * This is Module enum which represent module of system.
 * 
 * @author Nirav.Shah
 * @since 08/02/2018
 */

public enum ModuleEnum implements EnumType {
	USER(1, "User", Arrays.asList(AppEnum.END_USER),
			Arrays.asList(RightsEnum.ADD, RightsEnum.UPDATE, RightsEnum.VIEW, RightsEnum.DELETE, RightsEnum.ACTIVATION,
					RightsEnum.LIST)),
	ROLE(2, "Role", Arrays.asList(AppEnum.CLIENT), Arrays.asList(RightsEnum.VIEW, RightsEnum.LIST)),
	SETTING(3, "Setting", Arrays.asList(AppEnum.CLIENT), Arrays.asList(RightsEnum.UPDATE, RightsEnum.VIEW)),
	CLIENT(4, "Client", Arrays.asList(AppEnum.CLIENT), Arrays.asList(RightsEnum.UPDATE, RightsEnum.VIEW)),
	CLIENT_BRANCH(5, "Client Branch", Arrays.asList(AppEnum.CLIENT), Arrays.asList(RightsEnum.ADD, RightsEnum.UPDATE, RightsEnum.VIEW, RightsEnum.DELETE, RightsEnum.ACTIVATION,
			RightsEnum.LIST)),
	APP(6, "App", Arrays.asList(AppEnum.CLIENT), Arrays.asList()),
	SMS_CONTENT(7, "SmsContent", Arrays.asList(AppEnum.CLIENT), Arrays.asList(RightsEnum.UPDATE, RightsEnum.LIST)),
	SMS_DEFAULT_CONTENT(8, "Sms Default Content", Arrays.asList(AppEnum.CLIENT),
			Arrays.asList(RightsEnum.UPDATE, RightsEnum.LIST)),
	COMMUNICATION(9, "Communication", Arrays.asList(AppEnum.MASTER_ADMIN),
			Arrays.asList(RightsEnum.UPDATE, RightsEnum.LIST));
	private final int id;
	private final String name;
	private final List<AppEnum> appEnums;
	private final List<RightsEnum> rightsEnums;
	public static final Map<Integer, ModuleEnum> MAP = new HashMap<>();

	static {
		for (ModuleEnum moduleEnums : values()) {
			MAP.put(moduleEnums.getId(), moduleEnums);
		}
	}

	ModuleEnum(int id, String name, List<AppEnum> appEnums, List<RightsEnum> rightsEnums) {
		this.id = id;
		this.name = name;
		this.appEnums = appEnums;
		this.rightsEnums = rightsEnums;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	public List<AppEnum> getAppEnums() {
		return appEnums;
	}

	public List<RightsEnum> getRightsEnums() {
		return rightsEnums;
	}

	/**
	 * This methods is used to fetch Enum base on given id.
	 * 
	 * @param id enum key
	 * @return rightsEnums enum
	 */
	public static ModuleEnum fromId(Integer id) {
		return MAP.get(id);
	}
}
