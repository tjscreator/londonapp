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
package com.emotome.common.location.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.emotome.common.model.Model;

/**
 * This is TimeZone model which maps timezone table.
 * 
 * @author Nirav
 * @since 13/07/2018
 *
 */
public class TimeZoneModel implements Model {

	private static final long serialVersionUID = -5749141920944458389L;
	private Integer id;
	private String timezone;
	private static Map<Integer, TimeZoneModel> MAP = new ConcurrentHashMap<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public static void addTimeZone(TimeZoneModel timeZoneModel) {
		MAP.put(timeZoneModel.getId(), timeZoneModel);
	}

	public static void removeTimeZone(TimeZoneModel timeZoneModel) {
		MAP.remove(timeZoneModel.getId());
	}

	public static Map<Integer, TimeZoneModel> getMAP() {
		return MAP;
	}
}
