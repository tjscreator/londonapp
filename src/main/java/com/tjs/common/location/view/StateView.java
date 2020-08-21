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
package com.tjs.common.location.view;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tjs.common.view.IdentifierView;
import com.tjs.common.view.KeyValueView;

/**
 * This class is used to represent city object in json/in hospital response.
 * @author Nirav
 * @since 14/11/2017
 */

@JsonInclude(Include.NON_NULL)
public class StateView extends IdentifierView{
	
	private static final long serialVersionUID = 6298419420042301917L;
	private String name;
	private Long countryId;
	private CountryView country;
	private List<KeyValueView> countryList;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCountryId() {
		return countryId;
	}
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}
	public CountryView getCountry() {
		return country;
	}
	public void setCountry(CountryView country) {
		this.country = country;
	}
	public List<KeyValueView> getCountryList() {
		return countryList;
	}
	public void setCountryList(List<KeyValueView> countryList) {
		this.countryList = countryList;
	}
}
