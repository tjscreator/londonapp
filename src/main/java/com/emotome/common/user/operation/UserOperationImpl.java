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
package com.emotome.common.user.operation;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.emotome.common.client.model.ClientModel;
import com.emotome.common.client.service.ClientService;
import com.emotome.common.client.view.ClientView;
import com.emotome.common.enums.ResponseCode;
import com.emotome.common.file.service.FileService;
import com.emotome.common.location.model.CityModel;
import com.emotome.common.location.model.CountryModel;
import com.emotome.common.location.model.StateModel;
import com.emotome.common.location.service.CityService;
import com.emotome.common.location.service.CountryService;
import com.emotome.common.location.service.StateService;
import com.emotome.common.modelenums.CommonStatusEnum;
import com.emotome.common.operation.AbstractOperation;
import com.emotome.common.response.CommonResponse;
import com.emotome.common.response.Response;
import com.emotome.common.response.ViewResponse;
import com.emotome.common.service.BaseService;
import com.emotome.common.setting.model.SettingModel;
import com.emotome.common.sms.service.SmsContentService;
import com.emotome.common.sms.service.SmsTransactionService;
import com.emotome.common.threadlocal.Auditor;
import com.emotome.common.user.enums.GroupEnum;
import com.emotome.common.user.model.UserModel;
import com.emotome.common.user.model.UserPasswordModel;
import com.emotome.common.user.model.UserSessionModel;
import com.emotome.common.user.service.RoleService;
import com.emotome.common.user.service.UserPasswordService;
import com.emotome.common.user.service.UserService;
import com.emotome.common.user.service.UserSessionService;
import com.emotome.common.user.view.UserView;
import com.emotome.common.util.Constant;
import com.emotome.common.util.CookieUtility;
import com.emotome.common.util.DateUtility;
import com.emotome.common.util.HashUtil;
import com.emotome.common.util.HttpUtil;
import com.emotome.common.util.Utility;
import com.emotome.common.util.WebUtil;
import com.emotome.harbor.exception.HarborException;

/**
 * This class used to perform all business operation on user model.
 * 
 * @author Jalpa.Gandhi
 * @since 14/12/2018
 */
