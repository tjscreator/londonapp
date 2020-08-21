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

import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tjs.common.kernal.CustomInitializationBean;
import com.tjs.common.service.AbstractService;
import com.tjs.common.user.enums.ModuleEnum;
import com.tjs.common.user.model.ModuleModel;
import com.tjs.harbor.exception.HarborException;

/**
 * This class used to implement all database related operation that will be performed on module table.
 * @author Nirav.Shah
 * @since 14/02/2018
 */
@Service(value = "moduleService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor = Throwable.class)
public class ModuleServiceImpl extends AbstractService<ModuleModel> implements ModuleService, CustomInitializationBean {

	@Override
	public String getEntityName() {
		return MODULE_MODEL;
	}

	@Override
	public Criteria setCommonCriteria(String entityName) {
		Criteria criteria = getSession().createCriteria(entityName);
		return criteria;
	}

	@Override
	public Criteria setSearchCriteria(Object searchObject, Criteria commonCriteria) {
		return commonCriteria;
	}

	@Override
	public void onStartUp() throws HarborException {
		for(Entry<Integer, ModuleEnum> modulemap : ModuleEnum.MAP.entrySet()) {
			ModuleModel moduleModel = get(modulemap.getKey());
			if(moduleModel == null) {
				moduleModel = new ModuleModel();
				moduleModel.setId(modulemap.getKey().longValue());
				moduleModel.setName(modulemap.getValue().getName());
				create(moduleModel);
			}
		}
	}
}