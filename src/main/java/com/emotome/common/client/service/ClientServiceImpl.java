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
package com.emotome.common.client.service;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.emotome.common.client.model.ClientModel;
import com.emotome.common.client.view.ClientView;
import com.emotome.common.model.PageModel;
import com.emotome.common.service.AbstractService;

/**
 * 
 * @author Jaydip
 * @since 22/04/2019
 */
@Service(value = "clientService")
public class ClientServiceImpl extends AbstractService<ClientModel> implements ClientService {

	@Override
	public String getEntityName() {
		return CLIENT_MODEL;
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
		if (searchObject instanceof ClientView) {
			ClientView clientView = (ClientView) searchObject;
			if (!StringUtils.isEmpty(clientView.getName())) {
				commonCriteria.add(Restrictions.ilike("name", clientView.getName(), MatchMode.ANYWHERE));
			}
		}
		return commonCriteria;

	}

	@Override
	public ClientModel getLight(Long id) {
		Criteria criteria = setCommonCriteria(LIGHT_CLIENT_MODEL);
		criteria.add(Restrictions.eq("id", id));
		return (ClientModel) criteria.uniqueResult();
	}

	@Override
	public ClientModel getExtraLight(Long id) {
		Criteria criteria = setCommonCriteria(EXTRA_LIGHT_CLIENT_MODEL);
		criteria.add(Restrictions.eq("id", id));
		return (ClientModel) criteria.uniqueResult();
	}

	public ClientModel getLight(String apiKey) {
		Criteria criteria = setCommonCriteria(LIGHT_CLIENT_MODEL);
		criteria.add(Restrictions.eq("apiKey", apiKey));
		return (ClientModel) criteria.uniqueResult();
	}

	@Override
	public PageModel searchLight(ClientView clientView, Integer start, Integer recordSize) {
		return search(clientView, start, recordSize, LIGHT_CLIENT_MODEL);
	}
}