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
package com.tjs.common.email.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.tjs.common.client.model.ClientModel;
import com.tjs.common.model.ArchiveModel;
import com.tjs.common.model.IdentifierModel;
import com.tjs.harbor.email.enums.CommunicationTriggerEnum;

/**
 * This is EmailContentModel model which maps EmailContentModel table to class.
 * @author Vishwa.Shah
 * @since 14/06/2018
 *
 */
public class EmailContentModel extends ArchiveModel{

	private static final long serialVersionUID = 4365449160483605482L;

	private Long emailAccountId;
	private String name;
	private String subject;
	private String content;
	private String emailBcc;
	private String emailCc;
	private Integer triggerId;

	private static Map<Long, EmailContentModel> MAP = new ConcurrentHashMap<>();

	public Long getEmailAccountId() {
		return emailAccountId;
	}
	public void setEmailAccountId(Long emailAccountId) {
		this.emailAccountId = emailAccountId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getEmailBcc() {
		return emailBcc;
	}
	public void setEmailBcc(String emailBcc) {
		this.emailBcc = emailBcc;
	}
	public String getEmailCc() {
		return emailCc;
	}
	public void setEmailCc(String emailCc) {
		this.emailCc = emailCc;
	}
	
	public static Map<Long, EmailContentModel> getMAP() {
		return MAP;
	}
	
	public static void addEmailContent(EmailContentModel emailContentModel) {
		MAP.put(emailContentModel.getId(), emailContentModel);
	}
	
	public static void removeEmailContent(EmailContentModel emailContentModel) {
		MAP.remove(emailContentModel.getId());
	}
	
	public static void updateEmailContent(EmailContentModel emailContentModel) {
		MAP.replace(emailContentModel.getId(), emailContentModel);
	}
	
	public CommunicationTriggerEnum getTriggerId() {
		return CommunicationTriggerEnum.fromId(triggerId);
	}
	
	public void setTriggerId(Integer triggerId) {
		this.triggerId = triggerId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdentifierModel other = (IdentifierModel) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}
}