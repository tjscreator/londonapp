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
package com.emotome.common.user.enums;

import java.util.HashMap;
import java.util.Map;

import com.emotome.common.enums.EnumType;

/**
 * This is App enum which represent group of different apps in system.
 * 
 * @author Nirav.Shah
 * @since 28/03/2019
 */
public enum AppEnum implements EnumType {

	MASTER_ADMIN(1, "Master Admin"), CLIENT(2, "Client"), END_USER(3, "End User");

	private final int id;
	private final String name;
	public static final Map<Integer, AppEnum> MAP = new HashMap<>();

	static {
		for (AppEnum appEnum : values()) {
			MAP.put(appEnum.getId(), appEnum);
		}
	}

	AppEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	/**
	 * This methods is used to fetch Enum base on given id.
	 * 
	 * @param id
	 *            enum key
	 * @return rightsEnums enum
	 */
	public static AppEnum fromId(Integer id) {
		return MAP.get(id);
	}
}
