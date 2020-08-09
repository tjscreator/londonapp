/*******************************************************************************
 * Copyright -2017 @Emotome
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
package com.emotome.common.email.service;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.emotome.common.email.model.EmailAccountModel;
import com.emotome.common.email.view.EmailAccountView;
import com.emotome.common.kernal.CustomInitializationBean;
import com.emotome.common.service.AbstractService;
import com.emotome.harbor.exception.HarborException;

/**
 * This is definition of Email Account service which defines database operation
 * which can be performed on this table.
 * 
 * @author Nirav.Shah
 * @since 12/08/2017
 */
@Service(value = "emailAccountService")
public class EmailAccountServiceImpl extends AbstractService<EmailAccountModel>
		implements EmailAccountService, CustomInitializationBean {
	@Override
	public void onStartUp() throws HarborException {
		for (EmailAccountModel emailAccountModel : findAll()) {
			EmailAccountModel.getMAP().put(emailAccountModel.getId(), emailAccountModel);
		}
	}

	@Override
	public String getEntityName() {
		return EMAIL_ACCOUNT;
	}

	@Override
	public Criteria setCommonCriteria(String entityName) {
		Criteria criteria = getSession().createCriteria(entityName);
		criteria.add(Restrictions.eq("archive", false));
		criteria.add(Restrictions.eq("active", true));
		return criteria;
	}

	@Override
	public Criteria setSearchCriteria(Object searchObject, Criteria commonCriteria) {
		if (searchObject instanceof EmailAccountView) {
			EmailAccountView emailAccountView = (EmailAccountView) searchObject;
			if (!StringUtils.isEmpty(emailAccountView.getName())) {
				commonCriteria.add(Restrictions.eq("name", emailAccountView.getName()));
			}
		}
		return commonCriteria;
	}

	@Override
	public EmailAccountModel getLight(Long id) {
		return get(id, LIGHT_EMAIL_ACCOUNT);
	}

}