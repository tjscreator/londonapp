package com.emotome.common.email.view;

import com.emotome.common.client.view.ClientView;
import com.emotome.common.view.ArchiveView;
import com.emotome.common.view.KeyValueView;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * This class is used to represent emailcontent object in json/in hospital response
 * @author Nisha.Panchal
 * @Since 12/07/2018
 */
 
@JsonInclude(Include.NON_NULL)
public class EmailContentView extends ArchiveView{

	private static final long serialVersionUID = -7632495476077413752L;
    private EmailAccountView emailAccountView;
	private String name;
	private String content;
	private String subject;
	private String emailBcc;
	private String emailCc;
	private KeyValueView trigger;
	private ClientView hospitalView;
	
	
	public ClientView getHospitalView() {
		return hospitalView;
	}
	public void setHospitalView(ClientView hospitalView) {
		this.hospitalView = hospitalView;
	}
	public EmailAccountView getEmailAccountView() {
		return emailAccountView;
	}
	public void setEmailAccountView(EmailAccountView emailAccountView) {
		this.emailAccountView = emailAccountView;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getEmailBcc() {
		return emailBcc;
	}
	public void setEmailBcc(String emailBcc) {
		this.emailBcc = emailBcc;
	}
	public KeyValueView getTrigger() {
		return trigger;
	}
	public void setTrigger(KeyValueView trigger) {
		this.trigger = trigger;
	}
	public String getEmailCc() {
		return emailCc;
	}
	public void setEmailCc(String emailCc) {
		this.emailCc = emailCc;
	}	
}