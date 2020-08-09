package com.emotome.common.client.service;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.emotome.common.client.model.ClientBranchModel;
import com.emotome.common.service.AbstractService;

public class ClientBranchServiceImpl extends AbstractService<ClientBranchModel> implements ClientBranchService {

	@Override
	public String getEntityName() {
		return CLIENT_BRANCH_MODEL;
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
		return commonCriteria;
	}

}
