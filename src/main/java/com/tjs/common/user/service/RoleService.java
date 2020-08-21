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
package com.tjs.common.user.service;

import java.util.Set;

import com.tjs.common.service.BaseService;
import com.tjs.common.user.enums.GroupEnum;
import com.tjs.common.user.model.RoleModel;
import com.tjs.common.user.model.RoleModuleRightsModel;

/**
 * 
 * @author Nirav.Shah
 * @since 08/02/2018
 */
public interface RoleService extends BaseService<RoleModel> {
	String ROLE_MODEL = "roleModel";
	String LIGHT_ROLE_MODEL = "lightRoleModel";
	String UPDATE_TOTAL_COUNT = "updateTotalCount";
	String UPDATE_TOTAL_VERIFIED_COUNT = "updateTotalVerifiedCount";
	String DELETE_UPDATE_TOTAL_COUNT = "deleteUpdateTotalCount";

	/**
	 * This method is used to get role model using different entities.
	 * 
	 * @param id
	 * @return
	 */
	RoleModel getLight(Long id);

	/**
	 * This method is used to get role model using different entities.
	 * 
	 * @param name
	 * @return
	 */
	RoleModel getByName(String name);

	/**
	 * this method get role based on group.
	 * 
	 * @param groupEnum
	 * @return
	 */
	RoleModel getByGroup(GroupEnum groupEnum);

	/**
	 * This method is used to fetch role base rights.
	 * 
	 * @param id
	 * @param clientModel
	 * @return
	 */
	Set<RoleModuleRightsModel> getRights(Long id);

}
