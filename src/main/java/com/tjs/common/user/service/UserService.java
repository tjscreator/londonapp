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

import com.tjs.common.service.BaseService;
import com.tjs.common.user.model.UserModel;

/**
 * 
 * @author Nirav
 * @since 11/09/2018
 */
public interface UserService extends BaseService<UserModel> {

	String USER_MODEL = "userModel";
	String LIGHT_USER_MODEL = "lightUserModel";
	String EMAIL_MOBILE_USER_MODEL = "emailMobileUserModel";

	/**
	 * This method is used to find user through their email id.
	 * 
	 * @param email
	 * @return
	 */
	UserModel getByEmail(String email);

	/**
	 * This method is used to find user through their mobile.
	 * 
	 * @param mobile
	 * @return
	 */
	UserModel getByMobile(String mobile);

	/**
	 * This method is used to find user based on verification token.
	 * 
	 * @param token
	 * @return
	 */
	UserModel getByToken(String token);

	/**
	 * This method is used to find user based on reset password token.
	 * 
	 * @param token
	 * @return
	 */
	UserModel getByResetPasswordToken(String token);

	/**
	 * This method is used to get light entity.
	 * 
	 * @param id
	 * @return
	 */
	UserModel getLight(Long id);
}