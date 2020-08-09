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
package com.emotome.harbor.email.enums;

import java.util.HashMap;
import java.util.Map;

import com.emotome.common.email.model.EmailContentModel;
import com.emotome.common.email.model.TransactionalEmailModel;
import com.emotome.common.email.service.EmailContentService;
import com.emotome.common.email.service.TransactionalEmailService;
import com.emotome.common.model.Model;
import com.emotome.common.modelenums.ModelEnum;
import com.emotome.common.util.DateUtility;

/**
 * This is enum type of all email trigger.
 * 
 * @author Vishwa.Shah
 * @since 10/08/2018
 */
public enum CommunicationTriggerEnum implements ModelEnum {

	USER_CREATE(1, "User Create") {

		@Override
		public void prepareCommunicationDetail(Model model, EmailContentService emailContentService,
				TransactionalEmailService transactionalEmailService) {
			// TODO Auto-generated method stub

		}
	};

	private final Integer id;
	private final String name;

	public static final Map<Integer, CommunicationTriggerEnum> MAP = new HashMap<>();

	static {
		for (CommunicationTriggerEnum communicationTriggerEnum : values()) {
			MAP.put(communicationTriggerEnum.getId(), communicationTriggerEnum);
		}
	}

	CommunicationTriggerEnum(Integer id, String name) {
		this.id = id;
		this.name = name;
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

	public abstract void prepareCommunicationDetail(Model model, EmailContentService emailContentService,
			TransactionalEmailService transactionalEmailService);

	private static void emailSingleInsert(String Content, String email, EmailContentModel emailContentModel,
			TransactionalEmailService transactionalEmailService) {
		TransactionalEmailModel transactionalEmailModel = new TransactionalEmailModel();
		transactionalEmailModel.setBody(Content);
		transactionalEmailModel.setEmailAccountId(emailContentModel.getEmailAccountId());
		transactionalEmailModel.setEmailTo(email);
		transactionalEmailModel.setStatus(Status.NEW.getId());
		transactionalEmailModel.setSubject(emailContentModel.getSubject());
		transactionalEmailModel.setEmailBcc(emailContentModel.getEmailBcc());
		transactionalEmailModel.setDateSend(DateUtility.getCurrentEpoch());
		transactionalEmailModel.setActive(true);
		transactionalEmailService.create(transactionalEmailModel);
	}
}