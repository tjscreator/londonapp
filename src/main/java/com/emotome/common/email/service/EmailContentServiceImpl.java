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

import com.emotome.common.email.model.EmailContentModel;
import com.emotome.common.email.view.EmailContentView;
import com.emotome.common.kernal.CustomInitializationBean;
import com.emotome.common.model.PageModel;
import com.emotome.common.service.AbstractService;
import com.emotome.harbor.exception.HarborException;

/**
 * This is definition of Email Content service which defines database operation
 * which can be performed on this table.
 * 
 * @author Nirav.Shah
 * @since 12/08/2017
 */
@Service(value = "emailContentService")
public class EmailContentServiceImpl extends AbstractService<EmailContentModel>
		implements EmailContentService, CustomInitializationBean {

	@Override
	public void onStartUp() throws HarborException {
		for (EmailContentModel emailContentModel : findAll()) {
			EmailContentModel.getMAP().put(emailContentModel.getId(), emailContentModel);
		}
	}

	@Override
	public String getEntityName() {
		return EMAIL_CONTENT;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Criteria setCommonCriteria(String entityName) {
		Criteria criteria = getSession().createCriteria(entityName);
		criteria.add(Restrictions.eq("archive", false));
		criteria.add(Restrictions.eq("active", true));
		return criteria;
	}

	@Override
	public Criteria setSearchCriteria(Object searchObject, Criteria commonCriteria) {
		if (searchObject instanceof EmailContentView) {
			EmailContentView emailContentView = (EmailContentView) searchObject;
			if (!StringUtils.isEmpty(emailContentView.getName())) {
				commonCriteria.add(Restrictions.eq("name", emailContentView.getName()));
			}
		}
		return commonCriteria;
	}

	@Override
	public EmailContentModel findByName(String name) {
		Criteria criteria = setCommonCriteria(LIGHT_EMAIL_CONTENT);
		criteria.add(Restrictions.eq("name", name));
		return (EmailContentModel) criteria.uniqueResult();
	}

	@Override
	public EmailContentModel findByTrigger(Integer triggerId) {
		Criteria criteria = setCommonCriteria(LIGHT_EMAIL_CONTENT);
		criteria.add(Restrictions.eq("triggerId", triggerId));
		return (EmailContentModel) criteria.uniqueResult();
	}
	
	@Override
	public EmailContentModel findByTriggerAndHospital(Integer triggerId,Long hospitalId) {
		Criteria criteria = setCommonCriteria(LIGHT_EMAIL_CONTENT);
		criteria.add(Restrictions.eq("triggerId", triggerId));
		criteria.add(Restrictions.eq("hospitalModel.id",hospitalId));
		return (EmailContentModel) criteria.uniqueResult();
	}

	@Override
	public PageModel searchLight(EmailContentView emailContentView, Integer start, Integer recordSize) {
		return search(emailContentView, start, recordSize, LIGHT_EMAIL_CONTENT);
	}

	@Override
	public PageModel getGridDataLight(Integer start, Integer recordSize) {
		return getGridData(start, recordSize, LIGHT_EMAIL_CONTENT);
	}

}