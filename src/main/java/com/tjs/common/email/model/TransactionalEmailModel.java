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
package com.tjs.common.email.model;

import com.tjs.common.client.model.ClientModel;
import com.tjs.common.model.ArchiveModel;
import com.tjs.harbor.email.enums.Status;

/**
 * This is Transaction Email model which maps Transaction email table of
 * database. Transaction email like (Password generation, activation link etc)
 * will fall under this category. User action related mail fall under this
 * category.
 * 
 * @author Dhruvang.Joshi
 * @since 28/07/2017
 *
 */

public class TransactionalEmailModel extends ArchiveModel {

	private static final long serialVersionUID = -4351538513633808259L;

	private Long emailAccountId;
	private Long lockVersion;
	private String emailTo;
	private String emailCc;
	private String emailBcc;
	private String subject;
	private String body;
	private int status;
	private Long retryCount;
	private String attachmentPath;
	private String txtError;
	private Long dateSend;
	private Long dateSent;
	private ClientModel clientModel;

	public ClientModel getClientModel() {
		return clientModel;
	}

	public void setClientModel(ClientModel clientModel) {
		this.clientModel = clientModel;
	}

	public Long getLockVersion() {
		return lockVersion;
	}

	public void setLockVersion(Long lockVersion) {
		this.lockVersion = lockVersion;
	}

	public Long getEmailAccountId() {
		return emailAccountId;
	}

	public void setEmailAccountId(Long emailAccountId) {
		this.emailAccountId = emailAccountId;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	public String getEmailCc() {
		return emailCc;
	}

	public void setEmailCc(String emailCc) {
		this.emailCc = emailCc;
	}

	public String getEmailBcc() {
		return emailBcc;
	}

	public void setEmailBcc(String emailBcc) {
		this.emailBcc = emailBcc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Status getStatus() {
		return Status.fromId(status);
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(Long retryCount) {
		this.retryCount = retryCount;
	}

	public String getAttachmentPath() {
		return attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
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

	public String getTxtError() {
		return txtError;
	}

	public void setTxtError(String txtError) {
		this.txtError = txtError;
	}
}
