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

import java.time.Instant;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.emotome.common.kernal.CustomInitializationBean;
import com.emotome.common.service.AbstractService;
import com.emotome.common.sms.model.SmsTransactionModel;
import com.emotome.harbor.email.enums.Status;
import com.emotome.harbor.exception.HarborException;

/**
 * 
 * @author JD
 * @since 16/05/2019
 */
@Service(value = "smsTransactionService")
public class SmsTransactionServiceImpl extends AbstractService<SmsTransactionModel>
		implements SmsTransactionService, CustomInitializationBean {

	@Override
	public void onStartUp() throws HarborException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getEntityName() {
		return SMS_TRANSACTION_MODEL;
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

	@Override
	public List<SmsTransactionModel> getDataByStatus(Integer statusId) {
		Criteria criteria = setCommonCriteria(getEntityName());
		criteria.add(Restrictions.eq("status", statusId));
		return criteria.list();
	}

	@Override
	public List<SmsTransactionModel> getSmsList(int limit) {
		Criteria criteria = getSession().createCriteria(getEntityName());
		criteria.setLockMode(LockMode.UPGRADE);
		criteria.setMaxResults(limit);
		criteria.addOrder(Order.asc("id"));
		criteria.add(Restrictions.eq("status", Status.NEW.getId()));
		criteria.add(Restrictions.le("dateSend", Instant.now().getEpochSecond()));
		return updateStatus(criteria, false);
	}

	private List<SmsTransactionModel> updateStatus(Criteria criteria, boolean isRetryAttempt) {
		List<SmsTransactionModel> smsList = criteria.list();
		for (SmsTransactionModel smsTransactional : smsList) {
			smsTransactional.setStatus((Status.SENT.getId()));
			smsTransactional.setDateSent(Instant.now().getEpochSecond());
			if (isRetryAttempt) {
				smsTransactional.setRetryCount(smsTransactional.getRetryCount() + 1);
			}
			update(smsTransactional);
		}
		return smsList;
	}

}
