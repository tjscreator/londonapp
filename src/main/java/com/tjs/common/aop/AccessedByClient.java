package com.tjs.common.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This is annotation which is used to validate a request can be performed by
 * hospital/not.
 * 
 * @author Nirav.Shah
 * @since 08/08/2018
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AccessedByClient {

}
