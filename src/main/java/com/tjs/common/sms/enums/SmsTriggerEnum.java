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
package com.tjs.common.sms.enums;

import java.util.HashMap;
import java.util.Map;

import com.tjs.common.model.Model;
import com.tjs.common.modelenums.ModelEnum;
import com.tjs.common.sms.model.SmsContentModel;
import com.tjs.common.sms.model.SmsTransactionModel;
import com.tjs.common.sms.service.SmsContentService;
import com.tjs.common.sms.service.SmsTransactionService;
import com.tjs.common.user.model.UserModel;
import com.tjs.common.util.DateUtility;
import com.tjs.harbor.email.enums.Status;

/**
 * This is enum type of all sms trigger.
 * 
 * @author JD
 * @since 16/05/2019
 */
public enum SmsTriggerEnum implements ModelEnum {

	USER_CREATE(1, "User Create") {

		@Override
		public void prepareCommunicationDetail(Model model, SmsContentService smsContentService,
				SmsTransactionService smsTransactionService) {
			// TODO Auto-generated method stub

		}
	};

	private final Integer id;
	private final String name;

	public static final Map<Integer, SmsTriggerEnum> MAP = new HashMap<>();

	static {
		for (SmsTriggerEnum smsTriggerEnum : values()) {
			MAP.put(smsTriggerEnum.getId(), smsTriggerEnum);
		}
	}

	SmsTriggerEnum(Integer id, String name) {
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

	public static SmsTriggerEnum fromId(Integer id) {
		return MAP.get(id);
	}

	public abstract void prepareCommunicationDetail(Model model, SmsContentService smsContentService,
			SmsTransactionService smsTransactionService);

	private static void smsSingleInsert(String Content, UserModel userModel, SmsContentModel smsContentModel,
			SmsTransactionService smsTransactionService) {
		SmsTransactionModel smsTransactionModel = new SmsTransactionModel();
		smsTransactionModel.setBody(Content);
		smsTransactionModel.setSmsAccountId(smsContentModel.getSmsaccountid());
		smsTransactionModel.setMobile(userModel.getMobile());
		smsTransactionModel.setStatus(Status.NEW.getId());
		smsTransactionModel.setActive(true);
		smsTransactionModel.setArchive(false);
		smsTransactionModel.setDateSend(DateUtility.getCurrentEpoch());
		smsTransactionModel.setHospitalModel(userModel.getUserRequestedClientModel());
		smsTransactionService.create(smsTransactionModel);

	}
}
