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
package com.tjs.common.sms.service;

import com.tjs.common.service.BaseService;
import com.tjs.common.sms.model.SmsContentModel;

/**
 * 
 * @author JD
 * @since 16/05/2019
 */
public interface SmsContentService extends BaseService<SmsContentModel> {

	String SMS_CONTENT_MODEL = "smsContentModel";
	String LIGHT_SMS_CONTENT_MODEL = "lightSmsContentModel";

	/**
	 * Used to find sms content by trigger id.
	 * 
	 * @param triggerId
	 * @param hospitalId
	 * @return
	 */
	SmsContentModel findByTriggerAndHospital(Integer triggerId, Long hospitalId);
}
