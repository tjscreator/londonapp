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
import com.emotome.common.user.enums.RightsEnum;
import com.emotome.common.user.model.RightsModel;
import com.emotome.harbor.exception.HarborException;

/**
 * This class used to implement all database related operation that will be
 * performed on rights table.
 * 
 * @author Nirav.Shah
 * @since 26/02/2018
 */
@Service(value = "rightsService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor = Throwable.class)
public class RightsServiceImpl extends AbstractService<RightsModel> implements RightsService, CustomInitializationBean {

	@Override
	public String getEntityName() {
		return RIGHTS_MODEL;
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
		for (Entry<Integer, RightsEnum> rightsmap : RightsEnum.MAP.entrySet()) {
			RightsModel rightsModel = get(rightsmap.getKey());
			if (rightsModel == null) {
				rightsModel = new RightsModel();
				rightsModel.setId(rightsmap.getKey().longValue());
				rightsModel.setName(rightsmap.getValue().getName());
				create(rightsModel);
			}
		}
	}
}