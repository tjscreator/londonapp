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
package com.emotome.common.captcha.operation;

import com.emotome.common.captcha.model.CaptchaModel;
import com.emotome.common.captcha.service.CaptchaService;
import com.emotome.common.captcha.view.CaptchaView;
import com.emotome.common.operation.Operation;
import com.emotome.common.response.Response;
import com.emotome.harbor.exception.HarborException;

/**
 *
 * @author Nirav.Shah
 * @version 1.0
 * @since 17/12/2018
 */

public interface CaptchaOperation extends Operation {

	/**
	 * This method is used to save captcha details.
	 * @param captcha
	 * @param uuid
	 * @return
	 * @throws HarborException
	 */
	Response doSave(String captcha, String uuid) throws HarborException;

	

	/**
	 * This method is used to prepare model from view.
	 * @param CaptchaModel
	 * @param captcha
	 * @return
	 */
	CaptchaModel toModel(CaptchaModel CaptchaModel, String captcha, String uuid);

	/**
	 * This method is used to prepare model from view.
	 * 
	 * @param request
	 * @return
	 */
	CaptchaModel getModel(CaptchaView captchaView);

	/**
	 * This method used when require new model for view
	 * 
	 * @param view
	 *            view of model
	 * @return model
	 */
	CaptchaModel getNewModel();

	/**
	 * This method used when need to convert model to view
	 * 
	 * @param model
	 * @return view
	 */

	CaptchaView fromModel(CaptchaModel CaptchaModel);

	/**
	 * This method use for get Service with respected operation
	 * 
	 * @return FileService
	 */

	CaptchaService getService();

	/**
	 * This method is used to get captcha details using captcha id.
	 * 
	 * @param fileId
	 * @return
	 * @throws IAException
	 */
	CaptchaModel get(String id) throws HarborException;
}
