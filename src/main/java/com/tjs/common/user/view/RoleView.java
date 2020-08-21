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
package com.tjs.common.user.view;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tjs.common.client.view.ClientView;
import com.tjs.common.view.ArchiveView;

/**
 * This class is used to represent role object in json/in hospital response.
 * 
 * @author Nirav.Shah
 * @since 08/02/2018
 */
@JsonInclude(Include.NON_NULL)
public class RoleView extends ArchiveView {

	private static final long serialVersionUID = -4444717308537621033L;
	private String name;
	private String description;
	private Long total;
	private Long totalVerified;
	private List<RoleModuleRightsView> roleModuleRightsView;
	private ClientView hospitalView;
	private GroupView groupView;

	public ClientView getHospitalView() {
		return hospitalView;
	}

	public void setHospitalView(ClientView hospitalView) {
		this.hospitalView = hospitalView;
	}

	public RoleView() {
		super();
	}

	public RoleView(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<RoleModuleRightsView> getRoleModuleRightsView() {
		return roleModuleRightsView;
	}

	public void setRoleModuleRightsView(List<RoleModuleRightsView> roleModuleRightsView) {
		this.roleModuleRightsView = roleModuleRightsView;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Long getTotalVerified() {
		return totalVerified;
	}

	public void setTotalVerified(Long totalVerified) {
		this.totalVerified = totalVerified;
	}

	public GroupView getGroupView() {
		return groupView;
	}

	public void setGroupView(GroupView groupView) {
		this.groupView = groupView;
	}
}
