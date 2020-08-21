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
package com.tjs.common.user.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tjs.common.service.AbstractService;
import com.tjs.common.user.model.UserPasswordModel;

/**
 * This class used to implement all database related operation that will be
 * performed on user password table.
 * 
 * @author Nirav.Shah
 * @since 23/06/2018
 */
@Service(value = "userPasswordService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor = Throwable.class)
public class UserPasswordServiceImpl extends AbstractService<UserPasswordModel> implements UserPasswordService {

	@Override
	public String getEntityName() {
		return USER_PASSWORD_MODEL;
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
	public UserPasswordModel getCurrent(long userId) {
		Criteria criteria = getSession().createCriteria(getEntityName());
		criteria.createAlias("userModel", "userModel");
		criteria.add(Restrictions.eq("userModel.id", userId));
		criteria.addOrder(Order.desc("create"));
		criteria.setMaxResults(1);
		return (UserPasswordModel) criteria.uniqueResult();
	}

	@Override
	public List<UserPasswordModel> getByUser(long userId) {
		Criteria criteria = getSession().createCriteria(getEntityName());
		criteria.addOrder(Order.desc("create"));
		criteria.add(Restrictions.eq("userModel.id", userId));
		return (List<UserPasswordModel>)criteria.list();
	}
}