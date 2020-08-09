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
package com.emotome.common.email.service;


import com.emotome.common.email.model.EmailContentModel;
import com.emotome.common.email.view.EmailContentView;
import com.emotome.common.model.PageModel;
import com.emotome.common.service.BaseService;

/**
 * This is declaration of email content service which defines database operation
 * which can  be performed on this table.
 * @author Nirav.Shah
 * @since 12/08/2017
 */
public interface EmailContentService extends BaseService<EmailContentModel> {
	String EMAIL_CONTENT="emailContent";
	String LIGHT_EMAIL_CONTENT = "lightEmailContent";

	/**
	 * Used to find email content through name.
	 * @param name
	 * @return
	 */
	EmailContentModel findByName(String name);

	/**
	 * Used to find email content by trigger id.
	 * @param triggerId
	 * @return
	 */
	EmailContentModel findByTrigger(Integer triggerId);
	
	/**
	 * Used to find email content by trigger id and hospital id.
	 * @param triggerId
	 * @param hospitalId
	 * @return
	 */
	EmailContentModel findByTriggerAndHospital(Integer triggerId,Long hospitalId);
	
	/**
	 * Used to search light email content
	 * @param emailContentView
	 * @param start
	 * @param recordSize
	 * @return
	 */
	PageModel searchLight(EmailContentView emailContentView, Integer start, Integer recordSize);
	
	/**
	 * Get Light grid data.
	 * @param start
	 * @param recordSize
	 * @return
	 */
	PageModel getGridDataLight(Integer start, Integer recordSize);

}
