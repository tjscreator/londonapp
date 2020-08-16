package com.emotome.common.user.model;

import com.emotome.common.location.model.CityModel;
import com.emotome.common.location.model.CountryModel;
import com.emotome.common.location.model.StateModel;
import com.emotome.common.model.IdentifierModel;

/**
 * This is model represent to user address table.
 * 
 * @author jaydip.golviya
 *
 */
public class UserAddressModel extends IdentifierModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2058944305619771979L;
	private UserModel userModel;
	private String address;
	private CityModel cityModel;
	private StateModel stateModel;
	private CountryModel countryModel;
	private String pincode;

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

}
