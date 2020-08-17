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
