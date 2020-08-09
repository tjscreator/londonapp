package com.emotome.common.user.service;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;

import com.emotome.common.service.AbstractService;
import com.emotome.common.user.model.UserAddressModel;

@Service(value = "userAddressService")
public class UserAddressServiceImpl extends AbstractService<UserAddressModel> implements UserAddressService {

	@Override
	public String getEntityName() {
		return USER_ADDRESS_MODEL;
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

}
