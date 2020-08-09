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
package com.emotome.common.setting.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emotome.common.aop.AccessLog;
import com.emotome.common.controller.Controller;
import com.emotome.common.response.Response;
import com.emotome.common.setting.view.SettingView;
import com.emotome.harbor.exception.HarborException;

/**
 * 
 * @author Nirav
 * @since 24/11/2018
 *
 */
public interface SettingController extends Controller {

	/**
	 * This method is used to handle view request coming from hospital for any
	 * module.
	 * 
	 * @param id
	 * @return
	 * @throws HarborException
	 */
	// TODO authorization annotation
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	@ResponseBody
	@AccessLog
	Response view(@RequestParam(name = "key", required = true) String key) throws HarborException;

	/**
	 * This method is used to handle edit request coming from hospital for any
	 * module.
	 * 
	 * @param id
	 * @return
	 * @throws HarborException
	 */
	// TODO authorization annotation
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	@ResponseBody
	@AccessLog
	Response edit(@RequestParam(name = "key", required = true) String key) throws HarborException;

	/**
	 * This method is used to handle update request coming from hospital for any
	 * module.
	 * 
	 * @param settingView
	 * @return
	 * @throws HarborException
	 */
	// TODO authorization annotation
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseBody
	@AccessLog
	Response update(@RequestBody SettingView settingView) throws HarborException;

	/**
	 * This method is used to handle search request coming from hospital for any
	 * module.
	 * 
	 * @param settingView
	 * @return
	 * @throws HarborException
	 */
	// TODO authorization annotation
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	@AccessLog
	Response search(@RequestBody(required = false) SettingView settingView,
			@RequestParam(name = "start", required = false) Integer start,
			@RequestParam(name = "recordSize", required = false) Integer recordSize) throws HarborException;
}
