package com.emotome.common.setting.service;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;

import com.emotome.common.kernal.CustomInitializationBean;
import com.emotome.common.service.AbstractService;
import com.emotome.common.setting.model.AllowedDomainsModel;
import com.emotome.harbor.exception.HarborException;

/**
 * @author Jaydip
 * @since  29/03/2019
 */
@Service("allowedDomainsService")
public class AllowedDomainsServiceImpl extends AbstractService<AllowedDomainsModel> implements AllowedDomainsService,CustomInitializationBean {

	@Override
	public void onStartUp() throws HarborException {
		for (AllowedDomainsModel allowedDomainsModel: findAll()) {
			AllowedDomainsModel.getMAP().put(allowedDomainsModel.getId(), allowedDomainsModel.getDomain());
		}
	}

	@Override
	public String getEntityName() {
		return ALLOWED_DOMAINS_MODEL;
	}

	@Override
	public Criteria setCommonCriteria(String entityName) {
		Criteria criteria = getSession().createCriteria(ALLOWED_DOMAINS_MODEL);
		return criteria;
	}

	@Override
	public Criteria setSearchCriteria(Object searchObject, Criteria commonCriteria) {
		// TODO Auto-generated method stub
		return null;
	}
}
