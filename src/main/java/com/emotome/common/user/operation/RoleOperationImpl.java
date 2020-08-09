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
package com.emotome.common.user.operation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.emotome.common.enums.ResponseCode;
import com.emotome.common.model.PageModel;
import com.emotome.common.operation.AbstractOperation;
import com.emotome.common.response.CommonResponse;
import com.emotome.common.response.PageResultResponse;
import com.emotome.common.response.Response;
import com.emotome.common.response.ViewResponse;
import com.emotome.common.service.BaseService;
import com.emotome.common.threadlocal.Auditor;
import com.emotome.common.user.enums.ModuleEnum;
import com.emotome.common.user.enums.RightsEnum;
import com.emotome.common.user.model.RoleModel;
import com.emotome.common.user.model.RoleModuleRightsModel;
import com.emotome.common.user.service.GroupService;
import com.emotome.common.user.service.RoleService;
import com.emotome.common.user.service.UserService;
import com.emotome.common.user.view.GroupView;
import com.emotome.common.user.view.ModuleView;
import com.emotome.common.user.view.RightsView;
import com.emotome.common.user.view.RoleModuleRightsView;
import com.emotome.common.user.view.RoleView;
import com.emotome.harbor.exception.HarborException;

/**
 * This class used to perform all business operation on Announcement model.
 * 
 * @author Dhruvang.Joshi
 * @since 26/12/2018
 */
