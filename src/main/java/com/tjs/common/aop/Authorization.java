package com.tjs.common.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.tjs.common.user.enums.ModuleEnum;
import com.tjs.common.user.enums.RightsEnum;

/**
 * Authorization annotation
 * 
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Authorization {
	RightsEnum rights();

	ModuleEnum modules();
}
