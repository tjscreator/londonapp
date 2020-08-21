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
package com.tjs.common.sms.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.tjs.common.logger.LoggerService;
import com.tjs.common.sms.model.SmsAccountModel;
import com.tjs.common.sms.model.SmsSendModel;
import com.tjs.common.sms.model.SmsTransactionModel;
import com.tjs.common.sms.service.SmsTransactionService;
import com.tjs.common.util.DateUtility;
import com.tjs.common.util.JsonUtil;
import com.tjs.harbor.email.enums.Status;
import com.tjs.harbor.exception.HarborException;

/**
 * This class used to perform sms transaction run thread.
 * 
 * @author jaydeep
 * @since 12/08/2019
 */
@Component
public class SmsThread {

	@Autowired
	private SmsTransactionService smsTransactionService;

	private static String SMS_MANAGER = "SmsManager";

	private String responseCode = null;
	private String responseMessage = null;

	@Async("transactionSmsExecutor")
	public void sendTransactionSms(final SmsTransactionModel smsTransactionModel, final SmsAccountModel smsAccountModel,
			final RestTemplate restTemplate) {
		try {
			transactionSms(smsTransactionModel, smsAccountModel, restTemplate);
		} catch (HarborException e) {
			LoggerService.exception(e);
		}
	}

	// @Async("transactionEmailRetry")
	public void retrySms(final SmsTransactionModel smsTransactionModel, final SmsAccountModel smsAccountModel) {
	}

	private void transactionSms(final SmsTransactionModel smsTransactionModel, final SmsAccountModel smsAccountModel,
			final RestTemplate restTemplate) throws HarborException {
		SmsSendModel smsSendModel = setSmsContent(new SmsSendModel(), smsTransactionModel, smsAccountModel);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<SmsSendModel> entity = new HttpEntity<>(smsSendModel, headers);
		ResponseEntity<String> result = restTemplate.postForEntity(
				"http://route7.c2sms.com/rest/services/sendSMS/sendGroupSms?AUTH_KEY=" + smsAccountModel.getAuthKey(),
				entity, String.class);
		if (result.getBody() != null) {
			responseCode = JsonUtil.getValueOfSpecificKeyFromJsonData(result.getBody(), "responseCode");
			responseMessage = JsonUtil.getValueOfSpecificKeyFromJsonData(result.getBody(), "response");
			if (Integer.valueOf(responseCode) == 3001) {
				smsTransactionModel.setStatus(Status.SENT.getId());
				smsTransactionModel.setDateSend(DateUtility.getCurrentEpoch());
				smsTransactionModel.setDateSent(DateUtility.getCurrentEpoch());
				smsTransactionService.update(smsTransactionModel);
			} else {
				smsTransactionModel.setStatus(Status.FAILED.getId());
				smsTransactionModel.setError(responseMessage);
				smsTransactionService.update(smsTransactionModel);
			}
		}
	}

	private SmsSendModel setSmsContent(SmsSendModel smsSendModel, SmsTransactionModel smsTransactionModel,
			SmsAccountModel smsAccountModel) {
		smsSendModel.setSmsContent(smsTransactionModel.getBody());
		smsSendModel.setMobileNumbers(smsTransactionModel.getMobile());
		smsSendModel.setRouteId(smsAccountModel.getRouteId());
		smsSendModel.setSenderId(smsAccountModel.getSenderId());
		smsSendModel.setSignature(smsAccountModel.getSignature());
		smsSendModel.setSmsContentType(smsAccountModel.getContentType());
		return smsSendModel;
	}
}
