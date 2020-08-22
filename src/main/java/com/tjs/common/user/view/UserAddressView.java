package com.tjs.common.user.view;

import com.tjs.common.location.model.CountryModel;
import com.tjs.common.location.view.CityView;
import com.tjs.common.location.view.CountryView;
import com.tjs.common.location.view.StateView;
import com.tjs.common.view.IdNameView;
import com.tjs.common.view.IdentifierView;

public class UserAddressView extends IdentifierView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3639308973927328059L;
	private String address;
	private String pincode;
	private CityView cityView;
	private StateView stateView;
	private CountryView countryView;

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

	public CountryView getCountryView() {
		return countryView;
	}

	public void setCountryView(CountryView countryView) {
		this.countryView = countryView;
	}

}
