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
package com.tjs.common.sms.service;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.tjs.common.kernal.CustomInitializationBean;
import com.tjs.common.service.AbstractService;
import com.tjs.common.sms.model.SmsContentModel;
import com.tjs.common.threadlocal.Auditor;
import com.tjs.common.user.enums.GroupEnum;
import com.tjs.harbor.exception.HarborException;

/**
 * 
 * @author JD
 * @since 16/05/2019
 */
@Service(value = "smsContentService")
public class SmsContentServiceImpl extends AbstractService<SmsContentModel>
		implements SmsContentService, CustomInitializationBean {

	@Override
	public void onStartUp() throws HarborException {
		for (SmsContentModel smsContentModel : findAll()) {
			SmsContentModel.getMAP().put(smsContentModel.getId(), smsContentModel);
		}
	}

	@Override
	public String getEntityName() {
		return SMS_CONTENT_MODEL;
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
		if (Auditor.getAuditor() != null && Auditor.getAuditor().getUserRequestedClientModel() == null
				&& Auditor.getAuditor().getUserRequestedRoleModel() != null) {
			if (Auditor.getAuditor().getUserRequestedRoleModel().getGroupModel().equals(Long.valueOf(GroupEnum.MASTER_ADMIN.getId()))) {
				commonCriteria.add(Restrictions.isNull("hospitalModel"));
			}
		}
		return commonCriteria;
	}

	@Override
	public SmsContentModel findByTriggerAndHospital(Integer triggerId, Long hospitalId) {
		Criteria criteria = setCommonCriteria(LIGHT_SMS_CONTENT_MODEL);
		if (hospitalId != null) {
			criteria.createAlias("hospitalModel", "hospitalModel");
			criteria.add(Restrictions.eq("hospitalModel.id", hospitalId));
		}
		criteria.add(Restrictions.eq("triggerId", triggerId));
		return (SmsContentModel) criteria.uniqueResult();
	}

}
