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
package com.tjs.common.file.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tjs.common.aop.AccessLog;
import com.tjs.common.aop.AccessedByClient;
import com.tjs.common.enums.ResponseCode;
import com.tjs.common.file.enums.DataSheetFileExtensionEnum;
import com.tjs.common.file.enums.FileUploadType;
import com.tjs.common.file.enums.ImageFileExtensionEnum;
import com.tjs.common.file.model.FileModel;
import com.tjs.common.file.operation.FileOperation;
import com.tjs.common.file.view.FileView;
import com.tjs.common.logger.LoggerService;
import com.tjs.common.response.CommonResponse;
import com.tjs.common.response.Response;
import com.tjs.common.setting.model.SettingModel;
import com.tjs.common.threadlocal.Auditor;
import com.tjs.common.user.enums.ModuleEnum;
import com.tjs.common.user.enums.RightsEnum;
import com.tjs.common.user.model.RoleModel;
import com.tjs.common.util.DateUtility;
import com.tjs.common.util.FileUtility;
import com.tjs.harbor.exception.HarborException;

/**
 * This controller maps all file upload related apis.
 * 
 * @author Dhruvang.Joshi
 * @since 07/12/2017
 */
@Controller
@RequestMapping("/private/file")
public class FilePrivateControllerImpl implements FilePrivateController {

	@Autowired
	FileOperation fileOperation;

	@Override
	@AccessLog
	@AccessedByClient
	public Response uploadUserProfileInBytes(@RequestParam(name = "file", required = false) MultipartFile multipartFile,
			@RequestParam(name = "id", required = false) Long id,
			@RequestParam(name = "name", required = false) String name) throws HarborException {
		isValidSaveCroppedImageData(multipartFile, id, name);
		MultipartFile mockMultipartFile = null;
		try {
			mockMultipartFile = new MockMultipartFile(
					multipartFile.getOriginalFilename().replace(multipartFile.getOriginalFilename(),
							name + "_" + String.valueOf(id) + "_" + DateUtility.getCurrentEpoch()) + ".jpg",
					multipartFile.getInputStream());
		} catch (IOException e) {
			LoggerService.exception(e);
			throw new HarborException(ResponseCode.UNABLE_TO_UPLOAD_FILE.getCode(),
					ResponseCode.UNABLE_TO_UPLOAD_FILE.getMessage());
		}
		File uploadedFile = FileUtility.uploadCroppedImages(mockMultipartFile, "images");
		return fileOperation.doSave(uploadedFile.getName(), ModuleEnum.USER.getId(), true);
	}

	@Override
	@AccessLog
	public Response uploadCompanyLogo(@RequestParam(name = "file", required = false) MultipartFile multipartFile,
			@RequestParam(name = "resizeRequired", required = false) Boolean resizeRequired,
			@RequestParam(name = "width", required = false) Integer width,
			@RequestParam(name = "height", required = false) Integer height) throws HarborException {

		isValidImageFile(multipartFile);
		hasAccess(Auditor.getAuditor().getUserRequestedRoleModel(), ModuleEnum.CLIENT.getId());
		File uploadedFile = FileUtility.upload(multipartFile, "images");
		String uploadedFileExtension = uploadedFile.getName().substring(uploadedFile.getName().lastIndexOf(".") + 1,
				uploadedFile.getName().length());

		String thumbNailName = null;
		if (resizeRequired != null && resizeRequired) {
			if (uploadedFileExtension.equals(FileUploadType.png.getName())
					|| uploadedFileExtension.equals(FileUploadType.jpeg.getName())
					|| uploadedFileExtension.equals(FileUploadType.jpg.getName())) {
				thumbNailName = FileUtility.createThumbNail(uploadedFile, width, height, "images");
				return fileOperation.doSaveWithThumbNail(uploadedFile.getName(), thumbNailName,
						ModuleEnum.CLIENT.getId());
			}
		}
		return fileOperation.doSave(uploadedFile.getName(), ModuleEnum.CLIENT.getId(), false);
	}

	@Override
	@AccessLog
	public Response downloadImage(@RequestParam(value = "fileId") String fileId,
			HttpServletResponse httpServletResponse) throws HarborException {
		if (StringUtils.isBlank(fileId)) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"FileId " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		FileModel fileModel = fileOperation.get(fileId);
		String filePath = SettingModel.getFilePath() + File.separator + "images" + File.separator + fileModel.getName();
		httpServletResponse.setHeader("Content-disposition", "attachment; filename=\"" + fileModel.getName() + "\"");
		httpServletResponse.setHeader("Cache-control", "max-age=31536000");
		httpServletResponse.setHeader("Content-Type", "image/jpeg");
		FileUtility.download(filePath, fileModel.getName(), httpServletResponse);
		return CommonResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage());
	}

	private void isValidSaveCroppedImageData(MultipartFile multipartFile, Long id, String name) throws HarborException {
		if (multipartFile == null || (multipartFile != null && multipartFile.isEmpty())) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"File " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		if (StringUtils.isBlank(name)) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"User name " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
	}

	private void isValidImageFile(MultipartFile multipartFile) throws HarborException {
		if (multipartFile == null || (multipartFile != null && multipartFile.isEmpty())) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"File " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		if (!multipartFile.getOriginalFilename().contains(".")) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(), "Invalid File format");
		} else {
			if (ImageFileExtensionEnum.fromId(multipartFile.getOriginalFilename()
					.substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1)) == null) {
				throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
						"Please upload image file(s), Other file types are not allowed.");
			}
		}
	}

	private void isValidDataSheetFile(MultipartFile multipartFile) throws HarborException {
		if (multipartFile == null || (multipartFile != null && multipartFile.isEmpty())) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"File " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		if (!multipartFile.getOriginalFilename().contains(".")) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(), "Invalid File format");
		} else {
			if (DataSheetFileExtensionEnum.fromId(multipartFile.getOriginalFilename()
					.substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1)) == null) {
				throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
						"Please upload image file(s), Other file types are not allowed.");
			}
		}
	}

	private void hasAccess(RoleModel roleModel, Integer moduleId) throws HarborException {
		if (!Auditor.getAuditor().hasAccess(roleModel.getId(), moduleId.longValue(),
				Integer.valueOf(RightsEnum.FILE_UPLOAD.getId()).longValue())) {
			throw new HarborException(ResponseCode.UNAUTHORIZED_ACCESS.getCode(),
					ResponseCode.UNAUTHORIZED_ACCESS.getMessage());
		}
	}

	@Override
	@AccessLog
	@AccessedByClient
	public Response uploadUserProfileBytesArray(@RequestBody FileView fileView) throws HarborException {
		isValidSaveData(fileView);
		File file = FileUtility.uploadCroppedBytesImages(fileView.getFileByte(), "images");
		return fileOperation.doSave(file.getName(), ModuleEnum.USER.getId(), true);
	}

	private void isValidSaveData(FileView fileView) throws HarborException {

		if (StringUtils.isEmpty(fileView.getFileByte())) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"File " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		if (StringUtils.isBlank(fileView.getName())) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"User name " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
	}
}