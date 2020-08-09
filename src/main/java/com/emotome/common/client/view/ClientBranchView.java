package com.emotome.common.client.view;

import org.apache.commons.lang3.StringUtils;

import com.emotome.common.enums.ResponseCode;
import com.emotome.common.validation.DataType;
import com.emotome.common.validation.InputField;
import com.emotome.common.validation.RegexEnum;
import com.emotome.common.validation.Validator;
import com.emotome.common.view.ArchiveView;
import com.emotome.common.view.IdNameView;
import com.emotome.harbor.exception.HarborException;

public class ClientBranchView extends ArchiveView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6118179775329394044L;
	private String name;
	private String countryCode;
	private String mobile;
	private ClientView clientView;
	private String address;
	private String pincode;
	private IdNameView cityView;
	private IdNameView stateView;
	private IdNameView countryView;

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

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public ClientView getClientView() {
		return clientView;
	}

	public void setClientView(ClientView clientView) {
		this.clientView = clientView;
	}

	public static void isValid(ClientBranchView clientBranchView) throws HarborException {
		Validator.CLIENT_NAME.isValid(new InputField(clientBranchView.getName(), DataType.STRING, 300,
				RegexEnum.ALPHA_NUMERIC_WITH_SPECIFIC_SPECIAL_CHARACTER));
		Validator.USER_MOBILE
				.isValid(new InputField(clientBranchView.getMobile(), DataType.STRING, RegexEnum.PHONE_NUMBER));
		if (StringUtils.isBlank(clientBranchView.getCountryCode())) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"Country code " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		if (clientBranchView.getClientView() == null
				|| (clientBranchView.getClientView() != null && clientBranchView.getClientView().getId() == null)) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"Client " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		isValidPlaceDetails(clientBranchView);
	}

	public static void isValidPlaceDetails(ClientBranchView clientBranchView) throws HarborException {
		if (StringUtils.isBlank(clientBranchView.getAddress())) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"Address " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		if (StringUtils.isBlank(clientBranchView.getPincode())) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"Pincode " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		if (clientBranchView.getCityView() == null
				|| (clientBranchView.getCityView() != null && clientBranchView.getCityView().getId() == null)) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"City " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		if (clientBranchView.getStateView() == null
				|| (clientBranchView.getStateView() != null && clientBranchView.getStateView().getId() == null)) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"State " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		if (clientBranchView.getCountryView() == null
				|| (clientBranchView.getCountryView() != null && clientBranchView.getCountryView().getId() == null)) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"Country " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
	}

}
