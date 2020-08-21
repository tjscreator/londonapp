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
 * This is application type enum which represent applicaion type title.
 * 
 * @author Vishwa.Shah
 * @since 22/01/2019
 */
public enum ApplicationTypeEnum implements EnumType {

	OIC(1, "Open Innovation Challenge"), UC(2, "University Challenge");

	private final int id;
	private final String name;
	public static final Map<Integer, ApplicationTypeEnum> MAP = new HashMap<>();

	static {
		for (ApplicationTypeEnum applicationTypeEnum : values()) {
			MAP.put(applicationTypeEnum.getId(), applicationTypeEnum);
		}
	}

	ApplicationTypeEnum(int id, String name) {
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
	 * @return applicationTypeEnum enum
	 */
	public static ApplicationTypeEnum fromId(Integer id) {
		return MAP.get(id);
	}
}
