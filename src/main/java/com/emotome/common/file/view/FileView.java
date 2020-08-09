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
package com.emotome.common.file.view;

import org.springframework.web.multipart.MultipartFile;

import com.emotome.common.view.IdentifierView;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * This class is used to represent file object in json/in hospital response.
 * 
 * @author Nirav
 * @since 30/11/2017
 */
@JsonInclude(Include.NON_NULL)
public class FileView extends IdentifierView {

	private static final long serialVersionUID = 8692674149531174388L;
	private MultipartFile file;
	private String fileByte;
	private Integer moduleId;
	private String fileId;
	private String thumbNailId;
	private String name;
	private String thumbNailName;
	private boolean publicfile;

	public FileView() {
		super();
	}

	public FileView(String fileId) {
		super();
		this.fileId = fileId;
	}

	public FileView(String fileId, String name, boolean publicfile) {
		super();
		this.fileId = fileId;
		this.name = name;
		this.publicfile = publicfile;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public String getThumbNailId() {
		return thumbNailId;
	}

	public void setThumbNailId(String thumbNailId) {
		this.thumbNailId = thumbNailId;
	}

	public boolean isPublicfile() {
		return publicfile;
	}

	public void setPublicfile(boolean publicfile) {
		this.publicfile = publicfile;
	}

	public String getThumbNailName() {
		return thumbNailName;
	}

	public void setThumbNailName(String thumbNailName) {
		this.thumbNailName = thumbNailName;
	}

	public String getFileByte() {
		return fileByte;
	}

	public void setFileByte(String fileByte) {
		this.fileByte = fileByte;
	}

}
