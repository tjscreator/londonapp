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
package com.emotome.common.location.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emotome.common.aop.AccessLog;
import com.emotome.common.controller.BaseController;
import com.emotome.common.location.view.CountryView;
import com.emotome.common.response.Response;
import com.emotome.harbor.exception.HarborException;

/**
 * 
 * @author Nirav
 * @since 09/11/2017
 *
 */

public interface CountryController extends BaseController<CountryView> {
	
	/**
	 * This method is used to search country
	 * @param start
	 * @return
	 * @throws HarborException
	 */
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody
	@AccessLog
	Response search(@RequestParam(name="start", required=false) Integer start) throws HarborException;
	
	/**
	 * Prepare a country dropdown.
	 * @return
	 * @throws HarborException
	 */
	@RequestMapping(value = "/dropdown", method = RequestMethod.GET)
	@ResponseBody
	@AccessLog
	Response dropdown() throws HarborException;

}
