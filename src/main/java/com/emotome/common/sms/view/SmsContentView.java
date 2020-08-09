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
package com.emotome.common.sms.view;

import com.emotome.common.view.IdentifierView;
import com.emotome.common.view.KeyValueView;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * This class is used to represent sms content object in json/in hospital
 * response.
 * 
 * @author JD
 * @since 28/08/2019
 */
@JsonInclude(Include.NON_NULL)
public class SmsContentView extends IdentifierView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4483300593789244733L;
	private String content;
	private String subject;
	private KeyValueView trigger;

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

	public KeyValueView getTrigger() {
		return trigger;
	}

	public void setTrigger(KeyValueView trigger) {
		this.trigger = trigger;
	}

}
