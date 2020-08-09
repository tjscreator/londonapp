/*******************************************************************************
| * Copyright -2018 @Emotome
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
package com.emotome.common.setting.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.emotome.common.kernal.CustomInitializationBean;
import com.emotome.common.service.AbstractService;
import com.emotome.common.setting.model.SettingModel;
import com.emotome.harbor.exception.HarborException;

/**
 * This class used to implement all database related operation that will be
 * performed on setting table.
 * 
 * @author Nirav
 * @since 10/11/2018
 */
@Service(value = "settingService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class SettingServiceImpl extends AbstractService<SettingModel>
		implements SettingService, CustomInitializationBean {

	@Override
	public String getEntityName() {
		return SETTING_MODEL;
	}

	@Override
	public Criteria setCommonCriteria(String entityName) {
		Criteria criteria = getSession().createCriteria(getEntityName());
		return criteria;
	}

	@Override
	public Criteria setSearchCriteria(Object searchObject, Criteria commonCriteria) {
		return commonCriteria;
	}

	@Override
	public void onStartUp() throws HarborException {
		List<SettingModel> settingModelList = findAll();
		for (SettingModel settingTemplateModel : settingModelList) {
			SettingModel.getMAP().put(settingTemplateModel.getKey(), settingTemplateModel.getValue());
		}
	}

	@Override
	public SettingModel get(String key) {
		Criteria criteria = getSession().createCriteria(SETTING_MODEL);
		criteria.add(Restrictions.eq("key", key));
		return (SettingModel) criteria.uniqueResult();
	}
}
