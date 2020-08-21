package com.tjs.common.email.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tjs.common.email.model.EmailAccountModel;
import com.tjs.common.email.service.EmailAccountService;
import com.tjs.common.email.view.EmailAccountView;
import com.tjs.common.enums.ResponseCode;
import com.tjs.common.operation.AbstractOperation;
import com.tjs.common.response.CommonResponse;
import com.tjs.common.response.Response;
import com.tjs.common.response.ViewResponse;
import com.tjs.common.service.BaseService;
import com.tjs.common.threadlocal.Auditor;
import com.tjs.common.view.KeyValueView;
import com.tjs.harbor.exception.HarborException;
/**
 * This class used to perform all business operation on program model.
 * @author Nisha.Panchal
 * @since 17/07/2018
 *
 */
@Component(value = "emailAccountOperation")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class EmailAccountOperationImpl extends AbstractOperation<EmailAccountModel, EmailAccountView>
		implements EmailAccountOperation {

	@Autowired
	EmailAccountService emailAccountService;

	@Override
	public Response doAdd() throws HarborException {
		return null;
	}

	@Override
	public Response doView(Long id) throws HarborException {
		EmailAccountModel emailAccountModel = getEmailAccount(id);
		EmailAccountView emailAccountView = fromModel(emailAccountModel);
		return ViewResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(),
				emailAccountView);
	}

	public EmailAccountModel getEmailAccount(Long id) throws HarborException {
		EmailAccountModel emailAccountModel = EmailAccountModel.getMAP().get(id);
		if (emailAccountModel == null) {
			emailAccountModel = emailAccountService.get(id);
			if(emailAccountModel == null) {
				throw new HarborException(ResponseCode.NO_DATA_FOUND.getCode(), ResponseCode.NO_DATA_FOUND.getMessage());
			}			
		}
		return emailAccountModel;
	}
	
	@Override
	public Response doEdit(Long id) throws HarborException {
		return doView(id);
	}

	@Override
	protected EmailAccountModel loadModel(EmailAccountView emailAccountView) {
		return emailAccountService.get(emailAccountView.getId());
	}

	@Override
	public Response doDelete(Long id) throws HarborException {
		EmailAccountModel emailAccountModel = getEmailAccount(id);
		emailAccountModel.setArchive(true);
		emailAccountService.delete(emailAccountModel);
		EmailAccountModel.removeEmailAccount(emailAccountModel);
		return CommonResponse.create(ResponseCode.DELETE_SUCCESSFULLY.getCode(),
				ResponseCode.DELETE_SUCCESSFULLY.getMessage());
	}

	@Override
	public Response doActiveInActive(Long id) throws HarborException {
		EmailAccountModel emailAccountModel = getEmailAccount(id);
		emailAccountModel.setActive(false);
		Auditor.activationAudit(emailAccountModel, false);
		emailAccountService.update(emailAccountModel);
		EmailAccountModel.removeEmailAccount(emailAccountModel);
		return CommonResponse.create(ResponseCode.UPDATE_SUCCESSFULLY.getCode(),
				ResponseCode.UPDATE_SUCCESSFULLY.getMessage());
	}

	@Override
	public EmailAccountModel toModel(EmailAccountModel emailAccountModel, EmailAccountView emailAccountView) {
		emailAccountModel.setName(emailAccountView.getName());
		emailAccountModel.setHost(emailAccountView.getHost());
		emailAccountModel.setPort(emailAccountView.getPort());
		emailAccountModel.setUsername(emailAccountView.getUsername());
		emailAccountModel.setPassword(emailAccountView.getPassword());
		emailAccountModel.setReplyToEmail(emailAccountView.getReplyToEmail());
		emailAccountModel.setEmailFrom(emailAccountView.getEmailFrom());
		emailAccountModel.setRatePerHour(emailAccountView.getRatePerHour());
		emailAccountModel.setUpdateRatePerHour(emailAccountView.getUpdateRatePerHour());
		emailAccountModel.setDateRatePerHour(emailAccountView.getDateRatePerHour());
		emailAccountModel.setRatePerDay(emailAccountView.getRatePerDay());
		emailAccountModel.setUpdateRatePerDay(emailAccountView.getUpdateRatePerDay());
		emailAccountModel.setDateRatePerDay(emailAccountView.getDateRatePerDay());
		emailAccountModel.setAuthenticationMethod(Integer.parseInt(String.valueOf(emailAccountView.getAuthenticationMethod().getKey())));
		emailAccountModel
				.setAuthenticationSecurity(Integer.parseInt(String.valueOf(emailAccountView.getAuthenticationSecurity().getKey())));
		emailAccountModel.setTimeOut(emailAccountView.getTimeOut());
		return emailAccountModel;
	}

	@Override
	protected EmailAccountModel getNewModel() {
		return new EmailAccountModel();
	}

	@Override
	public EmailAccountView fromModel(EmailAccountModel emailAccountModel) {
		EmailAccountView emailAccountView = new EmailAccountView();
		emailAccountView.setId(emailAccountModel.getId());
		emailAccountView.setName(emailAccountModel.getName());
		emailAccountView.setHost(emailAccountModel.getHost());
		emailAccountView.setPort(emailAccountModel.getPort());
		emailAccountView.setUsername(emailAccountModel.getUsername());
		emailAccountView.setPassword(emailAccountModel.getPassword());
		emailAccountView.setReplyToEmail(emailAccountModel.getReplyToEmail());
		emailAccountView.setEmailFrom(emailAccountModel.getEmailFrom());
		emailAccountView.setRatePerHour(emailAccountModel.getRatePerHour());
		emailAccountView.setUpdateRatePerHour(emailAccountModel.getUpdateRatePerHour());
		emailAccountView.setDateRatePerHour(emailAccountModel.getDateRatePerHour());
		emailAccountView.setRatePerDay(emailAccountModel.getRatePerDay());
		emailAccountView.setUpdateRatePerDay(emailAccountModel.getUpdateRatePerDay());
		emailAccountView.setDateRatePerDay(emailAccountModel.getDateRatePerDay());
		emailAccountView.setAuthenticationMethod(
				KeyValueView.create(Long.valueOf(emailAccountModel.getAuthenticationMethod().getId()),
						emailAccountModel.getAuthenticationMethod().getName()));
		emailAccountView.setAuthenticationSecurity(
				KeyValueView.create(Long.valueOf(emailAccountModel.getAuthenticationSecurity().getId()),
						emailAccountModel.getAuthenticationSecurity().getName()));
		emailAccountView.setTimeOut(emailAccountModel.getTimeOut());
		return emailAccountView;
	}

	@Override
	public BaseService<EmailAccountModel> getService() {
		return emailAccountService;
	}

	@Override
	protected void checkInactive(EmailAccountModel emailAccountModel) throws HarborException {
		
	}
	
	@Override
	public Response doSave(EmailAccountView emailAccountView) throws HarborException {
		EmailAccountModel emailAccountModel = getModel(emailAccountView);
		emailAccountModel.setActive(true);
		getService().create(emailAccountModel);
		EmailAccountModel.addEmailAccount(emailAccountModel);
		return CommonResponse.create(ResponseCode.SAVE_SUCCESSFULLY.getCode(),	ResponseCode.SAVE_SUCCESSFULLY.getMessage());
	}
	
	@Override
	public Response doUpdate(EmailAccountView emailAccountView) throws HarborException {
		EmailAccountModel emailAccountModel = getEmailAccount(emailAccountView.getId());
		getService().update(toModel(emailAccountModel, emailAccountView));
		EmailAccountModel.updateEmailAccount(emailAccountModel);
		return CommonResponse.create(ResponseCode.UPDATE_SUCCESSFULLY.getCode(), ResponseCode.UPDATE_SUCCESSFULLY.getMessage());
	}
}