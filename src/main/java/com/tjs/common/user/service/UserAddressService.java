package com.tjs.common.user.service;

import java.util.List;

import org.hibernate.Criteria;

import com.tjs.common.service.BaseService;
import com.tjs.common.user.model.UserAddressModel;

/**
 * 
 * @author jaydip.golviya
 * @since 09/08/2020
 */
public interface UserAddressService extends BaseService<UserAddressModel> {

	String USER_ADDRESS_MODEL = "userAddressModel";

	/**
	 * this method for set common criteria.
	 * 
	 * @param userId
	 * @return
	 */
	Criteria setCommonCriteria(Long userId);

	/**
	 * this method used to get all address of user.
	 * 
	 * @param userId
	 * @return
	 */
	List<UserAddressModel> getByUser(Long userId);
}
