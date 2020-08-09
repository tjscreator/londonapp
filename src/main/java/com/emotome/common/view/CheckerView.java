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
package com.emotome.common.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * This is Checker view which used to send checked by user details in json format.
 * @author Nirav.Shah
 * @since 14/02/2018
 */
@JsonInclude(Include.NON_NULL)
public abstract class CheckerView extends AuditableView {

	private static final long serialVersionUID = 7982485400162432811L;
	
    private String checkedDate;
    private String checkedBy;
    private String checkerRequired;
    private String checkerComments;
    
	public String getCheckedDate() {
		return checkedDate;
	}
	public void setCheckedDate(String checkedDate) {
		this.checkedDate = checkedDate;
	}
	public String getCheckedBy() {
		return checkedBy;
	}
	public void setCheckedBy(String checkedBy) {
		this.checkedBy = checkedBy;
	}
	public String getCheckerRequired() {
		return checkerRequired;
	}
	public void setCheckerRequired(String checkerRequired) {
		this.checkerRequired = checkerRequired;
	}
	public String getCheckerComments() {
		return checkerComments;
	}
	public void setCheckerComments(String checkerComments) {
		this.checkerComments = checkerComments;
	}
}
