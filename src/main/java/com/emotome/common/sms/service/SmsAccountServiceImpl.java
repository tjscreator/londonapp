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
package com.emotome.common.sms.service;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.emotome.common.kernal.CustomInitializationBean;
import com.emotome.common.service.AbstractService;
import com.emotome.common.sms.model.SmsAccountModel;
import com.emotome.harbor.exception.HarborException;

/**
 * 
 * @author JD
 * @since 16/05/2019
 */
@Service(value = "smsAccountService")
public class SmsAccountServiceImpl extends AbstractService<SmsAccountModel>
		implements SmsAccountService, CustomInitializationBean {

	@Override
	public void onStartUp() throws HarborException {
		for (SmsAccountModel smsAccountModel : findAll()) {
			SmsAccountModel.getMAP().put("smsAccount", smsAccountModel);
		}
	}

	@Override
	public String getEntityName() {
		return SMS_ACCOUNT_MODEL;
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
		// TODO Auto-generated method stub
		return null;
	}

}
