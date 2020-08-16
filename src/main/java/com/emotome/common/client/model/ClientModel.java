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
package com.emotome.common.client.model;

import com.emotome.common.client.view.ClientView;
import com.emotome.common.file.model.FileModel;
import com.emotome.common.location.model.CityModel;
import com.emotome.common.location.model.CountryModel;
import com.emotome.common.location.model.StateModel;
import com.emotome.common.model.ArchiveModel;
import com.emotome.common.model.IdentifierModel;

/**
 * This is hospital model which maps hospital table to class.
 * 
 * @author Jaydip
 * @since 22/04/2019
 */
public class ClientModel extends ArchiveModel {

	private static final long serialVersionUID = 6393354184228944331L;

	private String name;
	private String countryCode;
	private String mobile;
	private String apiKey;
	private FileModel logoFileModel;
	private String address;
	private String pincode;
	private CityModel cityModel;
	private StateModel stateModel;
	private CountryModel countryModel;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public FileModel getLogoFileModel() {
		return logoFileModel;
	}

	public void setLogoFileModel(FileModel logoFileModel) {
		this.logoFileModel = logoFileModel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public CityModel getCityModel() {
		return cityModel;
	}

	public void setCityModel(CityModel cityModel) {
		this.cityModel = cityModel;
	}

	public StateModel getStateModel() {
		return stateModel;
	}

	public void setStateModel(StateModel stateModel) {
		this.stateModel = stateModel;
	}

	public CountryModel getCountryModel() {
		return countryModel;
	}

	public void setCountryModel(CountryModel countryModel) {
		this.countryModel = countryModel;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public static ClientView setClientView(ClientModel clientModel) {
		ClientView clientView = new ClientView();
		clientView.setId(clientModel.getId());
		clientView.setName(clientModel.getName());
		clientView.setMobile(clientModel.getMobile());
		clientView.setApiKey(clientModel.getApiKey());
		return clientView;
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
