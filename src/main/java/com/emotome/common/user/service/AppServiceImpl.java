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
package com.emotome.common.user.service;

import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.emotome.common.kernal.CustomInitializationBean;
import com.emotome.common.service.AbstractService;
import com.emotome.common.user.enums.AppEnum;
import com.emotome.common.user.model.AppModel;
import com.emotome.harbor.exception.HarborException;

/**
 * This class used to implement all database related operation that will be
 * performed on app table.
 * 
 * @author Nirav.Shah
 * @since 14/02/2018
 */
@Service(value = "appService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class AppServiceImpl extends AbstractService<AppModel> implements AppService, CustomInitializationBean {

	@Override
	public String getEntityName() {
		return APP_MODEL;
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
		for (Entry<Integer, AppEnum> appEnumMap : AppEnum.MAP.entrySet()) {
			AppModel appModel = get(appEnumMap.getKey());
			if (appModel == null) {
				appModel = new AppModel();
				appModel.setId(appEnumMap.getKey().longValue());
				appModel.setName(appEnumMap.getValue().getName());
				create(appModel);
			}
		}
	}
}