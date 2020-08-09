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

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.emotome.common.enums.ResponseCode;
import com.emotome.common.logger.LoggerService;
import com.emotome.common.threadlocal.Auditor;
import com.emotome.common.user.enums.ModuleEnum;
import com.emotome.common.user.enums.RightsEnum;
import com.emotome.common.user.model.UserModel;
import com.emotome.harbor.exception.HarborException;

/**
 * This will be used to perform access control validation. This aspect will be
 * applied when method has been annotated with Authorization Annotation.
 * 
 * @version 1.0
 */
@Component
@Aspect
public class AuthorizationAspect {

	@Before("@annotation(authorization)")
	public void authorized(JoinPoint joinPoint, Authorization authorization) throws Exception {
		UserModel userModel = Auditor.getAuditor();
		if (userModel == null) {
			HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder
					.currentRequestAttributes()).getRequest();
			LoggerService.info(joinPoint.getSignature().getName().toUpperCase(),
					" Unauthorized access, " + httpServletRequest.getRequestURI(), "");
			throw new HarborException(ResponseCode.UNAUTHORIZED_ACCESS.getCode(),
					ResponseCode.UNAUTHORIZED_ACCESS.getMessage());
		}
		RightsEnum rightsEnum = authorization.rights();
		ModuleEnum moduleEnum = authorization.modules();
		if (!userModel.hasAccess(userModel.getUserRequestedRoleModel().getId(),
				Integer.valueOf(moduleEnum.getId()).longValue(), Integer.valueOf(rightsEnum.getId()).longValue())) {
			HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder
					.currentRequestAttributes()).getRequest();
			LoggerService.info(joinPoint.getSignature().getName().toUpperCase(),
					" Unauthorized access of, " + httpServletRequest.getRequestURI(), " by " + userModel.getMobile());
			throw new HarborException(ResponseCode.UNAUTHORIZED_ACCESS.getCode(),
					ResponseCode.UNAUTHORIZED_ACCESS.getMessage());
		}
	}
}
