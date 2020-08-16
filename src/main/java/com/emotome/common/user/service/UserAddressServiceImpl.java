package com.emotome.common.user.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
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
	public Criteria setCommonCriteria(Long userId) {
		Criteria criteria = setCommonCriteria(USER_ADDRESS_MODEL);
		criteria.createAlias("userModel", "userModel");
		criteria.add(Restrictions.eq("userModel.id", userId));
		return criteria;

	}

	@Override
	public Criteria setSearchCriteria(Object searchObject, Criteria commonCriteria) {
		return commonCriteria;
	}

	@Override
	public List<UserAddressModel> getByUser(Long userId) {
		Criteria criteria = setCommonCriteria(userId);
		return (List<UserAddressModel>) criteria.list();
	}
}
