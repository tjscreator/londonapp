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
package com.tjs.common.sms.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tjs.common.enums.ResponseCode;
import com.tjs.common.operation.AbstractOperation;
import com.tjs.common.response.CommonResponse;
import com.tjs.common.response.Response;
import com.tjs.common.response.ViewResponse;
import com.tjs.common.service.BaseService;
import com.tjs.common.sms.model.SmsContentModel;
import com.tjs.common.sms.service.SmsContentService;
import com.tjs.common.sms.view.SmsContentView;
import com.tjs.harbor.exception.HarborException;

/**
 * This class used to perform all business operation on sms model.
 * 
 * @author JD
 * @since 28/08/2019
 */
@Component(value = "smsContentOperation")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class SmsContentOperationImpl extends AbstractOperation<SmsContentModel, SmsContentView>
		implements SmsContentOperation {

	@Autowired
	private SmsContentService smsContentService;

	@Override
	public Response doAdd() throws HarborException {
		return null;
	}

	@Override
	public Response doView(Long id) throws HarborException {
		SmsContentModel smsContentModel = smsContentService.get(id);
		if (smsContentModel == null) {
			throw new HarborException(ResponseCode.NO_DATA_FOUND.getCode(), ResponseCode.NO_DATA_FOUND.getMessage());
		}
		return ViewResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(),
				fromModel(smsContentModel));
	}

	@Override
	public Response doEdit(Long id) throws HarborException {
		return doView(id);
	}

	@Override
	protected SmsContentModel loadModel(SmsContentView smsContentView) {
		return smsContentService.load(smsContentView.getId());
	}

	@Override
	public Response doDelete(Long id) throws HarborException {
		return null;
	}

	@Override
	public Response doActiveInActive(Long id) throws HarborException {
		return null;
	}

	@Override
	public Response doUpdate(SmsContentView smsContentView) throws HarborException {
		SmsContentModel smsContentModel = smsContentService.get(smsContentView.getId());
		if (smsContentModel == null) {
			throw new HarborException(ResponseCode.NO_DATA_FOUND.getCode(), ResponseCode.NO_DATA_FOUND.getMessage());
		}
		toModel(smsContentModel, smsContentView);
		smsContentService.update(smsContentModel);
		return CommonResponse.create(ResponseCode.UPDATE_SUCCESSFULLY.getCode(),
				ResponseCode.UPDATE_SUCCESSFULLY.getMessage());
	}

	@Override
	public Response doSearch(SmsContentView smsContentView, Integer start, Integer recordSize) throws HarborException{
		return super.doSearch(smsContentView, start, recordSize);
	}

	@Override
	public SmsContentModel toModel(SmsContentModel smsContentModel, SmsContentView smsContentView) {
		smsContentModel.setSubject(smsContentView.getSubject());
		smsContentModel.setContent(smsContentView.getContent());
		return smsContentModel;
	}

	@Override
	protected SmsContentModel getNewModel() {
		return new SmsContentModel();
	}

	@Override
	public SmsContentView fromModel(SmsContentModel smsContentModel) {
		SmsContentView smsContentView = new SmsContentView();
		smsContentView.setId(smsContentModel.getId());
		smsContentView.setSubject(smsContentModel.getSubject());
		smsContentView.setContent(smsContentModel.getContent());
		return smsContentView;
	}

	@Override
	public BaseService getService() {
		return smsContentService;
	}

	@Override
	protected void checkInactive(SmsContentModel model) throws HarborException {
	}

}
