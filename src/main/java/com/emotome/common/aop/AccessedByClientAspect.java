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
package com.emotome.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.emotome.common.enums.ResponseCode;
import com.emotome.common.threadlocal.Auditor;
import com.emotome.common.user.model.UserModel;
import com.emotome.harbor.exception.HarborException;

/**
 * Accessed by Hospital aspect is validate a request can be performed by
 * hospital/not.
 * 
 * @author Nirav.Shah
 * @since 08/08/2018
 */
@Component
@Aspect
public class AccessedByClientAspect {

	/**
	 * To validate hospital model.
	 * 
	 * @param joinPoint
	 * @param authorization
	 * @throws Exception
	 */
	@Before("@annotation(accessedByHospital)")
	public void accessedByHospital(JoinPoint joinPoint, AccessedByClient accessedByHospital) throws Exception {
		UserModel userModel = Auditor.getAuditor();
		if (userModel.getUserRequestedClientModel() == null) {
			throw new HarborException(ResponseCode.UNAUTHORIZED_ACCESS.getCode(),
					ResponseCode.UNAUTHORIZED_ACCESS.getMessage());
		}
	}
}
