/*******************************************************************************
 * Copyright -2018 @IntentLabs
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
package com.emotome.common.startup;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.emotome.common.enums.ResponseCode;
import com.emotome.common.response.CommonResponse;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This is public request filter which does not validate any thing. Just pass it
 * to controller.
 * 
 * @version 1.0
 * @since 30/10/2018
 * @author nirav
 */
@Component
public class PublicRequestFilter implements Filter {
	private ApplicationContext applicationContext;

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

		if (httpServletRequest.getRequestURI().contains("/public/user")) {
			if (httpServletRequest.getRequestURI().contains("/public/user/login")) {

				String appKey = httpServletRequest.getHeader("app-key");
				if (StringUtils.isBlank(appKey)) {
					CommonResponse commonResponse = CommonResponse.create(ResponseCode.APP_KEY_IS_NOT_PRESENT.getCode(),
							ResponseCode.APP_KEY_IS_NOT_PRESENT.getMessage());
					sendResponse(httpServletRequest, httpServletResponse, commonResponse);
					return;
				}
			}
		}
		filterChain.doFilter(httpServletRequest, httpServletResponse);
		return;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.setApplicationContext(
				WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext()));
	}

	@Override
	public void destroy() {

	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	private void sendResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			CommonResponse commonResponse) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper json = new ObjectMapper();
		httpServletResponse.setContentType("application/json");
		json.writeValue(httpServletResponse.getOutputStream(), commonResponse);
	}
}