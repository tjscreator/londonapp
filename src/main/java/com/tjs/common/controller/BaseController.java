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
package com.tjs.common.controller;

import com.tjs.common.response.Response;
import com.tjs.common.view.View;
import com.tjs.harbor.exception.HarborException;

/**
 * It is used to define basic request that can come for any module in system.
 * 
 * @author Nirav.Shah
 * @since 08/08/2018
 */
public interface BaseController<V extends View> extends Controller {

	/**
	 * This method is used to validate add request to add any entity into system.
	 * Once it is been validate. it allow users to add entity data.
	 * @return
	 * @throws HarborException
	 */
	Response add() throws HarborException;
	
	/**
	 * This method is used to handle save request coming from hospital for any
	 * module.
	 * @param view
	 * @return
	 * @throws HarborException
	 */
	Response save(V view) throws HarborException;
	
	
	/**
	 * This method is used to handle view request coming from hospital for any
	 * module.
	 * @param id
	 * @return
	 * @throws HarborException
	 */
	Response view(Long id) throws HarborException;
	
	/**
	 * This method is used to handle edit request coming from hospital for any
	 * module.
	 * @param id
	 * @return
	 * @throws HarborException
	 */
	Response edit(Long id) throws HarborException;

	/**
	 * This method is used to handle update request coming from hospital for 
	 * any module.
	 * @param request
	 * @return
	 * @throws HarborException
	 */
	Response update(V view) throws HarborException;

	/**
	 * This method is used to handle delete request coming from hospital for 
	 * any module.
	 * @param id
	 * @return
	 * @throws HarborException
	 */
	Response delete(Long id) throws HarborException;
	
	/**
	 * This method is used to hanle active/inactive request.
	 * @param id
	 * @return
	 * @throws HarborException
	 */
	Response activeInActive(Long id) throws HarborException;
	
	/**
	 * This method is used to handle grid request coming from hospital for any
	 * module.
	 * @param start
	 * @param recordSize
	 * @return
	 * @throws HarborException
	 */
	Response displayGrid(Integer start, Integer recordSize) throws HarborException;
	
	/**
	 * This method is used to handle search request coming from hospital for any
	 * module.
	 * @param request
	 * @return
	 * @throws HarborException
	 */
	Response search(V view, Integer start, Integer recordSize) throws HarborException;
}
