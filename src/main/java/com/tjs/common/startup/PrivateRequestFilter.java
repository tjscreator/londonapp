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
import java.time.Instant;
import java.time.LocalDateTime;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjs.common.client.model.ClientModel;
import com.tjs.common.client.service.ClientService;
import com.tjs.common.enums.ResponseCode;
import com.tjs.common.modelenums.CommonStatusEnum;
import com.tjs.common.response.CommonResponse;
import com.tjs.common.setting.model.SettingModel;
import com.tjs.common.threadlocal.Auditor;
import com.tjs.common.threadlocal.Uuid;
import com.tjs.common.user.enums.AppEnum;
import com.tjs.common.user.model.RoleModel;
import com.tjs.common.user.model.UserSessionModel;
import com.tjs.common.user.service.RoleService;
import com.tjs.common.user.service.UserSessionService;
import com.tjs.common.util.Constant;
import com.tjs.common.util.CookieUtility;
import com.tjs.common.util.DateUtility;

import javafx.util.Pair;

/**
 * This is private request filter. Private request filter authentication users
 * using it's session and browser's informations. It also prepared role module
 * rights map which will be used by authorization annotation to check
 * authorization of user.
 * 
 * @since 30/10/2018
 * @author nirav
 *
 */
@Component
public class PrivateRequestFilter implements Filter {

