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
package com.emotome.common.location.view;

import java.util.List;

import com.emotome.common.view.IdentifierView;
import com.emotome.common.view.KeyValueView;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * This class is used to represent city object in json/in hospital response.
 * @author Nirav
 * @since 14/11/2017
 */

@JsonInclude(Include.NON_NULL)
public class CityView extends IdentifierView{
	
	private static final long serialVersionUID = 6298419420042301917L;
	private String name;
	private Long stateId;
	private StateView state;
	private List<KeyValueView> stateList;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public StateView getState() {
		return state;
	}
	public void setState(StateView state) {
		this.state = state;
	}
	public List<KeyValueView> getStateList() {
		return stateList;
	}
	public void setStateList(List<KeyValueView> stateList) {
		this.stateList = stateList;
	}
}
