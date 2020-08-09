/*******************************************************************************
 * Copyright -2017 @Emotome
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
package com.emotome.common.location.service;

import java.util.List;

import com.emotome.common.location.model.StateModel;
import com.emotome.common.service.BaseService;

/**
 * 
 * @author Nirav
 * @since 10/11/2017
 */
public interface StateService extends BaseService<StateModel> {
	String LIGHT_STATE_MODEL = "lightStateModel";
	String STATE_MODEL = "stateModel";
	String STATE_CITY_MODEL = "stateCityModel";
	
	/**
	 * This method is used to fetch all state data using light state entity.
	 * @return
	 */
	List<StateModel> findAllUsingDiffEntity();
	
	/**
	 * This method is used to fetch using different entity.
	 * @param id
	 * @return
	 */
	StateModel getUsingDiffEntity(Long id);

	/**
	 * This method is used get state models by country id.
	 * @param countryId
	 * @return
	 */
	 List<StateModel> findByCountry(Long countryId);
}
