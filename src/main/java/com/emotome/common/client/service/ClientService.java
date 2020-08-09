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
package com.emotome.common.client.service;

import com.emotome.common.client.model.ClientModel;
import com.emotome.common.client.view.ClientView;
import com.emotome.common.model.PageModel;
import com.emotome.common.service.BaseService;

/**
 * 
 * @author Jaydip
 * @since 22/04/2019
 */
public interface ClientService extends BaseService<ClientModel> {

	String CLIENT_MODEL = "clientModel";
	String LIGHT_CLIENT_MODEL = "lightClientModel";
	String EXTRA_LIGHT_CLIENT_MODEL = "extraLightClientModel";

	/**
	 * This method is used to get hospital with light entity.
	 * 
	 * @param id
	 * @return
	 */
	ClientModel getLight(Long id);

	/**
	 * This method is used to get hospital with extra light entity.
	 * 
	 * @param id
	 * @return
	 */
	ClientModel getExtraLight(Long id);

	/**
	 * This method is used to get hospital information based on hospital key.
	 * 
	 * @param id
	 * @return
	 */
	ClientModel getLight(String apiKey);

	/**
	 * Light search.
	 * 
	 * @param clientView
	 * @param start
	 * @param recordSize
	 * @return
	 */
	PageModel searchLight(ClientView clientView, Integer start, Integer recordSize);
}