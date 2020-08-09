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
import com.emotome.common.model.IdentifierModel;

/**
 * This is SMS account model which maps sms acoount table to class.
 * 
 * @author JD
 * @since 15/05/2019
 */
public class SmsAccountModel extends ArchiveModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6371608377878186125L;
	private String name;
	private String groupId;
	private String routeId;
	private String senderId;
	private String signature;
	private String contentType;
	private String authKey;

	private static Map<String, SmsAccountModel> MAP = new ConcurrentHashMap<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getContentType() {
		return contentType;
	}

	public static Map<String, SmsAccountModel> getMAP() {
		return MAP;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
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
