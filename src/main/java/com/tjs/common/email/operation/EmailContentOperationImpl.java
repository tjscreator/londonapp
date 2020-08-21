package com.tjs.common.email.operation;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tjs.common.email.model.EmailAccountModel;
import com.tjs.common.email.model.EmailContentModel;
import com.tjs.common.email.service.EmailAccountService;
import com.tjs.common.email.service.EmailContentService;
import com.tjs.common.email.view.EmailAccountView;
import com.tjs.common.email.view.EmailContentView;
import com.tjs.common.enums.ResponseCode;
import com.tjs.common.model.PageModel;
import com.tjs.common.operation.AbstractOperation;
import com.tjs.common.response.CommonResponse;
import com.tjs.common.response.PageResultResponse;
import com.tjs.common.response.Response;
import com.tjs.common.response.ViewResponse;
import com.tjs.common.service.BaseService;
import com.tjs.common.threadlocal.Auditor;
import com.tjs.common.view.KeyValueView;
import com.tjs.harbor.exception.HarborException;

/**
 * This class used to perform all business operation on program model.
 * 
 * @author Nisha.Panchal
 * @since 23/07/2018
 */

@Component(value = "emailContentOperation")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class EmailContentOperationImpl extends AbstractOperation<EmailContentModel, EmailContentView>
		implements EmailContentOperation {
	@Autowired
	EmailContentService emailContentService;

	@Autowired
	EmailAccountService emailAccountService;

	@Autowired
	EmailAccountOperationImpl emailAccountOperation;

	@Override
	public Response doAdd() throws HarborException {
		return null;
	}

	@Override
	public Response doView(Long id) throws HarborException {
		EmailContentModel emailContentModel = getEmailContent(id);
		EmailContentView emailContentView = fromModel(emailContentModel);
		setEmailAccountView(emailContentModel, emailContentView);
		return ViewResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(),
				emailContentView);
	}

	public EmailContentModel getEmailContent(Long id) throws HarborException {
		EmailContentModel emailAccountModel = EmailContentModel.getMAP().get(id);
		if (emailAccountModel == null) {
			emailAccountModel = emailContentService.get(id);
			if (emailAccountModel == null) {
				throw new HarborException(ResponseCode.NO_DATA_FOUND.getCode(),
						ResponseCode.NO_DATA_FOUND.getMessage());
			}
		}
		return emailAccountModel;
	}

	@Override
	public Response doEdit(Long id) throws HarborException {
		return doView(id);
	}

	@Override
	protected EmailContentModel loadModel(EmailContentView emailContentView) {
		return emailContentService.get(emailContentView.getId());
	}

	@Override
	public Response doDelete(Long id) throws HarborException {
		EmailContentModel emailContentModel = getEmailContent(id);
		emailContentModel.setArchive(true);
		emailContentService.delete(emailContentModel);
		EmailContentModel.removeEmailContent(emailContentModel);
		return CommonResponse.create(ResponseCode.DELETE_SUCCESSFULLY.getCode(),
				ResponseCode.DELETE_SUCCESSFULLY.getMessage());
	}

	@Override
	public Response doActiveInActive(Long id) throws HarborException {
		EmailContentModel emailContentModel = getEmailContent(id);
		emailContentModel.setActive(false);
		Auditor.activationAudit(emailContentModel, false);
		emailContentService.update(emailContentModel);
		EmailContentModel.removeEmailContent(emailContentModel);
		return CommonResponse.create(ResponseCode.UPDATE_SUCCESSFULLY.getCode(),
				ResponseCode.UPDATE_SUCCESSFULLY.getMessage());
	}

	@Override
	public EmailContentModel toModel(EmailContentModel emailContentModel, EmailContentView emailContentView) {
		emailContentModel.setName(emailContentView.getName());
		emailContentModel.setSubject(emailContentView.getSubject());
		emailContentModel.setContent(emailContentView.getContent());
		emailContentModel.setEmailBcc(emailContentView.getEmailBcc());
		emailContentModel.setEmailCc(emailContentView.getEmailCc());
		return emailContentModel;
	}

	@Override
	protected EmailContentModel getNewModel() {
		return new EmailContentModel();
	}

	@Override
	public EmailContentView fromModel(EmailContentModel emailContentModel) {
		EmailContentView emailContentView = new EmailContentView();
		emailContentView.setId(emailContentModel.getId());
		emailContentView.setName(emailContentModel.getName());
		emailContentView.setSubject(emailContentModel.getSubject());
		emailContentView.setContent(emailContentModel.getContent());
		emailContentView.setEmailBcc(emailContentModel.getEmailBcc());
		emailContentView.setEmailCc(emailContentModel.getEmailCc());
		emailContentView.setTrigger(KeyValueView.create(emailContentModel.getTriggerId().getId(),
				emailContentModel.getTriggerId().getName()));
		return emailContentView;
	}

	@Override
	public BaseService<EmailContentModel> getService() {
		return emailContentService;
	}

	@Override
	protected void checkInactive(EmailContentModel model) throws HarborException {

	}

	@Override
	public Response doSave(EmailContentView emailContentView) throws HarborException {
		EmailContentModel emailContentModel = emailContentService.findByName(emailContentView.getName());
		if (emailContentModel != null) {
			throw new HarborException(ResponseCode.ALREADY_EXIST.getCode(),
					"name " + ResponseCode.ALREADY_EXIST.getMessage());
		}
		emailContentModel = emailContentService.findByTrigger(emailContentView.getTrigger().getKey().intValue());
		if (emailContentModel != null) {
			throw new HarborException(ResponseCode.ALREADY_EXIST.getCode(),
					"Email Content with given trigger " + ResponseCode.ALREADY_EXIST.getMessage());
		}
		emailContentModel = getModel(emailContentView);
		emailContentModel.setEmailAccountId(emailContentView.getEmailAccountView().getId());
		emailContentModel.setActive(true);
		emailContentModel.setTriggerId(emailContentView.getTrigger().getKey().intValue());
		getService().create(emailContentModel);
		EmailContentModel.addEmailContent(emailContentModel);
		return CommonResponse.create(ResponseCode.SAVE_SUCCESSFULLY.getCode(),
				ResponseCode.SAVE_SUCCESSFULLY.getMessage());
	}

	@Override
	public Response doUpdate(EmailContentView emailContentView) throws HarborException {
		EmailContentModel emailContentModel = getEmailContent(emailContentView.getId());
		if (!emailContentView.getName().equals(emailContentModel.getName())) {
			emailContentModel = emailContentService.findByName(emailContentView.getName());
			if (emailContentModel != null) {
				throw new HarborException(ResponseCode.ALREADY_EXIST.getCode(),
						"name " + ResponseCode.ALREADY_EXIST.getMessage());
			}
		}
		if (emailContentView.getTrigger().getKey().intValue() != emailContentModel.getTriggerId().getId().intValue()) {
			emailContentModel = emailContentService.findByTrigger(emailContentView.getTrigger().getKey().intValue());
			if (emailContentModel != null) {
				throw new HarborException(ResponseCode.ALREADY_EXIST.getCode(),
						"Email Content with given trigger " + ResponseCode.ALREADY_EXIST.getMessage());
			}
		}
		emailContentModel.setEmailAccountId(emailContentView.getEmailAccountView().getId());
		emailContentModel.setTriggerId(emailContentView.getTrigger().getKey().intValue());
		getService().update(toModel(emailContentModel, emailContentView));
		EmailContentModel.updateEmailContent(emailContentModel);
		return CommonResponse.create(ResponseCode.UPDATE_SUCCESSFULLY.getCode(),
				ResponseCode.UPDATE_SUCCESSFULLY.getMessage());
	}

	@Override
	public Response doSearch(EmailContentView emailContentView, Integer start, Integer recordSize)
			throws HarborException {
		PageModel result = emailContentService.searchLight(emailContentView, start, recordSize);
		if (result.getRecords() == 0) {
			return PageResultResponse.create(ResponseCode.NO_DATA_FOUND.getCode(),
					ResponseCode.NO_DATA_FOUND.getMessage(), 0, Collections.EMPTY_LIST);
		}
		return PageResultResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(),
				result.getRecords(), fromModelList((List<EmailContentModel>) result.getList()));
	}

	@Override
	public Response doDisplayGrid(Integer start, Integer recordSize) {
		PageModel result = emailContentService.getGridData(start, recordSize);
		if (result.getRecords() == 0) {
			return PageResultResponse.create(ResponseCode.NO_DATA_FOUND.getCode(),
					ResponseCode.NO_DATA_FOUND.getMessage(), 0, Collections.EMPTY_LIST);
		}
		return PageResultResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(),
				result.getRecords(), fromModelList((List<EmailContentModel>) result.getList()));
	}

	private void setEmailAccountView(EmailContentModel emailContentModel, EmailContentView emailContentView) {
		EmailAccountModel emailAccountModel = emailAccountService.getLight(emailContentModel.getEmailAccountId());
		EmailAccountView emailAccountView = new EmailAccountView();
		emailAccountView.setName(emailAccountModel.getName());
		emailAccountView.setId(emailAccountModel.getId());
		emailContentView.setEmailAccountView(emailAccountView);
	}

}