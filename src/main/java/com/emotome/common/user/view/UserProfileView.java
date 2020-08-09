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
package com.emotome.common.user.view;

import com.emotome.common.file.view.FileView;
import com.emotome.common.location.view.CityView;
import com.emotome.common.location.view.StateView;
import com.emotome.common.view.IdentifierView;
import com.emotome.common.view.KeyValueView;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * This class is used to represent user's profile object in json/in hospital response.
 * @author Nirav.Shah
 * @since 12/08/2018
 */
@JsonInclude(Include.NON_NULL)
public class UserProfileView extends IdentifierView {

	private static final long serialVersionUID = -4444717308537621033L;
	
	private KeyValueView title;
	private FileView profile;
	private UserView userView;
	private String telephoneNumber;
	private String address;
	private CityView cityView;
	private StateView stateView;
	private String pincode;
	private boolean completeProfile;
	private String website;
	
	
	private String birthDate;
	
	public KeyValueView getTitle() {
		return title;
	}
	public void setTitle(KeyValueView title) {
		this.title = title;
	}
	public FileView getProfile() {
		return profile;
	}
	public void setProfile(FileView profile) {
		this.profile = profile;
	}
	public UserView getUserView() {
		return userView;
	}
	public void setUserView(UserView userView) {
		this.userView = userView;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public CityView getCityView() {
		return cityView;
	}
	public void setCityView(CityView cityView) {
		this.cityView = cityView;
	}
	public StateView getStateView() {
		return stateView;
	}
	public void setStateView(StateView stateView) {
		this.stateView = stateView;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public boolean isCompleteProfile() {
		return completeProfile;
	}
	public void setCompleteProfile(boolean completeProfile) {
		this.completeProfile = completeProfile;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
}