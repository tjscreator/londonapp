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
package com.tjs.common.threadlocal;

import com.tjs.common.client.model.ClientModel;

/**
 * This class store hospital details into thread local to fetch hospital details in
 * current request.
 * 
 * @author Nirav.Shah
 * @since 26/03/2019
 */
public class HospitalAuditor {

	private static ThreadLocal<ClientModel> hospitalAuditor = new ThreadLocal<>();

	private HospitalAuditor() {
	}

	/**
	 * To get current hospital auditor details
	 * @return User
	 */
	public static ClientModel getHospitalAuditor() {
		return hospitalAuditor.get();
	}

	/**
	 * To set user details into thread local.
	 * 
	 * @return User
	 */
	public static void setHospitalAuditor(ClientModel hospitalModel) {
		hospitalAuditor.set(hospitalModel);
	}

	public static void removeHospitalAuditor() {
		hospitalAuditor.remove();
	}
}
