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
