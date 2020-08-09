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
package com.emotome.common.file.operation;

import java.util.List;

import com.emotome.common.file.model.FileModel;
import com.emotome.common.file.service.FileService;
import com.emotome.common.file.view.FileView;
import com.emotome.common.operation.Operation;
import com.emotome.common.response.Response;
import com.emotome.harbor.exception.HarborException;

/**
 *
 * @author Dhruvang Joshi.
 * @version 1.0
 * @since 25/11/2017
 */

public interface FileOperation extends Operation {

	/**
	 * This method is used to save file name with its details.
	 * 
	 * @param fileName
	 * @param moduleId
	 * @param isPublic
	 * @return
	 * @throws HarborException
	 */
	Response doSave(String fileName, Integer moduleId, boolean isPublic) throws HarborException;

	/**
	 * This method is used to save file & thumb nail name with its details.
	 * 
	 * @param fileName
	 * @param thumbNailName
	 * @param moduleId
	 * @param ispublic
	 * @return
	 * @throws HarborException
	 */
	Response doSaveWithThumbNail(String fileName, String thumbNailName, Integer moduleId) throws HarborException;

	/**
	 * This method is used to prepare model from view.
	 * 
	 * @param fileModel
	 * @param fileName
	 * @param moduleId
	 * @param isPublic
	 * @return
	 */
	FileModel toModel(FileModel fileModel, String fileName, Integer moduleId, boolean isPublic);

	/**
	 * This method is used to prepare model from view.
	 * 
	 * @param request
	 * @return
	 */
	FileModel getModel(FileView fileView);

	/**
	 * This method used when require new model for view
	 * 
	 * @param view view of model
	 * @return model
	 */
	FileModel getNewModel();

	/**
	 * This method used when need to convert model to view
	 * 
	 * @param model
	 * @return view
	 */

	FileView fromModel(FileModel fileModel);

	/**
	 * This method convert list of model to list of view
	 * 
	 * @param modelList list of model
	 * @return list of view
	 */

	List<FileView> fromModelList(List<FileModel> fileModels);

	/**
	 * This method use for get Service with respected operation
	 * 
	 * @return FileService
	 */

	FileService getService();

	/**
	 * This method is used to get file details using file Id.
	 * 
	 * @param fileId
	 * @return
	 * @throws IAException
	 */
	FileModel get(String fileId) throws HarborException;
}
