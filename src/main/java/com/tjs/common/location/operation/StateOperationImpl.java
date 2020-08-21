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
package com.tjs.common.location.operation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tjs.common.enums.ResponseCode;
import com.tjs.common.location.model.CountryModel;
import com.tjs.common.location.model.StateModel;
import com.tjs.common.location.service.CountryService;
import com.tjs.common.location.service.StateService;
import com.tjs.common.location.view.CountryView;
import com.tjs.common.location.view.StateView;
import com.tjs.common.operation.AbstractOperation;
import com.tjs.common.response.CommonResponse;
import com.tjs.common.response.PageResultResponse;
import com.tjs.common.response.Response;
import com.tjs.common.response.ViewResponse;
import com.tjs.common.service.BaseService;
import com.tjs.common.view.KeyValueView;
import com.tjs.harbor.exception.HarborException;

/**
 * This class used to perform all business operation on state model.
 * 
 * @author Nirav
 * @since 14/11/2017
 */
@Component(value = "stateOperation")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class StateOperationImpl extends AbstractOperation<StateModel, StateView> implements StateOperation {

	@Autowired
	StateService stateService;

	@Autowired
	CountryService countryService;

	@Override
	public Response doAdd() throws HarborException {
		return null;
	}

	@Override
	public Response doSave(StateView stateView) throws HarborException {
		StateModel stateModel = getModel(stateView);
		stateService.create(stateModel);
		StateModel.addState(stateModel);
		return CommonResponse.create(ResponseCode.SAVE_SUCCESSFULLY.getCode(),
				ResponseCode.SAVE_SUCCESSFULLY.getMessage());
	}

	@Override
	public Response doView(Long id) throws HarborException {
		StateModel stateModel = StateModel.getMAP().get(id);
		if (stateModel == null) {
			stateModel = stateService.getUsingDiffEntity(id);
			if (stateModel == null) {
				throw new HarborException(ResponseCode.NO_DATA_FOUND.getCode(),
						ResponseCode.NO_DATA_FOUND.getMessage());
			}
		}
		StateView stateView = fromModel(stateModel);
		return ViewResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(), stateView);
	}

	@Override
	public Response doEdit(Long id) throws HarborException {
		StateModel stateModel = StateModel.getMAP().get(id);
		if (stateModel == null) {
			throw new HarborException(ResponseCode.NO_DATA_FOUND.getCode(),
					ResponseCode.NO_DATA_FOUND.getMessage());
		}
		StateView stateView = fromModel(stateModel);
		List<KeyValueView> listView = new ArrayList<>(CountryModel.getMAP().size());
		for (Map.Entry<Long, CountryModel> map : CountryModel.getMAP().entrySet()) {
			listView.add(KeyValueView.create(map.getKey().intValue(), map.getValue().getName()));
		}
		Collections.sort(listView, KeyValueView.nameComparator);
		stateView.setCountryList(listView);
		return ViewResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(), stateView);
	}

	@Override
	public Response doUpdate(StateView stateView) throws HarborException {
		StateModel stateModel = loadModel(stateView);
		if (stateModel == null) {
			return CommonResponse.create(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		stateService.update(toModel(stateModel, stateView));
		StateModel.removeState(stateModel);
		StateModel.addState(stateModel);
		return CommonResponse.create(ResponseCode.UPDATE_SUCCESSFULLY.getCode(),
				ResponseCode.UPDATE_SUCCESSFULLY.getMessage());
	}

	@Override
	public Response doDelete(Long id) throws HarborException {
		return null;
	}

	@Override
	public Response doActiveInActive(Long id) throws HarborException {
		return null;
	}

	@Override
	public Response doSearch(StateView stateView, Integer start, Integer end) throws HarborException{
		List<StateView> stateViewList = new ArrayList<>();
		Set<StateModel> stateModelSet = CountryModel.getCountryStateMap().get(stateView.getCountryId());
		if (stateModelSet == null || stateModelSet.isEmpty()) {
			return CommonResponse.create(ResponseCode.NO_DATA_FOUND.getCode(), ResponseCode.NO_DATA_FOUND.getMessage());
		}
		for (StateModel stateModel : stateModelSet) {
			StateView temp = new StateView();
			temp.setId(stateModel.getId());
			temp.setName(stateModel.getName());
			stateViewList.add(temp);
		}
		return PageResultResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(),
				stateViewList.size(), stateViewList);
	}

	@Override
	public StateModel toModel(StateModel stateModel, StateView stateView) {
		CountryModel countryModel = countryService.get(stateView.getCountryId());
		stateModel.setName(stateView.getName());
		stateModel.setCountryModel(countryModel);
		return stateModel;
	}

	@Override
	protected StateModel getNewModel() {
		return new StateModel();
	}

	@Override
	public StateView fromModel(StateModel stateModel) {
		StateView stateView = new StateView();
		stateView.setId(stateModel.getId());
		stateView.setName(stateModel.getName());
		if (stateModel.getCountryModel() != null) {
			CountryView countryView = new CountryView();
			countryView.setId(stateModel.getCountryModel().getId());
			countryView.setName(stateModel.getCountryModel().getName());
			stateView.setCountry(countryView);
		}
		return stateView;
	}

	@Override
	public BaseService<StateModel> getService() {
		return stateService;
	}

	@Override
	protected void checkInactive(StateModel stateModel) throws HarborException {
		return;
	}

	@Override
	protected StateModel loadModel(StateView stateView) {
		return stateService.get(stateView.getId());
	}

	@Override
	public Response doDropdown(Long countryId) throws HarborException {
		List<StateView> stateViews = fromMap(countryId);
		if (stateViews != null && !stateViews.isEmpty()) {
			return PageResultResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(),
					stateViews.size(), stateViews);
		}
		List<StateModel> stateModels = stateService.findByCountry(countryId);
		if (stateModels == null || (stateModels != null && stateModels.isEmpty())) {
			return CommonResponse.create(ResponseCode.NO_DATA_FOUND.getCode(), ResponseCode.NO_DATA_FOUND.getMessage());
		}
		return PageResultResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(),
				stateModels.size(), fromModelList(stateModels));
	}

	private List<StateView> fromMap(Long countryId) {
		Set<StateModel> stateModels = CountryModel.getCountryStateMap().get(countryId);
		List<StateView> stateViews = new ArrayList<>();
		if (stateModels != null && stateModels.isEmpty() == false) {
			for (StateModel stateModel : stateModels) {
				StateView stateView = fromModel(stateModel);
				stateViews.add(stateView);
			}
			return stateViews;
		}
		return null;
	}
}