/**
 * 
 */
package com.tjs.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tjs.common.model.Model;

/**
 * @author Dhruvang
 *
 */
public class WebUtil {
	
	private WebUtil(){
	}
	
	
	/**
     * It is used to get current http servlet request
     * @return HttpServletRequest
     */
	public static HttpServletRequest getCurrentRequest(){
       return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }
	
	public static HttpServletResponse getCurrentResponse(){
	       return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
	}
	
	/**
     * It is used to get current session using key,value parameter.
     * @param key key of Session
     * @param value value of Session  
     */
    public static void setToCurrentSession(String key, Model model) {
        getCurrentRequest().getSession(true).setAttribute(key, model);
    }
    
    public static void setUserSession(String key, String value) {
        getCurrentRequest().getSession(true).setAttribute(key, value);
    }
    
    /**
     * It is used to get current session using key,value parameter.
     * @param key key of Session
     * @param value value of Session  
     */
    public static void setSaltToCurrentSession(String key, String value) {
        getCurrentRequest().getSession(true).setAttribute(key, value);
    }
    
    /**
     * It is used to get current session using key parameter.
     * @param key key of Session
     * @return Object  
     */
    
    public static Object getFromCurrentSession(String key) {
        return getCurrentRequest().getSession(true).getAttribute(key);
    }
    
    /**
     * It is used to invalidate or remove Session. 
     */
     public static void invalidatSession(){
    	 getCurrentRequest().getSession(true).invalidate();
     }


	public static void setToCurrentSession(String key, String value) {
		 getCurrentRequest().getSession(true).setAttribute(key, value);
	}

}