	private ApplicationContext applicationContext;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.setApplicationContext(
				WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext()));
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
		ClientService clientService = (ClientService) applicationContext.getBean("clientService");

		Pair<String, String> pairs = validateAndGetHeaders(httpServletRequest, httpServletResponse);
		if (pairs == null) {
			CommonResponse commonResponse = CommonResponse.create(ResponseCode.APP_KEY_IS_NOT_PRESENT.getCode(),
					ResponseCode.APP_KEY_IS_NOT_PRESENT.getMessage());
			sendResponse(httpServletRequest, httpServletResponse, commonResponse);
			return;
		}
		String appKey = pairs.getKey();
		String apiKey = pairs.getValue();

		Pair<String, String> deviceAndLoginCookiePair = getSessionAndDeviceCookie(httpServletRequest,
				httpServletResponse);
		if (deviceAndLoginCookiePair == null) {
			return;
		}
		String session = deviceAndLoginCookiePair.getKey();
		String device = deviceAndLoginCookiePair.getValue();

		UserSessionModel userSessionModel = validateSession(httpServletRequest, httpServletResponse, session, device);

		if (httpServletRequest.getRequestURI().contains("/download-image")) {
			Auditor.setAuditor(userSessionModel.getUserModel());
			filterChain.doFilter(httpServletRequest, httpServletResponse);
			Auditor.removeAuditor();
			Uuid.removeUuid();
			return;
		}

		RoleModel userRequestedRoleModel = null;

		if (AppEnum.CLIENT.equals(AppEnum.fromId(Integer.valueOf(appKey))) && StringUtils.isBlank(apiKey)) {
			CommonResponse commonResponse = CommonResponse.create(ResponseCode.UNAUTHORIZED_ACCESS.getCode(),
					ResponseCode.UNAUTHORIZED_ACCESS.getMessage());
			sendResponse(httpServletRequest, httpServletResponse, commonResponse);
			return;
		}

		for (RoleModel roleModel : userSessionModel.getUserModel().getRoleModels()) {
			RoleModel mapRoleModel = RoleModel.getMAP().get(roleModel.getId());
			if (mapRoleModel.getAppModel() != null && mapRoleModel.getAppModel().getId().equals(Long.valueOf(appKey))) {
				userRequestedRoleModel = roleModel;
			}
		}

		if (userRequestedRoleModel == null) {
			CommonResponse commonResponse = CommonResponse.create(ResponseCode.UNAUTHORIZED_ACCESS.getCode(),
					ResponseCode.UNAUTHORIZED_ACCESS.getMessage());
			sendResponse(httpServletRequest, httpServletResponse, commonResponse);
			return;
		}

		ClientModel clientModel = null;
		if (!StringUtils.isBlank(apiKey)) {
			clientModel = clientService.getLight(apiKey);
			if (clientModel == null) {
				CommonResponse commonResponse = CommonResponse.create(ResponseCode.AUTHENTICATION_REQUIRED.getCode(),
						ResponseCode.AUTHENTICATION_REQUIRED.getMessage());
				sendResponse(httpServletRequest, httpServletResponse, commonResponse);
				return;
			}
			userSessionModel.getUserModel().setUserRequestedClientModel(clientModel);
		}

		RoleService roleService = (RoleService) applicationContext.getBean("roleService");
		userSessionModel.getUserModel()
				.setAuditorRoleModuleRightsModel(roleService.getRights(userRequestedRoleModel.getId()));
		userSessionModel.getUserModel().setUserRequestedRoleModel(userRequestedRoleModel);
		Auditor.setAuditor(userSessionModel.getUserModel());
		filterChain.doFilter(httpServletRequest, httpServletResponse);
		Auditor.removeAuditor();
		Uuid.removeUuid();
	}

	private UserSessionModel validateSession(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, String session, String device)
			throws JsonGenerationException, JsonMappingException, IOException {
		UserSessionService userSessionService = (UserSessionService) applicationContext.getBean("userSessionService");
		UserSessionModel userSessionModel = userSessionService.get(session);
		if (userSessionModel == null || userSessionModel.getUserModel() == null
				|| !userSessionModel.getUserModel().isActive() || userSessionModel.getUserModel().isArchive()) {
			CookieUtility.setCookie(httpServletResponse, Constant.AUTH_TOKEN, session, 0,
					httpServletRequest.getContextPath());
			CommonResponse commonResponse = CommonResponse.create(ResponseCode.NO_SESSION.getCode(),
					ResponseCode.NO_SESSION.getMessage());
			sendResponse(httpServletRequest, httpServletResponse, commonResponse);
			return null;
		}

		Long sessionInactiveTimeInMinutes = null;
		Integer twoFactorEnabled = null;
		boolean isSessionExpired = false;
		sessionInactiveTimeInMinutes = Long.valueOf(SettingModel.getSessionInactiveTimeInMinutes());
		isSessionExpired = DateUtility.getLocalDateTime(userSessionModel.getUpdateDate())
				.plusMinutes(sessionInactiveTimeInMinutes).isBefore(LocalDateTime.now());
		twoFactorEnabled = Integer.valueOf(SettingModel.getTwoFactorAuthenticationEnable());
		if (isSessionExpired) {
			CookieUtility.setCookie(httpServletResponse, Constant.AUTH_TOKEN, session, 0,
					httpServletRequest.getContextPath());
			CommonResponse commonResponse = CommonResponse.create(ResponseCode.NO_SESSION.getCode(),
					ResponseCode.NO_SESSION.getMessage());
			sendResponse(httpServletRequest, httpServletResponse, commonResponse);
			return null;
		}

		if (CommonStatusEnum.YES.getId().equals(twoFactorEnabled)) {
			if (!userSessionModel.getDeviceCookie().equals(device)) {
				CommonResponse commonResponse = CommonResponse.create(ResponseCode.VALIDATE_NEW_DEVICE.getCode(),
						ResponseCode.VALIDATE_NEW_DEVICE.getMessage());
				sendResponse(httpServletRequest, httpServletResponse, commonResponse);
				return null;
			}
		}

		if (CommonStatusEnum.YES.getId().equals(Integer.valueOf(twoFactorEnabled))) {
			if (userSessionModel.isTwoFactorSession() && !httpServletRequest.getRequestURI().endsWith("validateOtp")) {
				CommonResponse commonResponse = CommonResponse.create(ResponseCode.VALIDATE_NEW_DEVICE.getCode(),
						ResponseCode.VALIDATE_NEW_DEVICE.getMessage());
				sendResponse(httpServletRequest, httpServletResponse, commonResponse);
				return null;
			}
		}

		if (userSessionModel.isResetPasswordSession()) {
			if (!httpServletRequest.getRequestURI().endsWith("reset-password")) {
				CommonResponse commonResponse = CommonResponse.create(ResponseCode.INVALID_REQUEST.getCode(),
						ResponseCode.INVALID_REQUEST.getMessage());
				sendResponse(httpServletRequest, httpServletResponse, commonResponse);
				return null;
			}
		}

		userSessionModel.setUpdateDate(Instant.now().getEpochSecond());
		userSessionService.update(userSessionModel);
		return userSessionModel;
	}

	private Pair<String, String> getSessionAndDeviceCookie(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws JsonGenerationException, JsonMappingException, IOException {
		Cookie cookies[] = httpServletRequest.getCookies();
		if (cookies == null) {
			CommonResponse commonResponse = CommonResponse.create(ResponseCode.AUTHENTICATION_REQUIRED.getCode(),
					ResponseCode.AUTHENTICATION_REQUIRED.getMessage());
			sendResponse(httpServletRequest, httpServletResponse, commonResponse);
			return null;
		}

		String session = null;
		String device = null;
		for (Cookie cookie : cookies) {
			if (Constant.AUTH_TOKEN.equals(cookie.getName())) {
				session = cookie.getValue();
			}
			if (Constant.DEVICE_TOKEN.equals(cookie.getName())) {
				device = cookie.getValue();
			}
		}

		if (StringUtils.isBlank(session)) {
			CommonResponse commonResponse = CommonResponse.create(ResponseCode.AUTHENTICATION_REQUIRED.getCode(),
					ResponseCode.AUTHENTICATION_REQUIRED.getMessage());
			sendResponse(httpServletRequest, httpServletResponse, commonResponse);
			return null;
		}

		if (Constant.AUTH_TOKEN_REMOVED.contains(session)) {
			CookieUtility.setCookie(httpServletResponse, Constant.AUTH_TOKEN, session, 0,
					httpServletRequest.getContextPath());
			CommonResponse commonResponse = CommonResponse.create(ResponseCode.NO_SESSION.getCode(),
					ResponseCode.NO_SESSION.getMessage());
			sendResponse(httpServletRequest, httpServletResponse, commonResponse);
			return null;
		}
		return new Pair<String, String>(session, device);
	}

	private Pair<String, String> validateAndGetHeaders(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws JsonGenerationException, JsonMappingException, IOException {
		String appKey = httpServletRequest.getHeader("app-key");
		String apiKey = httpServletRequest.getHeader("api-key");

		if (StringUtils.isEmpty(appKey)) {
			CommonResponse commonResponse = CommonResponse.create(ResponseCode.APP_KEY_IS_NOT_PRESENT.getCode(),
					ResponseCode.APP_KEY_IS_NOT_PRESENT.getMessage());
			sendResponse(httpServletRequest, httpServletResponse, commonResponse);
			return null;
		}
		if (AppEnum.fromId(Integer.valueOf(appKey)) == null) {
			CommonResponse commonResponse = CommonResponse.create(ResponseCode.APP_KEY_IS_NOT_PRESENT.getCode(),
					ResponseCode.APP_KEY_IS_NOT_PRESENT.getMessage());
			sendResponse(httpServletRequest, httpServletResponse, commonResponse);
			return null;
		}
		return new Pair<String, String>(appKey, apiKey);
	}

	private void sendResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			CommonResponse commonResponse) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper json = new ObjectMapper();
		httpServletResponse.setContentType("application/json");
		json.writeValue(httpServletResponse.getOutputStream(), commonResponse);
	}

	@Override
	public void destroy() {

	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
}