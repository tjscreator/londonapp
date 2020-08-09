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

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.emotome.common.service.AbstractService;
import com.emotome.common.threadlocal.Auditor;
import com.emotome.common.user.model.UserModel;

@Service(value = "userService")
public class UserServiceImpl extends AbstractService<UserModel> implements UserService {

	@Override
	public String getEntityName() {
		return USER_MODEL;
	}

	@Override
	public Criteria setCommonCriteria(String entityName) {
		Criteria criteria = getSession().createCriteria(entityName);
		criteria.add(Restrictions.eq("archive", false));
		criteria.add(Restrictions.eq("active", true));
		if (Auditor.getAuditor() != null && Auditor.getAuditor().getUserRequestedClientModel() != null
				&& Auditor.getAuditor().getUserRequestedClientModel().getId() != null) {
			criteria.createAlias("clientModels", "clientModels");
			criteria.add(
					Restrictions.eq("clientModels.id", Auditor.getAuditor().getUserRequestedClientModel().getId()));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		}
		return criteria;
	}

	@Override
	public Criteria setSearchCriteria(Object searchObject, Criteria commonCriteria) {
		return commonCriteria;
	}

	@Override
	public UserModel getByEmail(String email) {
		Criteria criteria = getSession().createCriteria(EMAIL_MOBILE_USER_MODEL);
		criteria.add(Restrictions.eq("email", email));
		return (UserModel) criteria.uniqueResult();
	}

	@Override
	public UserModel getByMobile(String mobile) {
		Criteria criteria = getSession().createCriteria(EMAIL_MOBILE_USER_MODEL);
		criteria.add(Restrictions.eq("mobile", mobile));
		return (UserModel) criteria.uniqueResult();
	}

	@Override
	public UserModel getByToken(String token) {
		Criteria criteria = getSession().createCriteria(LIGHT_USER_MODEL);
		criteria.add(Restrictions.eq("verifyToken", token));
		return (UserModel) criteria.uniqueResult();
	}

	@Override
	public UserModel getByResetPasswordToken(String token) {
		Criteria criteria = getSession().createCriteria(LIGHT_USER_MODEL);
		criteria.add(Restrictions.eq("resetPasswordToken", token));
		return (UserModel) criteria.uniqueResult();
	}

	@Override
	public UserModel getLight(Long id) {
		Criteria criteria = setCommonCriteria(LIGHT_USER_MODEL);
		criteria.add(Restrictions.eq("id", id));
		return (UserModel) criteria.uniqueResult();
	}
}
