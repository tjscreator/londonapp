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

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tjs.common.kernal.CustomInitializationBean;
import com.tjs.common.service.AbstractService;
import com.tjs.common.user.enums.GroupEnum;
import com.tjs.common.user.model.RoleModel;
import com.tjs.common.user.model.RoleModuleRightsModel;
import com.tjs.harbor.exception.HarborException;

/**
 * This class used to implement all database related operation that will be
 * performed on role table.
 * 
 * @author Nirav.Shah
 * @since 08/02/2018
 */
@Service(value = "roleService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class RoleServiceImpl extends AbstractService<RoleModel> implements RoleService, CustomInitializationBean {

	@Override
	public String getEntityName() {
		return ROLE_MODEL;
	}

	@Override
	public Criteria setCommonCriteria(String entityName) {
		Criteria criteria = getSession().createCriteria(entityName);
		criteria.add(Restrictions.eq("active", true));
		criteria.add(Restrictions.eq("archive", false));
		return criteria;
	}

	@Override
	public Criteria setSearchCriteria(Object searchObject, Criteria commonCriteria) {
		return commonCriteria;
	}

	@Override
	public void onStartUp() throws HarborException {
		List<RoleModel> roleModels = (List<RoleModel>) findAll(LIGHT_ROLE_MODEL);
		for (RoleModel roleModel : roleModels) {
			RoleModel.getMAP().put(roleModel.getId(), roleModel);
		}
	}

	@Override
	public RoleModel getLight(Long id) {
		return get(id, LIGHT_ROLE_MODEL);
	}

	@Override
	public RoleModel getByName(String name) {
		Criteria criteria = setCommonCriteria(LIGHT_ROLE_MODEL);
		criteria.add(Restrictions.eq("name", name));
		return (RoleModel) criteria.uniqueResult();
	}

	@Override
	public RoleModel getByGroup(GroupEnum groupEnum) {
		Criteria criteria = setCommonCriteria(LIGHT_ROLE_MODEL);
		criteria.createAlias("groupModel", "groupModel");
		criteria.add(Restrictions.eq("groupModel.id", Long.valueOf(groupEnum.getId())));
		return (RoleModel) criteria.uniqueResult();
	}

	@Override
	public Set<RoleModuleRightsModel> getRights(Long id) {
		Criteria criteria = getSession().createCriteria(LIGHT_ROLE_MODEL);
		criteria.add(Restrictions.eq("active", true));
		criteria.add(Restrictions.eq("archive", false));
		criteria.add(Restrictions.eq("id", id));
		RoleModel roleModel = (RoleModel) criteria.uniqueResult();
		return roleModel.getRoleModuleRightsModels();
	}
}
