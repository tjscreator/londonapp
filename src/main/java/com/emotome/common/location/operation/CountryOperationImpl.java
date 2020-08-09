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
package com.emotome.common.location.operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.emotome.common.enums.ResponseCode;
import com.emotome.common.location.model.CountryModel;
import com.emotome.common.location.service.CountryService;
import com.emotome.common.location.view.CountryView;
import com.emotome.common.model.PageModel;
import com.emotome.common.operation.AbstractOperation;
import com.emotome.common.response.CommonResponse;
import com.emotome.common.response.PageResultResponse;
import com.emotome.common.response.Response;
import com.emotome.common.response.ViewResponse;
import com.emotome.common.service.BaseService;
import com.emotome.common.view.KeyValueView;
import com.emotome.harbor.exception.HarborException;

/**
 * This class used to perform all business operation on state model.
 * 
 * @author Dhruvang
 * @since 29/01/2018
 */

@Component(value = "countryOperation")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class CountryOperationImpl extends AbstractOperation<CountryModel, CountryView> implements CountryOperation {

	@Autowired
	private CountryService countryService;

	@Override
	public Response doSave(CountryView countryView) throws HarborException {
		CountryModel countryModel = getModel(countryView);
		countryService.create(countryModel);
		CountryModel.addCountry(countryModel);
		return CommonResponse.create(ResponseCode.SAVE_SUCCESSFULLY.getCode(),
				ResponseCode.SAVE_SUCCESSFULLY.getMessage());
	}

	@Override
	public Response doUpdate(CountryView countryView) throws HarborException {
		CountryModel countryModel = loadModel(countryView);
		if (countryModel == null) {
			return CommonResponse.create(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		checkInactive(countryModel);
		getService().update(toModel(countryModel, countryView));
		CountryModel.removeCountry(countryModel);
		CountryModel.addCountry(countryModel);
		return CommonResponse.create(ResponseCode.UPDATE_SUCCESSFULLY.getCode(),
				ResponseCode.UPDATE_SUCCESSFULLY.getMessage());
	}

	@Override
	public Response doAdd() throws HarborException {
		return null;
	}

	@Override
	public Response doView(Long id) throws HarborException {
		CountryModel countryModel = CountryModel.getMAP().get(id);
		if (countryModel == null) {
			countryModel = countryService.getUsingDiffEntity(id);
			if (countryModel == null) {
				throw new HarborException(ResponseCode.NO_DATA_FOUND.getCode(), ResponseCode.NO_DATA_FOUND.getMessage());
			}
		}
		CountryView countryView = fromModel(countryModel);
		return ViewResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(),
				countryView);
	}

	@Override
	public Response doEdit(Long id) throws HarborException {
		CountryModel countryModel = countryService.get(id);
		if (countryModel == null) {
			throw new HarborException(ResponseCode.NO_DATA_FOUND.getCode(), ResponseCode.NO_DATA_FOUND.getMessage());
		}
		CountryView countryView = fromModel(countryModel);
		return ViewResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(),
				countryView);
	}

	@Override
	protected CountryModel loadModel(CountryView countryView) {
		return countryService.get(countryView.getId());
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
	public CountryModel toModel(CountryModel countryModel, CountryView countryView) {
		countryModel.setName(countryView.getName());
		countryModel.setSortName(countryView.getSortName());
		countryModel.setPhoneCode(countryView.getPhoneCode());
		return countryModel;
	}

	@Override
	public Response doSearch(CountryView view, Integer start, Integer recordSize) throws HarborException {
		List<CountryModel> countries = countryService.findAll();
		Set<KeyValueView> countryList = new TreeSet<KeyValueView>();
		for (CountryModel country : countries) {
			countryList.add(KeyValueView.create(country.getId(), country.getName()));
		}
		List<KeyValueView> listCountry = new ArrayList<>();
		listCountry.addAll(countryList);
		return PageResultResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(),
				listCountry.size(), listCountry);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response doDisplayGrid(Integer start, Integer recordSize) {
		PageModel pageModel = countryService.getGridData(start, recordSize);
		if(pageModel == null) {
			return CommonResponse.create(ResponseCode.NO_DATA_FOUND.getCode(), ResponseCode.NO_DATA_FOUND.getMessage());
		}
		List<CountryModel> countryModels = new ArrayList<>();
		countryModels = (List<CountryModel>) pageModel.getList();
		if (countryModels.isEmpty()) {
			return CommonResponse.create(ResponseCode.NO_DATA_FOUND.getCode(), ResponseCode.NO_DATA_FOUND.getMessage());
		}
		return PageResultResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(),
				countryModels.size(), fromModelList(countryModels));
	}

	@Override
	protected CountryModel getNewModel() {
		return new CountryModel();
	}

	@Override
	public CountryView fromModel(CountryModel countryModel) {
		CountryView countryView = new CountryView();
		countryView.setId(countryModel.getId());
		countryView.setName(countryModel.getName());
		countryView.setSortName(countryModel.getSortName());
		countryView.setPhoneCode(countryModel.getPhoneCode());
		return countryView;
	}

	@Override
	public BaseService<CountryModel> getService() {
		return countryService;
	}

	@Override
	protected void checkInactive(CountryModel model) throws HarborException {
	}

	@Override
	public Response doDropdown() throws HarborException {
		List<CountryView> countryViews = fromMap();
		if(countryViews != null && ! countryViews.isEmpty()) {
			return PageResultResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(),
					countryViews.size(), countryViews);	
		}
		List<CountryModel> countryModels = new ArrayList<>();
		PageModel pageModel = countryService.getGridData(null, null);
		if(pageModel == null) {
			return CommonResponse.create(ResponseCode.NO_DATA_FOUND.getCode(), ResponseCode.NO_DATA_FOUND.getMessage());
		}
		countryModels = (List<CountryModel>) pageModel.getList();
		if (countryModels.isEmpty()) {
			return CommonResponse.create(ResponseCode.NO_DATA_FOUND.getCode(), ResponseCode.NO_DATA_FOUND.getMessage());
		}
		return PageResultResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(),
				countryModels.size(), fromModelList(countryModels));
	}
	
	private List<CountryView> fromMap(){
		Map<Long,CountryModel> countryMap = CountryModel.getMAP();
		List<CountryView> countryViews = new ArrayList<>();
		if (countryMap != null && countryMap.isEmpty() == false) {			
			for (Long key : countryMap.keySet()) {
				CountryModel countryModel = (CountryModel) countryMap.get(key);
				CountryView countryView = fromModel(countryModel);
				countryViews.add(countryView);
			}
			return countryViews;
		}
		return null;
	}
}