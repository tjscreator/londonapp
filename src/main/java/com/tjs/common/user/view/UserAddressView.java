package com.tjs.common.user.view;

import com.tjs.common.view.IdNameView;
import com.tjs.common.view.IdentifierView;

public class UserAddressView extends IdentifierView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3639308973927328059L;
	private String address;
	private String pincode;
	private IdNameView cityView;
	private IdNameView stateView;
	private IdNameView countryView;

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

	public IdNameView getCityView() {
		return cityView;
	}

	public void setCityView(IdNameView cityView) {
		this.cityView = cityView;
	}

	public IdNameView getStateView() {
		return stateView;
	}

	public void setStateView(IdNameView stateView) {
		this.stateView = stateView;
	}

	public IdNameView getCountryView() {
		return countryView;
	}

	public void setCountryView(IdNameView countryView) {
		this.countryView = countryView;
	}

}
