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
package com.tjs.common.startup;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.tjs.common.logger.LoggerService;
import com.tjs.common.setting.model.AllowedDomainsModel;
import com.tjs.common.threadlocal.Uuid;
import com.tjs.common.util.Utility;

/**
 * This is every request filter. Any request coming from any hospital will pass
 * through this filter. It checked cross domain values and allow access based on
 * it.
 * 
 * @author nirav
 * @since 30/10/2018
 */
@Component
public class EveryRequestFilter implements Filter {

	private static final String EVERY_REQUEST_FILTER = "EveryRequestFilter";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ImageIO.scanForPlugins();
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
		String originHeader = httpServletRequest.getHeader("Origin");
		if (isCrossDomainReuqest(httpServletRequest, httpServletResponse, originHeader)) {
			return;
		}

		if (!StringUtils.isBlank(originHeader)) {
			giveAccess(httpServletRequest, httpServletResponse, originHeader);
		}

		Uuid.setUuid(Utility.generateUuid());
		filterChain.doFilter(httpServletRequest, httpServletResponse);
		Uuid.removeUuid();
	}

	@Override
	public void destroy() {
		LoggerService.info(EVERY_REQUEST_FILTER, "Destory", "Tomcat is been stopped");
	}

	private void giveAccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			String originHeader) {
		if (!httpServletRequest.getRequestURI().contains("file/download") && AllowedDomainsModel.getValue(originHeader)) {
			httpServletResponse.addHeader("Access-Control-Allow-Origin", originHeader);
			httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
			httpServletResponse.addHeader("Access-Control-Expose-Headers", "Content-Type,app-key,kiosk-key,hospital-id");
		}
	}

	private boolean isCrossDomainReuqest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			String originHeader) {
		if (httpServletRequest.getHeader("Access-Control-Request-Method") != null
				&& "OPTIONS".equals(httpServletRequest.getMethod())) {
			if (AllowedDomainsModel.getValue(originHeader)) {
				httpServletResponse.addHeader("Access-Control-Allow-Origin", originHeader);
				httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
				httpServletResponse.addHeader("Access-Control-Expose-Headers", "Content-Type,app-key,kiosk-key,hospital-id");
			}
			httpServletResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
			httpServletResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,app-key,kiosk-key,hospital-id");
			httpServletResponse.addHeader("Access-Control-Max-Age", "1");
			return true;
		}
		return false;
	}
}