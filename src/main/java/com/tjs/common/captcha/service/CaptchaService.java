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
package com.tjs.common.captcha.service;

import com.tjs.common.captcha.model.CaptchaModel;
import com.tjs.common.service.BaseService;

/**
 * 
 * @author Nirav
 * @since 17/12/2018
 */
public interface CaptchaService extends BaseService<CaptchaModel> {
	String CAPTCHA_MODEL = "captchaModel";
	
	/**
	 * This method is used to get File value base on given captcha id.
	 * 
	 * @param id
	 * @return
	 */
	CaptchaModel get(String id);

	/**
	 * This method is used to delete attachment.
	 * 
	 * @param fileModel
	 */
	void delete(CaptchaModel captchaModel);
}
