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
package com.emotome.common.sms.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.emotome.common.model.ArchiveModel;
import com.emotome.common.sms.enums.SmsTriggerEnum;

/**
 * This is SMS content model which maps sms content table to class.
 * 
 * @author JD
 * @since 15/05/2019
 */
public class SmsContentModel extends ArchiveModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6803406329134861102L;
	private Long smsaccountid;
	private String subject;
	private String content;
	private Integer triggerId;

	private static Map<Long, SmsContentModel> MAP = new ConcurrentHashMap<>();

	public Long getSmsaccountid() {
		return smsaccountid;
	}

	public void setSmsaccountid(Long smsaccountid) {
		this.smsaccountid = smsaccountid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setTriggerId(Integer triggerId) {
		this.triggerId = triggerId;
	}

	public SmsTriggerEnum getTriggerId() {
		return SmsTriggerEnum.fromId(triggerId);
	}

	public static Map<Long, SmsContentModel> getMAP() {
		return MAP;
	}

}
