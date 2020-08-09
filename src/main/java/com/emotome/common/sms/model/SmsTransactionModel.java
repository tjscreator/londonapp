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

import com.emotome.common.client.model.ClientModel;
import com.emotome.common.model.ArchiveModel;

/**
 * This is sms transaction model which maps sms transaction table to class.
 * 
 * @author JD
 * @since 16/05/2019
 */
public class SmsTransactionModel extends ArchiveModel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7578101229216359952L;
	private Long smsAccountId;
	private String body;
	private String mobile;
	private int status;
	private Long retryCount;
	private String error;
	private Long dateSend;
	private Long dateSent;
	private ClientModel hospitalModel;

	public Long getSmsAccountId() {
		return smsAccountId;
	}

	public void setSmsAccountId(Long smsAccountId) {
		this.smsAccountId = smsAccountId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(Long retryCount) {
		this.retryCount = retryCount;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Long getDateSend() {
		return dateSend;
	}

	public void setDateSend(Long dateSend) {
		this.dateSend = dateSend;
	}

	public Long getDateSent() {
		return dateSent;
	}

	public void setDateSent(Long dateSent) {
		this.dateSent = dateSent;
	}

	public ClientModel getHospitalModel() {
		return hospitalModel;
	}

	public void setHospitalModel(ClientModel hospitalModel) {
		this.hospitalModel = hospitalModel;
	}

}
