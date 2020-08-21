package com.tjs.common.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tjs.common.logger.LoggerService;
import com.tjs.common.threadlocal.Auditor;
import com.tjs.common.user.model.UserModel;

/**
 * Access logger aspect is log requested URI with the time taken by that URL.
 * 
 * @author Nirav.Shah
 * @since 08/08/2018
 */
@Component
@Aspect
public class AccessLogAspect {

	/**
	 * Used to log requested url & time taken by url.
	 * 
	 * @param pjp
	 * @param performanceLogger
	 * @return Object
	 * @throws Throwable
	 */
	@Around("@annotation(accessLog)")
	public Object around(ProceedingJoinPoint pjp, AccessLog accessLog) throws Throwable {
		long startTime = System.currentTimeMillis();
		try {
			return pjp.proceed();
		} finally {
			HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder
					.currentRequestAttributes()).getRequest();
			UserModel userModel = Auditor.getAuditor();
			if (userModel == null) {
				LoggerService.info(pjp.getSignature().getName().toUpperCase(), httpServletRequest.getRequestURI(),
						(System.currentTimeMillis() - startTime) + "ms");
			} else {
				LoggerService.info(pjp.getSignature().getName().toUpperCase(),
						userModel.getMobile() + " Requested for, " + httpServletRequest.getRequestURI(),
						(System.currentTimeMillis() - startTime) + "ms");
			}

		}
	}
}
