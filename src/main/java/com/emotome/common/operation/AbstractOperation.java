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
package com.emotome.common.operation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.emotome.common.enums.ResponseCode;
import com.emotome.common.model.Model;
import com.emotome.common.model.PageModel;
import com.emotome.common.response.CommonResponse;
import com.emotome.common.response.PageResultResponse;
import com.emotome.common.response.Response;
import com.emotome.common.service.BaseService;
import com.emotome.common.view.IdentifierView;
import com.emotome.harbor.exception.HarborException;

/**
 * This class is provide transaction wrapper ,Actual transaction begin over here
 * contains common operation and list of abstract method
 * 
 * @author Nirav.Shah
 *
 * @param <M>
 * @param <V>
 */
@Component
@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Throwable.class)
public abstract class AbstractOperation<M extends Model, V extends IdentifierView> {

	/**
	 * This method is used to validate add request to add any entity into
	 * system. Once it is been validate. it allow users to add entity data.
	 * 
	 * @return
	 * @throws HarborException
	 */
	public abstract Response doAdd() throws HarborException;

	/**
	 * This method is used to save entity
	 * 
	 * @param request
	 * @return
	 * @throws HarborException
	 */
	public Response doSave(V view) throws HarborException {
		Model model = getModel(view);
		getService().create(model);
		return CommonResponse.create(ResponseCode.SAVE_SUCCESSFULLY.getCode(),	ResponseCode.SAVE_SUCCESSFULLY.getMessage());
	}

	/**
	 * This method is used to view entity.
	 * 
	 * @param id
	 * @return
	 * @throws HarborException
	 */
	public abstract Response doView(Long id) throws HarborException;

	/**
	 * This method is used to edit entity.
	 * 
	 * @param id
	 * @return
	 * @throws HarborException
	 */
	public abstract Response doEdit(Long id) throws HarborException;

	/**
	 * This method is used to update entity
	 * 
	 * @param view
	 * @return
	 * @throws HarborException
	 */
	public Response doUpdate(V view) throws HarborException {
		M model = loadModel(view);
		if (model == null) {
			return CommonResponse.create(ResponseCode.INVALID_REQUEST.getCode(), ResponseCode.INVALID_REQUEST.getMessage());
		}
		checkInactive(model);
		getService().update(toModel(model, view));
		return CommonResponse.create(ResponseCode.UPDATE_SUCCESSFULLY.getCode(), ResponseCode.UPDATE_SUCCESSFULLY.getMessage());
	}

	/**
     * This method load original model from view  
     * @param view view of model
     * @return model
     */
    protected abstract M loadModel(V view);
    
	/**
	 * This method is used delete existing data.
	 * 
	 * @param id
	 * @return
	 */
	public abstract Response doDelete(Long id) throws HarborException;

	/**
	 * This method is used active/inactive existing data.
	 * 
	 * @param id
	 * @return
	 */
	public abstract Response doActiveInActive(Long id) throws HarborException;

	/**
	 * This method used for grid display with pagination and Search Purpose
	 * 
	 * @param start
	 *            starting value of fetch record use for limit purpose
	 * @param recordSize
	 *            end value of fetch record use for limit purposes
	 * @return Response
	 */
	public Response doDisplayGrid(Integer start, Integer recordSize) {
		PageModel result = getService().getGridData(start, recordSize);
		if (result.getRecords() == 0) {
			return PageResultResponse.create(ResponseCode.NO_DATA_FOUND.getCode(), ResponseCode.NO_DATA_FOUND.getMessage(), 0, Collections.EMPTY_LIST);
		}
		return PageResultResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(), result.getRecords(), fromModelList((List<M>) result.getList()));
	}
	
	/**
	 * This method used for search data base on queries.
	 * @param start
	 * @param view
	 * @param recordSize
	 * @return
	 */
	public Response doSearch(V view, Integer start, Integer recordSize) throws HarborException{
		PageModel result = getService().search(view, start, recordSize);
		if (result.getRecords() == 0) {
			return PageResultResponse.create(ResponseCode.NO_DATA_FOUND.getCode(), ResponseCode.NO_DATA_FOUND.getMessage(), 0, Collections.EMPTY_LIST);
		}
		return PageResultResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(), result.getRecords(), fromModelList((List<M>) result.getList()));
	}

	/**
	 * This method is used to prepare model from view.
	 * 
	 * @param model
	 * @param view
	 * @return
	 */
	public abstract M toModel(M model, V view);

	/**
	 * This method is used to prepare model from view.
	 * 
	 * @param request
	 * @return
	 */
	protected M getModel(V view) {
		return toModel(getNewModel(), view);
	}

	/**
	 * This method used when require new model for view
	 * 
	 * @param view
	 *            view of model
	 * @return model
	 */
	protected abstract M getNewModel();

	/**
	 * This method used when need to convert model to view
	 * 
	 * @param model
	 * @return view
	 */
	public abstract V fromModel(M model);

	/**
	 * This method convert list of model to list of view
	 * 
	 * @param modelList
	 *            list of model
	 * @return list of view
	 */
	public List<V> fromModelList(List<M> modelList) {
		List<V> viewList = new ArrayList<>(modelList.size());
		for (M model : modelList) {
			viewList.add(fromModel(model));
		}
		return viewList;
	}

	/**
	 * This method use for get Service with respected operation
	 * 
	 * @return BaseService
	 */
	public abstract BaseService getService();

	/**
	 * This method is used to validate active or inactive state of model.
	 * 
	 * @param model
	 * @throws HarborException
	 */
	protected abstract void checkInactive(M model) throws HarborException;
}
