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
package com.emotome.common.sms.service;

import java.util.List;

import com.emotome.common.service.BaseService;
import com.emotome.common.sms.model.SmsTransactionModel;

/**
 * 
 * @author JD
 * @since 16/05/2019
 */
public interface SmsTransactionService extends BaseService<SmsTransactionModel> {

	String SMS_TRANSACTION_MODEL = "smsTransactionModel";
	String LIGHT_SMS_TRANSACTION_MODEL = "lightSmsTransactionModel";

	/**
	 * this method for get all transactional sms by status.
	 * 
	 * @param statusId
	 * @return
	 */
	List<SmsTransactionModel> getDataByStatus(Integer statusId);

	/**
	 * This method is used to get sms records list base on given count.
	 * 
	 * @param limit
	 * @return
	 */
	List<SmsTransactionModel> getSmsList(int limit);

}
