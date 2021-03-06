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

import java.util.HashMap;
import java.util.Map;

import com.tjs.common.enums.EnumType;

/**
 * This is Group enum which represent group of different roles in system.
 * 
 * @author Nirav.Shah
 * @since 28/03/2019
 */
public enum GroupEnum implements EnumType {

	MASTER_ADMIN(1, "Master Admin"), CLIENT_ADMIN(2, "Client Admin"), END_USER(3, "End User");

	private final int id;
	private final String name;
	public static final Map<Integer, GroupEnum> MAP = new HashMap<>();

	static {
		for (GroupEnum groupEnum : values()) {
			MAP.put(groupEnum.getId(), groupEnum);
		}
	}

	GroupEnum(int id, String name) {
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
	public static GroupEnum fromId(Integer id) {
		return MAP.get(id);
	}
}
