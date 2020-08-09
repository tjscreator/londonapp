package com.emotome.common.client.operation;

import org.springframework.beans.factory.annotation.Autowired;

import com.emotome.common.client.model.ClientBranchModel;
import com.emotome.common.client.model.ClientModel;
import com.emotome.common.client.service.ClientBranchService;
import com.emotome.common.client.service.ClientService;
import com.emotome.common.client.view.ClientBranchView;
import com.emotome.common.location.model.CityModel;
import com.emotome.common.location.model.CountryModel;
import com.emotome.common.location.model.StateModel;
import com.emotome.common.location.service.CityService;
import com.emotome.common.location.service.CountryService;
import com.emotome.common.location.service.StateService;
import com.emotome.common.operation.AbstractOperation;
import com.emotome.common.response.Response;
import com.emotome.common.service.BaseService;
import com.emotome.harbor.exception.HarborException;

public class ClientBranchOperationImpl extends AbstractOperation<ClientBranchModel, ClientBranchView>
		implements ClientBranchOperation {

	@Autowired
	private ClientBranchService clientBranchService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private CityService cityService;

	@Autowired
	private StateService stateService;

	@Autowired
	private CountryService countryService;

	@Override
	public Response doAdd() throws HarborException {
		return null;
	}

	@Override
	public Response doView(Long id) throws HarborException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response doEdit(Long id) throws HarborException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ClientBranchModel loadModel(ClientBranchView view) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response doDelete(Long id) throws HarborException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response doActiveInActive(Long id) throws HarborException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientBranchModel toModel(ClientBranchModel clientBranchModel, ClientBranchView clientBranchView) {
		clientBranchModel.setName(clientBranchView.getName());
		clientBranchModel.setMobile(clientBranchView.getMobile());
		clientBranchModel.setCountryCode(clientBranchView.getCountryCode());
		clientBranchModel.setAddress(clientBranchView.getAddress());
		return null;
	}

	@Override
	protected ClientBranchModel getNewModel() {
		return new ClientBranchModel();
	}

	@Override
	public ClientBranchView fromModel(ClientBranchModel clientBranchModel) {
		ClientBranchView clientBranchView = new ClientBranchView();
		clientBranchView.setId(clientBranchModel.getId());
		clientBranchView.setName(clientBranchModel.getName());
		clientBranchView.setCountryCode(clientBranchModel.getCountryCode());
		clientBranchView.setMobile(clientBranchModel.getMobile());
		setAddressDetails(clientBranchView, clientBranchModel);
		clientBranchView.setClientView(ClientModel.setClientView(clientBranchModel.getClientModel()));
		return clientBranchView;
	}

	private void setAddressDetails(ClientBranchView clientBranchView, ClientBranchModel clientBranchModel) {
		clientBranchView.setAddress(clientBranchModel.getAddress());
		clientBranchView.setCityView(CityModel.setCityView(clientBranchModel.getCityModel()));
		clientBranchView.setStateView(StateModel.setStateView(clientBranchModel.getStateModel()));
		clientBranchView.setCountryView(CountryModel.setCountyView(clientBranchModel.getCountryModel()));
	}

	@Override
	public BaseService getService() {
		return clientBranchService;
	}

	@Override
	protected void checkInactive(ClientBranchModel model) throws HarborException {

	}

}
