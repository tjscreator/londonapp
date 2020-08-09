package com.emotome.common.file.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emotome.common.aop.AccessLog;
import com.emotome.common.controller.Controller;
import com.emotome.common.file.view.FileView;
import com.emotome.common.response.Response;
import com.emotome.harbor.exception.HarborException;

public interface FilePublicController extends Controller {

	/**
	 * This method is used to public upload hospital logo images in bytes array.
	 * 
	 * @param fileView
	 * @return
	 * @throws HarborException
	 */
	@RequestMapping(value = "/upload-hospital-logo", method = RequestMethod.POST)
	@ResponseBody
	@AccessLog
	Response uploadHospitalLogoBytesArray(@RequestBody FileView fileView) throws HarborException;

	/**
	 * this api for download image from public folder.
	 * 
	 * @param fileId
	 * @param httpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/download-image", method = RequestMethod.GET)
	@ResponseBody
	Response downloadImage(@RequestParam(value = "fileId") String fileId, HttpServletResponse httpServletResponse)
			throws HarborException;
}
