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
package com.tjs.common.captcha.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tjs.common.captcha.model.CaptchaModel;
import com.tjs.common.captcha.service.CaptchaService;
import com.tjs.common.captcha.view.CaptchaView;
import com.tjs.common.enums.ResponseCode;
import com.tjs.common.response.Response;
import com.tjs.common.response.ViewResponse;
import com.tjs.common.util.DateUtility;
import com.tjs.harbor.exception.HarborException;

/**
 * This class used to perform all business operation on captcha model.
 * 
 * @author Nirav.Shah
 * @since 17/12/2018
 */
@Component(value = "captchaOperation")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Throwable.class)
public class CaptchaOperationImpl implements CaptchaOperation {

	@Autowired
	private CaptchaService captchaService;

	@Override
	public CaptchaService getService() {
		return captchaService;
	}

	@Override
	public Response doSave(String captcha, String uuid) throws HarborException {
		CaptchaModel captchaModel = toModel(getNewModel(), captcha, uuid);
		captchaService.create(captchaModel);
		return ViewResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(),
				fromModel(captchaModel));
	}

	@Override
	public CaptchaModel toModel(CaptchaModel captchaModel, String captcha, String uuid) {
		captchaModel.setCreateDate(DateUtility.getCurrentEpoch());
		captchaModel.setValue(captcha);
		captchaModel.setId(uuid);
		return captchaModel;
	}

	@Override
	public CaptchaModel getModel(CaptchaView captchaView) {
		return captchaService.get(captchaView.getId());
	}

	@Override
	public CaptchaModel getNewModel() {
		return new CaptchaModel();
	}

	@Override
	public CaptchaView fromModel(CaptchaModel captchaModel) {
		CaptchaView captchaView = new CaptchaView();
		captchaView.setId(captchaModel.getId());
		return captchaView;
	}


	@Override
	public CaptchaModel get(String id) throws HarborException {
		CaptchaModel captchaModel = captchaService.get(id);
		if (captchaModel == null) {
			throw new HarborException(ResponseCode.INVALID_CAPTCHA.getCode(),
					ResponseCode.INVALID_CAPTCHA.getMessage());
		}
		return captchaModel;
	}
}