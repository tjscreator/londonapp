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
package com.tjs.common.file.model;

import com.tjs.common.client.model.ClientModel;
import com.tjs.common.model.IdentifierModel;
import com.tjs.common.user.enums.ModuleEnum;
import com.tjs.common.util.DateUtility;
import com.tjs.common.util.Utility;

/**
 * This is model for File Uploading, Downloading, View.
 * 
 * @author Dhruvang Joshi.
 * @version 1.0
 * @since 25/11/2017
 */

public class FileModel extends IdentifierModel {
	private static final long serialVersionUID = -8868918297210365891L;

	private String name;
	private String fileId;
	private Long moduleId;
	private Long upload;
	private boolean publicfile;
	private ClientModel hospitalModel;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public ModuleEnum getModule() {
		return ModuleEnum.fromId(moduleId.intValue());
	}

	public void setModule(Long moduleId) {
		this.moduleId = moduleId;
	}

	public Long getUpload() {
		return upload;
	}

	public void setUpload(Long upload) {
		this.upload = upload;
	}

	public boolean isPublicfile() {
		return publicfile;
	}

	public void setPublicfile(boolean publicfile) {
		this.publicfile = publicfile;
	}

	public ClientModel getHospitalModel() {
		return hospitalModel;
	}

	public void setHospitalModel(ClientModel hospitalModel) {
		this.hospitalModel = hospitalModel;
	}

	public void setFileModel(String name, ModuleEnum moduleEnum, boolean isPublic) {
		this.setFileId(Utility.generateUuid());
		this.setName(name);
		this.setModule(Long.valueOf(moduleEnum.getId()));
		this.setPublicfile(isPublic);
		this.setUpload(DateUtility.getCurrentEpoch());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fileId == null) ? 0 : fileId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileModel other = (FileModel) obj;
		if (fileId == null) {
			if (other.fileId != null)
				return false;
		} else if (!fileId.equals(other.fileId))
			return false;
		return true;
	}
}
