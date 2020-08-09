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

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.emotome.common.enums.ResponseCode;
import com.emotome.common.file.model.FileModel;
import com.emotome.common.file.service.FileService;
import com.emotome.common.file.view.FileView;
import com.emotome.common.response.Response;
import com.emotome.common.response.ViewResponse;
import com.emotome.common.threadlocal.Auditor;
import com.emotome.common.util.DateUtility;
import com.emotome.common.util.Utility;
import com.emotome.harbor.exception.HarborException;

/**
 * This class used to perform all business operation on file model.
 * 
 * @author Dhruvang.Joshi
 * @since 30/11/2017
 */
@Component(value = "fileOperation")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class FileOperationImpl implements FileOperation {

	@Autowired
	private FileService fileService;

	@Override
	public FileService getService() {
		return fileService;
	}

	@Override
	public Response doSave(String fileName, Integer moduleId, boolean isPublic) throws HarborException {
		FileModel fileModel = toModel(getNewModel(), fileName, moduleId, isPublic);
		if (Auditor.getAuditor() != null) {
			fileModel.setHospitalModel(Auditor.getAuditor().getUserRequestedClientModel());
		}
		fileService.create(fileModel);
		return ViewResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(),
				fromModel(fileModel));
	}

	@Override
	public Response doSaveWithThumbNail(String fileName, String thumbNailName, Integer moduleId)
			throws HarborException {
		FileModel fileModel = toModel(getNewModel(), fileName, moduleId, false);
		fileModel.setHospitalModel(Auditor.getAuditor().getUserRequestedClientModel());
		fileService.create(fileModel);
		FileView fileView = fromModel(fileModel);

		FileModel thumbNailModel = toModel(getNewModel(), thumbNailName, moduleId, false);
		thumbNailModel.setHospitalModel(Auditor.getAuditor().getUserRequestedClientModel());
		fileService.create(thumbNailModel);
		fileView.setThumbNailName(thumbNailModel.getName());
		fileView.setThumbNailId(thumbNailModel.getFileId());
		return ViewResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(), fileView);
	}

	@Override
	public FileModel toModel(FileModel fileModel, String fileName, Integer moduleId, boolean isPublic) {
		fileModel.setFileId(Utility.generateUuid());
		fileModel.setName(fileName);
		fileModel.setModule(moduleId.longValue());
		fileModel.setPublicfile(isPublic);
		fileModel.setUpload(DateUtility.getCurrentEpoch());
		return fileModel;
	}

	@Override
	public FileModel getModel(FileView fileView) {
		return fileService.getByFileId(fileView.getFileId());
	}

	@Override
	public FileModel getNewModel() {
		return new FileModel();
	}

	@Override
	public FileView fromModel(FileModel fileModel) {
		FileView fileView = new FileView();
		fileView.setName(fileModel.getName());
		fileView.setFileId(fileModel.getFileId());
		fileView.setPublicfile(fileModel.isPublicfile());
		return fileView;
	}

	@Override
	public List<FileView> fromModelList(List<FileModel> fileModels) {
		List<FileView> fileViews = new ArrayList<>(fileModels.size());
		for (FileModel fileModel : fileModels) {
			fileViews.add(fromModel(fileModel));
		}
		return fileViews;
	}

	@Override
	public FileModel get(String fileId) throws HarborException {
		FileModel fileModel = fileService.getByFileId(fileId);
		if (fileModel == null) {
			throw new HarborException(ResponseCode.NO_DATA_FOUND.getCode(), ResponseCode.NO_DATA_FOUND.getMessage());
		}
		return fileModel;
	}
}