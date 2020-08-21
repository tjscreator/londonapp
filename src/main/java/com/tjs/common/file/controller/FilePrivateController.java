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

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tjs.common.aop.AccessLog;
import com.tjs.common.controller.Controller;
import com.tjs.common.file.view.FileView;
import com.tjs.common.response.Response;
import com.tjs.harbor.exception.HarborException;

/**
 * 
 * @author Dhruvang.Joshi
 * @since 09/11/2017
 *
 */
public interface FilePrivateController extends Controller {

	/**
	 * This method is used to upload an attachment
	 * 
	 * @param multipartFile
	 * @param moduleId
	 * @param thumbNailRequired
	 * @param width
	 * @param height
	 * @param isONRequired
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/upload-company-logo", method = RequestMethod.POST)
	@ResponseBody
	@AccessLog
	Response uploadCompanyLogo(@RequestParam(name = "file", required = false) MultipartFile multipartFile,
			@RequestParam(name = "resizeRequired", required = false) Boolean resizeRequired,
			@RequestParam(name = "width", required = false) Integer width,
			@RequestParam(name = "height", required = false) Integer height) throws HarborException;

	/**
	 * This method is used to upload user profile images in bytes format.
	 * 
	 * @param multipartFile
	 * @param moduleId
	 * @param id
	 * @param name
	 * @return
	 * @throws HarborException
	 */
	@RequestMapping(value = "/upload-user-profile-in-bytes", method = RequestMethod.POST)
	@ResponseBody
	@AccessLog
	Response uploadUserProfileInBytes(@RequestParam(name = "file", required = false) MultipartFile multipartFile,
			@RequestParam(name = "id", required = false) Long id,
			@RequestParam(name = "name", required = false) String name) throws HarborException;

	/**
	 * 
	 * @param fileId
	 * @param httpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/download-image", method = RequestMethod.GET)
	@ResponseBody
	@AccessLog
	Response downloadImage(@RequestParam(value = "fileId") String fileId, HttpServletResponse httpServletResponse)
			throws HarborException;

	/**
	 * This method is used to upload user profile images in bytes array.
	 * 
	 * @param fileByte
	 * @param id
	 * @param name
	 * @return
	 * @throws HarborException
	 */
	@RequestMapping(value = "/upload-user-profile-bytes", method = RequestMethod.POST)
	@ResponseBody
	@AccessLog
	Response uploadUserProfileBytesArray(@RequestBody FileView fileView) throws HarborException;
}