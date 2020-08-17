package com.emotome.common.captcha.model;

import com.emotome.common.model.Model;

/**
 * This is Captcha model which maps captcha table to class.
 * 
 * @author Nirav.Shah
 * @since 17/12/2018
 */
public class CaptchaModel implements Model {

	private static final long serialVersionUID = 8966066609469804789L;
	private String id;
	private Long createDate;
	private String value;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}