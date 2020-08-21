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
package com.tjs.common.user.model;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.tjs.common.model.ArchiveModel;
import com.tjs.common.model.IdentifierModel;

/**
 * Role model defines different types of stack holders.
 * 
 * @author Nirav.Shah
 * @since 05/08/2018
 *
 */
public class RoleModel extends ArchiveModel {

	private static final long serialVersionUID = -5764068071467332650L;

	private String name;
	private String description;
	private Long total;
	private Long totalVerified;
	private Set<RoleModuleRightsModel> roleModuleRightsModels = new HashSet<>();
	private AppModel appModel;
	private GroupModel groupModel;

	public RoleModel() {
		super();
	}

	public RoleModel(String name, String description, Long total, Long totalVerified, GroupModel groupModel) {
		super();
		this.name = name;
		this.description = description;
		this.total = total;
		this.totalVerified = totalVerified;
		this.groupModel = groupModel;
	}

	private static Map<Long, RoleModel> MAP = new ConcurrentHashMap<>();

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

	public static Map<Long, RoleModel> getMAP() {
		return MAP;
	}

	public static void setMAP(Map<Long, RoleModel> map) {
		MAP = map;
	}

	public Set<RoleModuleRightsModel> getRoleModuleRightsModels() {
		return roleModuleRightsModels;
	}

	public void setRoleModuleRightsModels(Set<RoleModuleRightsModel> roleModuleRightsModels) {
		this.roleModuleRightsModels = roleModuleRightsModels;
	}

	public void addRoleModuleRightsModels(RoleModuleRightsModel roleModuleRightsModels) {
		this.roleModuleRightsModels.add(roleModuleRightsModels);
	}

	public void removeRoleModuleRightsModels(RoleModuleRightsModel roleModuleRightsModels) {
		this.roleModuleRightsModels.remove(roleModuleRightsModels);
	}

	public GroupModel getGroupModel() {
		return groupModel;
	}

	public void setGroupModel(GroupModel groupModel) {
		this.groupModel = groupModel;
	}

	public AppModel getAppModel() {
		return appModel;
	}

	public void setAppModel(AppModel appModel) {
		this.appModel = appModel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		IdentifierModel other = (IdentifierModel) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}
}