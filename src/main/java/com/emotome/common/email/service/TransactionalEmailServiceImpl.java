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

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.emotome.common.email.model.TransactionalEmailModel;
import com.emotome.common.service.AbstractService;

/**
 * This is definition of Email service which defines database operation which
 * can be performed on this table.
 * 
 * @author Nirav.Shah
 * @since 12/08/2017
 */
@Service(value = "transactionEmailService")
public class TransactionalEmailServiceImpl extends AbstractService<TransactionalEmailModel>
		implements TransactionalEmailService {

	@Override
	public String getEntityName() {
		return "transactionalEmailModel";
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
		/*if (searchObject instanceof TransactionalEmailModel) {
			TransactionalEmailView transactionalEmailView = (TransactionalEmailView) searchObject;
			if (!StringUtils.isEmpty(transactionalEmailView.getEmailTo())) {
				commonCriteria.add(Restrictions.eq("name", transactionalEmailView.getEmailTo()));
			}
		}*/
		return commonCriteria;
	}
}
