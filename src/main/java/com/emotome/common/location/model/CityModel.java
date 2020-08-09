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

import com.emotome.common.model.IdentifierModel;
import com.emotome.common.view.IdNameView;

/**
 * This is City model which maps city list table to class.
 * 
 * @author Nirav
 * @since 06/06/2018
 *
 */
public class CityModel extends IdentifierModel {

	private static final long serialVersionUID = 6653648434546572167L;
	private String name;
	private StateModel stateModel;
	private static Map<Long, CityModel> MAP = new ConcurrentHashMap<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StateModel getStateModel() {
		return stateModel;
	}

	public void setStateModel(StateModel stateModel) {
		this.stateModel = stateModel;
	}

	public static void addCity(CityModel cityModel) {
		MAP.put(cityModel.getId(), cityModel);
	}

	public static void removeCity(CityModel cityModel) {
		MAP.remove(cityModel.getId());
	}

	public static Map<Long, CityModel> getMAP() {
		return MAP;
	}

	public static IdNameView setCityView(CityModel cityModel) {
		return new IdNameView(cityModel.getId(), cityModel.getName());
	}
}
