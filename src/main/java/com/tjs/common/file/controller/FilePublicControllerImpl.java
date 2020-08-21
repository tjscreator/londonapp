package com.tjs.common.file.controller;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjs.common.aop.AccessLog;
import com.tjs.common.enums.ResponseCode;
import com.tjs.common.file.model.FileModel;
import com.tjs.common.file.operation.FileOperation;
import com.tjs.common.file.view.FileView;
import com.tjs.common.response.CommonResponse;
import com.tjs.common.response.Response;
import com.tjs.common.user.enums.ModuleEnum;
import com.tjs.common.util.Constant;
import com.tjs.common.util.FileUtility;
import com.tjs.harbor.exception.HarborException;

/**
 * This controller maps all file upload related apis.
 * 
 * @author Dhruvang.Joshi
 * @since 07/12/2017
 */
@Controller
@RequestMapping("/public/file")
public class FilePublicControllerImpl implements FilePublicController {

	@Autowired
	private FileOperation fileOperation;

	@Override
	@AccessLog
	public Response uploadHospitalLogoBytesArray(@RequestBody FileView fileView) throws HarborException {
		isValidSaveData(fileView);
		File file = FileUtility.uploadPublicImage(fileView.getFileByte(), fileView.getName());
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

	@Override
	@AccessLog
	public Response downloadImage(@RequestParam(value = "fileId") String fileId,
			HttpServletResponse httpServletResponse) throws HarborException {
		if (StringUtils.isBlank(fileId)) {
			throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
					"FileId " + ResponseCode.DATA_IS_MISSING.getMessage());
		}
		FileModel fileModel = fileOperation.get(fileId);
		String filePath = Constant.URL_FOR_PUBLIC_UPLOAD_IMAGE + File.separator + fileModel.getName();
		httpServletResponse.setHeader("Content-disposition", "attachment; filename=\"" + fileModel.getName() + "\"");
		httpServletResponse.setHeader("Cache-control", "max-age=31536000");
		FileUtility.download(filePath, fileModel.getName(), httpServletResponse);
		return CommonResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage());
	}

}
