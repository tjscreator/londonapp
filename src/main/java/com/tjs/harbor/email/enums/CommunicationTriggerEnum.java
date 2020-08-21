/*******************************************************************************
 * Copyright -2017 @Emotome
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
package com.tjs.harbor.email.enums;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.text.StrSubstitutor;

import com.tjs.common.email.model.EmailContentModel;
import com.tjs.common.email.model.TransactionalEmailModel;
import com.tjs.common.email.service.EmailContentService;
import com.tjs.common.email.service.TransactionalEmailService;
import com.tjs.common.logger.LoggerService;
import com.tjs.common.modelenums.ModelEnum;
import com.tjs.common.util.DateUtility;

/**
 * This is enum type of all email trigger.
 * 
 * @author Vishwa.Shah
 * @since 10/08/2018
 */
public enum CommunicationTriggerEnum implements ModelEnum {

	CLIENT_CREATE_BY_ADMIN(1, "Client create by admin", false) {
		@Override
		public void prepareCommunicationDetail(Map<String, String> dynamicField,
				EmailContentService emailContentService, TransactionalEmailService transactionalEmailService) {
			EmailContentModel emailContentModel = emailContentService.findByTrigger(CLIENT_CREATE_BY_ADMIN.getId());
			if (emailContentModel == null) {
				LoggerService.error("Unable to find template for " + CLIENT_CREATE_BY_ADMIN.getName() + " trigger");
				return;
			}

			Map<String, String> dynamicFields = new HashMap<String, String>();
			dynamicFields.put(CommunicationFields.USER_NAME.getName(),
					dynamicField.get(CommunicationFields.USER_NAME.getName()));
			dynamicFields.put(CommunicationFields.EMAIL.getName(),
					dynamicField.get(CommunicationFields.EMAIL.getName()));
			dynamicFields.put(CommunicationFields.PASSWORD.getName(),
					dynamicField.get(CommunicationFields.PASSWORD.getName()));
			dynamicFields.put(CommunicationFields.URL.getName(), dynamicField.get(CommunicationFields.URL.getName()));
			dynamicFields.put(CommunicationFields.CLIENT.getName(),
					dynamicField.get(CommunicationFields.CLIENT.getName()));

			StrSubstitutor sub = new StrSubstitutor(dynamicFields);
			String content = sub.replace(emailContentModel.getContent());
			emailSingleInsert(content, dynamicField.get(CommunicationFields.EMAIL.getName()), emailContentModel,
					transactionalEmailService);
		}
	},
	USER_RESET_PASSWORD(2, "Reset password", false) {
		@Override
		public void prepareCommunicationDetail(Map<String, String> dynamicField,
				EmailContentService emailContentService, TransactionalEmailService transactionalEmailService) {
			EmailContentModel emailContentModel = emailContentService.findByTrigger(USER_RESET_PASSWORD.getId());
			if (emailContentModel == null) {
				LoggerService.error("Unable to find template for " + USER_RESET_PASSWORD.getName() + " trigger");
				return;
			}

			Map<String, String> dynamicFields = new HashMap<String, String>();
			dynamicFields.put(CommunicationFields.USER_NAME.getName(),
					dynamicField.get(CommunicationFields.USER_NAME.getName()));
			dynamicFields.put(CommunicationFields.URL.getName(),
					dynamicField.get(CommunicationFields.URL.getName()) + File.separator + "reset-password"
							+ File.separator + dynamicField.get(CommunicationFields.RESET_PASSWORD_TOKEN.getName()));
			StrSubstitutor sub = new StrSubstitutor(dynamicFields);
			String content = sub.replace(emailContentModel.getContent());
			emailSingleInsert(content, dynamicField.get(CommunicationFields.EMAIL.getName()), emailContentModel,
					transactionalEmailService);
		}
	};

	private final Integer id;
	private final String name;
	private final boolean isEditableByClient;

	public static final Map<Integer, CommunicationTriggerEnum> MAP = new HashMap<>();

	static {
		for (CommunicationTriggerEnum communicationTriggerEnum : values()) {
			MAP.put(communicationTriggerEnum.getId(), communicationTriggerEnum);
		}
	}

	CommunicationTriggerEnum(Integer id, String name, boolean isEditableByClient) {
		this.id = id;
		this.name = name;
		this.isEditableByClient = isEditableByClient;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	public static CommunicationTriggerEnum fromId(Integer id) {
		return MAP.get(id);
	}

	/**
	 * Implemented by all triggers
	 * 
	 * @param dynamicField
	 * @param emailContentService
	 * @param transactionalEmailService
	 */
	public abstract void prepareCommunicationDetail(Map<String, String> dynamicField,
			EmailContentService emailContentService, TransactionalEmailService transactionalEmailService);

	private static void emailSingleInsert(String Content, String email, EmailContentModel emailContentModel,
			TransactionalEmailService transactionalEmailService) {
		TransactionalEmailModel transactionalEmailModel = new TransactionalEmailModel();
		transactionalEmailModel.setBody(Content);
		transactionalEmailModel.setEmailAccountId(emailContentModel.getEmailAccountId());
		transactionalEmailModel.setEmailTo(email);
		transactionalEmailModel.setStatus(EmailStatusEnum.NEW.getId());
		transactionalEmailModel.setSubject(emailContentModel.getSubject());
		transactionalEmailModel.setEmailCc(emailContentModel.getEmailCc());
		transactionalEmailModel.setEmailBcc(emailContentModel.getEmailBcc());
		transactionalEmailModel.setDateSend(DateUtility.getCurrentEpoch());
		transactionalEmailService.create(transactionalEmailModel);
	}

	public boolean isEditableByClient() {
		return isEditableByClient;
	}

}