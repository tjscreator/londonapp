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
package com.emotome.common.user.model;

import com.emotome.common.model.Model;

/**
 * This is User display grid model which maps user table to class.
 * 
 * @author Nirav.Shah
 * @since 24/07/2018
 */
public class UserDisplayGridModel implements Model {
	private static final long serialVersionUID = -5764068071467332650L;

	private Long id;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private Boolean active;
	private Boolean archive;
	private String mobile;
	private String fileId;
	private String fileName;
	private Boolean publicfile;
	private String roleName;
	private Long createDate;
	private Boolean completeProfile;
	private Long lastlogin;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public Long getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(Long lastlogin) {
		this.lastlogin = lastlogin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getArchive() {
		return archive;
	}

	public void setArchive(Boolean archive) {
		this.archive = archive;
	}

	public Boolean getPublicfile() {
		return publicfile;
	}

	public void setPublicfile(Boolean publicfile) {
		this.publicfile = publicfile;
	}

	public Boolean getCompleteProfile() {
		return completeProfile;
	}

	public void setCompleteProfile(Boolean completeProfile) {
		this.completeProfile = completeProfile;
	}

}