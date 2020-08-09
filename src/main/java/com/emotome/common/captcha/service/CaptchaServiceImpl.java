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
package com.emotome.common.captcha.service;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.emotome.common.captcha.model.CaptchaModel;
import com.emotome.common.service.AbstractService;

/**
 * This class used to implement all database related operation that will be performed on captcha table.
 * @author Nirav.Shah
 * @since 17/12/2018
 */
@Service(value = "captchaService")
public class CaptchaServiceImpl extends AbstractService<CaptchaModel> implements CaptchaService {

	@Override
	public String getEntityName() {
		return CAPTCHA_MODEL;
	}

	@Override
	public Criteria setCommonCriteria(String entityName) {
		Criteria criteria = getSession().createCriteria(getEntityName());
		return criteria;
	}

	@Override
	public Criteria setSearchCriteria(Object searchObject, Criteria commonCriteria) {
		return commonCriteria;
	}

	@Override
	public void delete(CaptchaModel captchaModel) {
		getSession().delete(captchaModel);
	}

	@Override
	public CaptchaModel get(String id) {
		Criteria criteria = getSession().createCriteria(CAPTCHA_MODEL);
		criteria.add(Restrictions.eq("id", id));
		return (CaptchaModel) criteria.uniqueResult();
	}
}