@Component(value = "userOperation")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class UserOperationImpl extends AbstractOperation<UserModel, UserView> implements UserOperation {

	@Autowired
	private UserService userService;

	@Autowired
	private UserPasswordService userPasswordService;

	@Autowired
	private UserSessionService userSessionService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private FileService fileService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private CityService cityService;

	@Autowired
	private StateService stateService;

	@Autowired
	private CountryService countryService;

	@Autowired
	private SmsContentService smsContentService;

	@Autowired
	private SmsTransactionService smsTransactionService;

	@Override
	public Response doAdd() throws HarborException {
		return null;
	}

	@Override
	public Response doView(Long id) throws HarborException {
		UserModel userModel = userService.getLight(id);
		if (userModel == null) {
			throw new HarborException(ResponseCode.NO_DATA_FOUND.getCode(), ResponseCode.NO_DATA_FOUND.getMessage());
		}
		UserView userView = fromModel(userModel);
		return ViewResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(), userView);
	}

	@Override
	public Response doEdit(Long id) throws HarborException {
		return doView(id);
	}

	@Override
	protected UserModel loadModel(UserView userView) {
		return userService.get(userView.getId());
	}

	@Override
	public Response doDelete(Long id) throws HarborException {
		UserModel userModel = userService.get(id);
		if (userModel == null) {
			throw new HarborException(ResponseCode.NO_DATA_FOUND.getCode(), ResponseCode.NO_DATA_FOUND.getMessage());
		}
		userService.delete(userModel);
		return CommonResponse.create(ResponseCode.DELETE_SUCCESSFULLY.getCode(),
				ResponseCode.DELETE_SUCCESSFULLY.getMessage());
	}

	@Override
	public Response doActiveInActive(Long id) throws HarborException {
		return null;
	}

	@Override
	public UserModel toModel(UserModel userModel, UserView userView) {
		userModel.setName(userView.getName());
		userModel.setEmail(userView.getEmail());
		userModel.setCountryCode(userView.getCountryCode());
		userModel.setMobile(userView.getMobile());
		return userModel;
	}

	private void ValidPlaceDetails(UserModel userModel, UserView userView) throws HarborException {
		CityModel cityModel = cityService.get(userView.getCityView().getId());
		if (cityModel == null) {
			throw new HarborException(ResponseCode.DATA_IS_INVALID.getCode(),
					ResponseCode.DATA_IS_INVALID.getMessage());
		}
		StateModel stateModel = stateService.get(userView.getStateView().getId());
		if (stateModel == null) {
			throw new HarborException(ResponseCode.DATA_IS_INVALID.getCode(),
					ResponseCode.DATA_IS_INVALID.getMessage());
		}
		CountryModel countryModel = countryService.get(userView.getCountryView().getId());
		if (countryModel == null) {
			throw new HarborException(ResponseCode.DATA_IS_INVALID.getCode(),
					ResponseCode.DATA_IS_INVALID.getMessage());
		}
	}

	@Override
	public UserView fromModel(UserModel userModel) {
		UserView userView = new UserView();
		userView.setId(userModel.getId());
		userView.setName(userModel.getName());
		userView.setEmail(userModel.getEmail());
		userView.setCountryCode(userModel.getCountryCode());
		userView.setMobile(userModel.getMobile());
		if (userModel.isClientAdmin()) {
			if (userModel.getClientModels() != null && !userModel.getClientModels().isEmpty()) {
				List<ClientView> clientViews = new ArrayList<ClientView>();
				userModel.getClientModels().stream().forEach(clientModel -> {
					clientViews.add(ClientModel.setClientView(clientModel));
				});
				userView.setClientViews(clientViews);
			}
		}
		return userView;
	}

	@Override
	protected UserModel getNewModel() {
		return new UserModel();
	}

	@Override
	public Response doSave(UserView userView) throws HarborException {
		UserModel userModel = toModel(new UserModel(), userView);
		ValidPlaceDetails(userModel, userView);
		userService.create(userModel);
		return CommonResponse.create(ResponseCode.SAVE_SUCCESSFULLY.getCode(),
				ResponseCode.SAVE_SUCCESSFULLY.getMessage());
	}

	@Override
	public Response doUpdate(UserView userView) throws HarborException {
		UserModel userModel = userService.get(userView.getId());
		if (userModel == null) {
			throw new HarborException(ResponseCode.NO_DATA_FOUND.getCode(), ResponseCode.NO_DATA_FOUND.getMessage());
		}
		toModel(userModel, userView);
		ValidPlaceDetails(userModel, userView);
		userService.update(userModel);
		return CommonResponse.create(ResponseCode.UPDATE_SUCCESSFULLY.getCode(),
				ResponseCode.UPDATE_SUCCESSFULLY.getMessage());
	}

	@Override
	public BaseService<UserModel> getService() {
		return userService;
	}

	@Override
	protected void checkInactive(UserModel model) throws HarborException {
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response doSearch(UserView userView, Integer start, Integer recordSize) throws HarborException {
		return null;
	}

	@Override
	public Response doLogin(UserView userView, HttpServletRequest request, HttpServletResponse response,
			boolean isLoginThroughEmail) throws HarborException {
		UserModel userModel = validateUser(userView, isLoginThroughEmail, true);
		validatePassword(userView, userModel);
		UserSessionModel userSessionModel = setAuthAndDeviceToken(userModel, true, false);
		if (userSessionModel.isTwoFactorSession()) {
			CommonResponse.create(ResponseCode.VALIDATE_NEW_DEVICE.getCode(),
					ResponseCode.VALIDATE_NEW_DEVICE.getMessage());
		}
		if (!userModel.isHasLoggedIn()) {
			userModel.setHasLoggedIn(true);
			userService.update(userModel);
		}
		return ViewResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(),
				fromModel(userModel));
	}

	@Override
	public Response doLogout(String session) throws HarborException {
		UserSessionModel userSessionModel = userSessionService.get(session);
		if (userSessionModel == null) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		if (!userSessionModel.getUserModel().equals(Auditor.getAuditor())) {
			throw new HarborException(ResponseCode.INVALID_REQUEST.getCode(),
					ResponseCode.INVALID_REQUEST.getMessage());
		}
		userSessionModel.setSession(userSessionModel.getSession() + Constant.AUTH_TOKEN_REMOVED);
		userSessionService.update(userSessionModel);
		return CommonResponse.create(ResponseCode.LOGGED_OUT_SUCCESSFUL.getCode(),
				ResponseCode.LOGGED_OUT_SUCCESSFUL.getMessage());
	}

	private void setPassword(UserModel userModel, UserView userView) throws HarborException {
		UserPasswordModel userPasswordModel = new UserPasswordModel();
		userPasswordModel.setUserModel(userModel);
		userPasswordModel.setPassword(HashUtil.hash(userView.getPassword()));
		userPasswordModel.setCreate(Instant.now().getEpochSecond());
		userPasswordService.create(userPasswordModel);
	}

	private void validatePassword(UserView userView, UserModel userModel) throws HarborException {
		UserPasswordModel userPasswordModel = userPasswordService.getCurrent(userModel.getId());
		if (userPasswordModel == null) {
			throw new HarborException(ResponseCode.INVALID_LOGINID_OR_PASSWORD.getCode(),
					ResponseCode.INVALID_LOGINID_OR_PASSWORD.getMessage());
		}

		if (!HashUtil.matchHash(userView.getPassword(), userPasswordModel.getPassword())) {
			throw new HarborException(ResponseCode.INVALID_LOGINID_OR_PASSWORD.getCode(),
					ResponseCode.INVALID_LOGINID_OR_PASSWORD.getMessage());
		}
	}

	private UserModel validateUser(UserView userView, boolean isLoginThroughEmail, boolean isLogin)
			throws HarborException {
		UserModel userModel = null;
		if (isLoginThroughEmail) {
			userModel = userService.getByEmail(userView.getLoginId());
		} else {
			userModel = userService.getByMobile(userView.getLoginId());
		}
		if (userModel == null && isLogin) {
			throw new HarborException(ResponseCode.INVALID_LOGINID_OR_PASSWORD.getCode(),
					ResponseCode.INVALID_LOGINID_OR_PASSWORD.getMessage());
		}
		if (userModel == null && !isLogin) {
			throw new HarborException(ResponseCode.INVALID_EMAIL_OR_MOBILE_NUMBER.getCode(),
					ResponseCode.INVALID_EMAIL_OR_MOBILE_NUMBER.getMessage());
		}
		if (userModel.isArchive()) {
			throw new HarborException(ResponseCode.DELETED_USER.getCode(), ResponseCode.DELETED_USER.getMessage());
		}
		if (!userModel.isActive()) {
			throw new HarborException(ResponseCode.EMAIL_VERIFICATION.getCode(),
					ResponseCode.EMAIL_VERIFICATION.getMessage());
		}
		return userModel;
	}

	private UserSessionModel setAuthAndDeviceToken(UserModel userModel, boolean isFactor, boolean isResetPassword)
			throws HarborException {
		String deviceCookie = null;
		String sessionCookie = null;
		Long sessionInactiveMinutes = Long.valueOf(SettingModel.getSessionInactiveTimeInMinutes());
		Integer twoFactorEnabled = CommonStatusEnum
				.fromId(Integer.valueOf(SettingModel.getTwoFactorAuthenticationEnable())).getId();
		Long maxAllowDevice = Long.valueOf(SettingModel.getMaxAllowedDevice());
		Integer deviceCookieTime = Integer.valueOf(SettingModel.getDeviceCookieTimeInSeconds());

		Cookie cookies[] = WebUtil.getCurrentRequest().getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (Constant.DEVICE_TOKEN.equals(cookie.getName())) {
					deviceCookie = cookie.getValue();
				}
				if (Constant.AUTH_TOKEN.equals(cookie.getName())) {
					sessionCookie = cookie.getValue();
				}
			}
		}

		Long deviceCount = userSessionService.deviceUsed(userModel.getId());

		if ((StringUtils.isBlank(deviceCookie) && StringUtils.isBlank(sessionCookie))
				|| (StringUtils.isBlank(deviceCookie) && !StringUtils.isBlank(sessionCookie))) {
			UserSessionModel userSessionModel = generateNewUserSession(userModel, deviceCount, twoFactorEnabled,
					maxAllowDevice, HashUtil.generateDeviceToken(), isResetPassword);
			CookieUtility.setCookie(WebUtil.getCurrentResponse(), Constant.DEVICE_TOKEN,
					userSessionModel.getDeviceCookie(), deviceCookieTime, WebUtil.getCurrentRequest().getContextPath());
			CookieUtility.setCookie(WebUtil.getCurrentResponse(), Constant.AUTH_TOKEN, userSessionModel.getSession(),
					null, WebUtil.getCurrentRequest().getContextPath());
			return userSessionModel;
		}

		UserSessionModel deviceUserSessionModel = userSessionService.getByDeviceCookie(deviceCookie, userModel.getId());
		boolean isDeviceRegistered = userSessionService.isDeviceRegistered(deviceCookie);
		if (deviceUserSessionModel == null) {
			if (isDeviceRegistered) {
				deviceUserSessionModel = generateNewUserSession(userModel, deviceCount, twoFactorEnabled,
						maxAllowDevice, deviceCookie, isResetPassword);
			} else {
				deviceUserSessionModel = generateNewUserSession(userModel, deviceCount, twoFactorEnabled,
						maxAllowDevice, HashUtil.generateDeviceToken(), isResetPassword);
			}

			CookieUtility.setCookie(WebUtil.getCurrentResponse(), Constant.DEVICE_TOKEN,
					deviceUserSessionModel.getDeviceCookie(), deviceCookieTime,
					WebUtil.getCurrentRequest().getContextPath());
			CookieUtility.setCookie(WebUtil.getCurrentResponse(), Constant.AUTH_TOKEN,
					deviceUserSessionModel.getSession(), null, WebUtil.getCurrentRequest().getContextPath());
			return deviceUserSessionModel;
		}

		UserSessionModel userSessionModel = userSessionService.getByDeviceAndSessionCookie(sessionCookie, deviceCookie,
				userModel.getId());

		if (userSessionModel == null) {
			setUserSessionModel(userModel, deviceUserSessionModel, deviceCookie,
					deviceUserSessionModel.getCookieCreateDate(), isResetPassword);
			userSessionService.update(deviceUserSessionModel);
			CookieUtility.setCookie(WebUtil.getCurrentResponse(), Constant.DEVICE_TOKEN,
					deviceUserSessionModel.getDeviceCookie(), deviceCookieTime,
					WebUtil.getCurrentRequest().getContextPath());
			CookieUtility.setCookie(WebUtil.getCurrentResponse(), Constant.AUTH_TOKEN,
					deviceUserSessionModel.getSession(), null, WebUtil.getCurrentRequest().getContextPath());
			return deviceUserSessionModel;
		} else {
			if (DateUtility.getLocalDateTime(userSessionModel.getUpdateDate())
					.plusMinutes(Long.valueOf(sessionInactiveMinutes))
					.isBefore(LocalDateTime.now(DateUtility.getDefaultTimeZone()))) {
				CookieUtility.setCookie(WebUtil.getCurrentResponse(), Constant.AUTH_TOKEN, sessionCookie, 0,
						WebUtil.getCurrentRequest().getContextPath());
				setUserSessionModel(userModel, userSessionModel, deviceCookie, userSessionModel.getCookieCreateDate(),
						isResetPassword);
				userSessionService.update(userSessionModel);
			} else {
				if (isResetPassword) {
					userSessionModel.setResetPasswordSession(true);
				} else {
					userSessionModel.setResetPasswordSession(false);
				}
				userSessionModel.setTwoFactorSession(false);
				userSessionModel.setUpdateDate(DateUtility.getCurrentEpoch());
				userSessionService.update(userSessionModel);
			}
			CookieUtility.setCookie(WebUtil.getCurrentResponse(), Constant.DEVICE_TOKEN,
					userSessionModel.getDeviceCookie(), deviceCookieTime, WebUtil.getCurrentRequest().getContextPath());
			CookieUtility.setCookie(WebUtil.getCurrentResponse(), Constant.AUTH_TOKEN, userSessionModel.getSession(),
					null, WebUtil.getCurrentRequest().getContextPath());
			return userSessionModel;
		}

	}

	private UserSessionModel generateNewUserSession(UserModel userModel, Long deviceCount, Integer twoFactorEnabled,
			Long maxAllowDevice, String deviceCookie, boolean isResetPassword) throws HarborException {
		UserSessionModel userSessionModel = new UserSessionModel();
		setUserSessionModel(userModel, userSessionModel, deviceCookie, Instant.now().getEpochSecond(), isResetPassword);
		if (deviceCount > 0 && CommonStatusEnum.YES.getId().equals(twoFactorEnabled)) {
			userSessionModel.setTwoFactorSession(true);
		}
		if (maxAllowDevice <= deviceCount) {
			userSessionService.deleteLeastUnused(userModel.getId());
		}
		userSessionService.create(userSessionModel);
		return userSessionModel;
	}

	private void setUserSessionModel(UserModel userModel, UserSessionModel userSessionModel, String deviceCookie,
			Long deviceCookieCreateDate, boolean isResetPassword) throws HarborException {
		userSessionModel.setBrowser(HttpUtil.getUserBrowser());
		userSessionModel.setIp(HttpUtil.getHospitalIpAddress());
		userSessionModel.setOs(HttpUtil.getUserOs());
		userSessionModel.setSession(HashUtil.generateAuthToken());
		userSessionModel.setCreateDate(Instant.now().getEpochSecond());
		userSessionModel.setUpdateDate(Instant.now().getEpochSecond());
		userSessionModel.setUserModel(userModel);
		userSessionModel.setDeviceCookie(deviceCookie);
		userSessionModel.setCookieCreateDate(deviceCookieCreateDate);
		userSessionModel.setTwoFactorSession(false);
		if (isResetPassword) {
			userSessionModel.setResetPasswordSession(true);
		} else {
			userSessionModel.setResetPasswordSession(false);
		}
	}

	private List<UserPasswordModel> validateLastUsedPasswords(UserSessionModel userSessionModel, UserView userView,
			boolean isChangePwd) throws HarborException {
		List<UserPasswordModel> userPasswordModels = userPasswordService
				.getByUser(userSessionModel.getUserModel().getId());
		UserPasswordModel userPasswordModelTemp = userPasswordModels.get(0);
		if (isChangePwd && userPasswordModelTemp != null
				&& !HashUtil.matchHash(userView.getOldPassword(), userPasswordModelTemp.getPassword())) {
			throw new HarborException(ResponseCode.CURRENT_PASSWORD_IS_INVALID.getCode(),
					ResponseCode.CURRENT_PASSWORD_IS_INVALID.getMessage());
		}

		return userPasswordModels;
	}

	@Override
	public Response doDisplayGrid(Integer start, Integer recordSize) {
		return null;
	}

	@Override
	public Response doSendResetLink(UserView userView) throws HarborException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response doResetPassword(UserView userView, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws HarborException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response doChangePassword(UserView userView, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws HarborException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response doResetPasswordVerification(String token, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws HarborException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response doIsLoggedIn() throws HarborException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response doSendResetOtp(UserView userView) throws HarborException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response doVerifyOtp(UserView userView, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws HarborException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response doRegister(UserView userView) throws HarborException {
		UserModel userModel = toModel(new UserModel(), userView);
		userModel.setVerifyToken(Utility.generateUuid());
		userService.create(userModel);
		setPassword(userModel, userView);
		userModel.addRoleModel(roleService.getByGroup(GroupEnum.END_USER));
		userService.update(userModel);
		return CommonResponse.create(ResponseCode.SAVE_SUCCESSFULLY.getCode(),
				ResponseCode.SAVE_SUCCESSFULLY.getMessage());
	}
}