@Component(value = "roleOperation")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class RoleOperationImpl extends AbstractOperation<RoleModel, RoleView> implements RoleOperation {

	@Autowired
	private RoleService roleService;

	@Autowired
	private GroupService groupService;

	@Autowired
	private UserService userService;

	@Override
	public Response doAdd() throws HarborException {
		return null;
	}

	@Override
	public Response doView(Long id) throws HarborException {
		RoleModel roleModel = roleService.get(id);
		if (roleModel == null) {
			throw new HarborException(ResponseCode.NO_DATA_FOUND.getCode(), ResponseCode.NO_DATA_FOUND.getMessage());
		}
		RoleView roleView = fromModel(roleModel);
		if (roleModel.getRoleModuleRightsModels() != null) {
			setRoleModuleRightsView(roleView, roleModel);
		}
		setGroupView(roleView, roleModel);
		return ViewResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(), roleView);
	}

	private void setGroupView(RoleView roleView, RoleModel roleModel) {
		GroupView groupView = new GroupView();
		groupView.setId(roleModel.getGroupModel().getId().intValue());
		groupView.setName(roleModel.getGroupModel().getName());
		roleView.setGroupView(groupView);

	}

	private void setRoleModuleRightsView(RoleView roleView, RoleModel roleModel) {
		List<RoleModuleRightsView> roleModuleRightsViews = new ArrayList<>();
		for (RoleModuleRightsModel roleModuleRightsModel : roleModel.getRoleModuleRightsModels()) {
			RoleModuleRightsView roleModuleRightsView = new RoleModuleRightsView();
			RightsView rightsView = new RightsView();
			rightsView.setId(roleModuleRightsModel.getRightsId().intValue());
			rightsView.setName(String.valueOf(RightsEnum.fromId(roleModuleRightsModel.getRightsId().intValue())));
			ModuleView moduleView = new ModuleView();
			moduleView.setId(roleModuleRightsModel.getModuleId().intValue());
			moduleView.setName(String.valueOf(ModuleEnum.fromId(roleModuleRightsModel.getModuleId().intValue())));
			roleModuleRightsView.setModuleView(moduleView);
			roleModuleRightsView.setRightsView(rightsView);
			roleModuleRightsViews.add(roleModuleRightsView);
		}
		roleView.setRoleModuleRightsView(roleModuleRightsViews);
	}

	@Override
	public Response doEdit(Long id) throws HarborException {
		return doView(id);
	}

	@Override
	protected RoleModel loadModel(RoleView roleView) {
		return roleService.get(roleView.getId());
	}

	@Override
	public Response doDelete(Long id) throws HarborException {
		RoleModel roleModel = roleService.get(id);
		if (roleModel == null) {
			throw new HarborException(ResponseCode.NO_DATA_FOUND.getCode(), ResponseCode.NO_DATA_FOUND.getMessage());
		}
		roleModel.setArchive(true);
		roleService.delete(roleModel);
		return CommonResponse.create(ResponseCode.DELETE_SUCCESSFULLY.getCode(),
				ResponseCode.DELETE_SUCCESSFULLY.getMessage());
	}

	@Override
	public Response doActiveInActive(Long id) throws HarborException {
		RoleModel roleModel = roleService.get(id);
		if (roleModel == null) {
			throw new HarborException(ResponseCode.NO_DATA_FOUND.getCode(), ResponseCode.NO_DATA_FOUND.getMessage());
		}
		Auditor.activationAudit(roleModel, false);
		roleService.update(roleModel);

		return CommonResponse.create(ResponseCode.UPDATE_SUCCESSFULLY.getCode(),
				ResponseCode.UPDATE_SUCCESSFULLY.getMessage());
	}

	@Override
	public RoleModel toModel(RoleModel roleModel, RoleView roleView) {
		roleModel.setDescription(roleView.getDescription());
		roleModel.setName(roleView.getName());
		return roleModel;
	}

	@Override
	protected RoleModel getNewModel() {
		return new RoleModel();
	}

	@Override
	public Response doSave(RoleView roleView) throws HarborException {
		RoleModel roleModelExist = roleService.getByName(roleView.getName());
		if (roleModelExist != null) {
			throw new HarborException(ResponseCode.ALREADY_EXIST.getCode(),
					"Role Name " + ResponseCode.ALREADY_EXIST.getMessage());
		}
		RoleModel roleModel = toModel(getNewModel(), roleView);
		roleModel.setActive(true);
		roleModel.setTotal(0l);
		roleModel.setTotalVerified(0l);
		roleService.create(roleModel);
		setRoleModuleRights(roleModel, roleView);
		return CommonResponse.create(ResponseCode.SAVE_SUCCESSFULLY.getCode(),
				ResponseCode.SAVE_SUCCESSFULLY.getMessage());
	}

	@Override
	public RoleView fromModel(RoleModel roleModel) {
		RoleView roleView = new RoleView();
		roleView.setId(roleModel.getId());
		roleView.setName(roleModel.getName());
		roleView.setTotal(roleModel.getTotal());
		roleView.setTotalVerified(roleModel.getTotalVerified());
		return roleView;
	}

	@Override
	public BaseService getService() {
		return roleService;
	}

	@Override
	public Response doUpdate(RoleView roleView) throws HarborException {
		RoleModel roleModel = roleService.get(roleView.getId());
		if (roleModel == null) {
			throw new HarborException(ResponseCode.NO_DATA_FOUND.getCode(), ResponseCode.NO_DATA_FOUND.getMessage());
		}
		if (!roleModel.getName().equals(roleView.getName())) {
			RoleModel existRoleModel = roleService.getByName(roleView.getName());
			if (existRoleModel != null) {
				throw new HarborException(ResponseCode.ALREADY_EXIST.getCode(),
						"Role Name " + ResponseCode.ALREADY_EXIST.getMessage());
			}
		}
		roleModel = toModel(roleModel, roleView);
		roleService.update(roleModel);
		setRoleModuleRights(roleModel, roleView);

		return CommonResponse.create(ResponseCode.UPDATE_SUCCESSFULLY.getCode(),
				ResponseCode.UPDATE_SUCCESSFULLY.getMessage());
	}

	@Override
	public Response doSearch(RoleView roleView, Integer start, Integer recordSize) throws HarborException {
		PageModel pageModel = getService().search(roleView, start, recordSize);
		if (pageModel.getRecords() == 0) {
			return PageResultResponse.create(ResponseCode.NO_DATA_FOUND.getCode(),
					ResponseCode.NO_DATA_FOUND.getMessage(), 0, Collections.emptyList());
		}
		return PageResultResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(),
				pageModel.getRecords(), fromModelList((List<RoleModel>) pageModel.getList()));
	}

	@Override
	public Response doDisplayGrid(Integer start, Integer recordSize) {
		List<RoleView> roleViews = new ArrayList<>();
		for (Map.Entry<Long, RoleModel> entry : RoleModel.getMAP().entrySet()) {
			RoleView roleView = new RoleView();
			roleView.setId(entry.getKey());
			roleView.setName(entry.getValue().getName());
			roleViews.add(roleView);
		}
		return PageResultResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(),
				roleViews.size(), roleViews);
	}

	@Override
	protected void checkInactive(RoleModel roleModel) throws HarborException {
	}

	private void setRoleModuleRights(RoleModel roleModel, RoleView roleView) throws HarborException {

		RoleModuleRightsModel tempRoleModuleRightsModel;

		Set<RoleModuleRightsModel> existRoleModuleRightsModels = new HashSet<>();
		Set<RoleModuleRightsModel> toDeleteRoleModuleRightsModels = new HashSet<>();
		Set<RoleModuleRightsModel> toAddRoleModuleRightsModels = new HashSet<>();

		for (RoleModuleRightsView roleModuleRightsView : roleView.getRoleModuleRightsView()) {
			tempRoleModuleRightsModel = new RoleModuleRightsModel();
			ModuleEnum moduleEnum = ModuleEnum.fromId(roleModuleRightsView.getModuleView().getId());
			if (moduleEnum == null) {
				throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
						"Module " + ResponseCode.DATA_IS_MISSING.getMessage());
			}
			RightsEnum rightsEnum = RightsEnum.fromId(roleModuleRightsView.getRightsView().getId());
			if (rightsEnum == null) {
				throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
						"Right " + ResponseCode.DATA_IS_MISSING.getMessage());
			}
			tempRoleModuleRightsModel.setRoleId(roleModel.getId());
			tempRoleModuleRightsModel.setRightsId((long) rightsEnum.getId());
			tempRoleModuleRightsModel.setModuleId((long) moduleEnum.getId());
			if (roleModel.getRoleModuleRightsModels().contains(tempRoleModuleRightsModel) == false) {
				toAddRoleModuleRightsModels.add(tempRoleModuleRightsModel);
			} else {
				existRoleModuleRightsModels.add(tempRoleModuleRightsModel);
			}
		}

		for (RoleModuleRightsModel roleModuleRightsModel : roleModel.getRoleModuleRightsModels()) {
			if (existRoleModuleRightsModels.contains(roleModuleRightsModel) == false) {
				toDeleteRoleModuleRightsModels.add(roleModuleRightsModel);
			}
		}

		for (RoleModuleRightsModel roleModuleRightsModel : toDeleteRoleModuleRightsModels) {
			roleModel.removeRoleModuleRightsModels(roleModuleRightsModel);
		}
		for (RoleModuleRightsModel roleModuleRightsModel : toAddRoleModuleRightsModels) {
			roleModel.addRoleModuleRightsModels(roleModuleRightsModel);
		}
	}
}