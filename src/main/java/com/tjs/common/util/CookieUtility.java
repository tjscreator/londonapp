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
package com.tjs.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;


public class CookieUtility {
	
	public static final String COOKIE_FOR_USER_ID = "Harbor";
    
	private CookieUtility(){
	}
	
	/**
	 * THis method will get cookie value from cookie name.
	 * @param cookieName
	 * @param defaultVal
	 * @return
	 */
    public static String getCookie(String cookieName, String defaultVal) {
        Cookie[] cookies = WebUtil.getCurrentRequest().getCookies();
        if (cookies == null) {
            return defaultVal;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equalsIgnoreCase(cookieName) && StringUtils.isNotBlank(cookie.getValue())) {
                return cookie.getValue();
            }
        }
        return defaultVal;
    }
    
    /**
     * this method will return all the cookies whose name contains  specific string.
     * @param defaultVal
     * @return
     */
    public static String getCookiesBasedOnSufix(String defaultVal) {
   	 String cookiesNames ="";
        Cookie[] cookies = WebUtil.getCurrentRequest().getCookies();
        if (cookies == null) {
            return defaultVal;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().contains(COOKIE_FOR_USER_ID)) {
           	 cookiesNames += cookie.getName() + ",";
            }
        }
        return cookiesNames;
    }

    /**
     * This method is used to set cookies at hospital side.
     * @param response
     * @param cookieName
     * @param value
     * @param maxAge
     * @param path
     * @return
     */
    public static void setCookie(HttpServletResponse response, String cookieName, String value, Integer maxAge, String path) {
        if (response == null || StringUtils.isBlank(cookieName) || StringUtils.isBlank(value)) {
            return;
        }
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setMaxAge(maxAge != null ? maxAge.intValue() : (60*60*24*365*10));
        cookie.setPath(StringUtils.isNotBlank(path) ? path : "/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }
  
    /*
     * return cookie name.
     */
    public static String getCookieName(Long userId){
     	return COOKIE_FOR_USER_ID+String.valueOf((userId*214)+214);
     }
     
    /**
     * return cookie value.
     */
    public static String getCookieValue(String uuid){
     	return uuid;
     }

    /**
     * will return user id from cookie name.
     * @param cookieName
     * @return
     */
    public static Long getUserIdFromCookieName(String cookieName){
    	 String id = cookieName.replace(COOKIE_FOR_USER_ID, "");
    	 return (Utility.getLongFromString(id)==null)? null : ((Utility.getLongFromString(id)-214)/214);
     }
   
}