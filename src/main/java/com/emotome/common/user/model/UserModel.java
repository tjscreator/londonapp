package com.emotome.common.user.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.emotome.common.client.model.ClientModel;
import com.emotome.common.file.model.FileModel;
import com.emotome.common.model.ArchiveModel;
import com.emotome.common.model.IdentifierModel;

/**
 * This is model represent to user table.
 * 
 * @author jaydip.golviya
 *
 */
public class UserModel extends ArchiveModel {

	private static final long serialVersionUID = -5764068071467332650L;

	private String email;
	private String name;
	private String countryCode;
	private String mobile;
	private String verifyToken;
	private boolean verifyTokenUsed;
	private String resetPasswordToken;
	private boolean resetPasswordTokenUsed;
	private Long resetPasswordDate;
	private String twofactorToken;
	private boolean twofactorTokenUsed;
	private Long twofactorDate;
	private Integer otp;
	private Long sendOtpDate;
	private boolean otpUsed;
	private boolean clientAdmin;
	private boolean masterAdmin;
	private FileModel profilepic;
	private List<UserAddressModel> userAddressModels;
	private String tempPassword;
	private boolean hasLoggedIn;
	private RoleModel userRequestedRoleModel;
	private ClientModel userRequestedClientModel;
	private Set<RoleModel> roleModels = new HashSet<>();
	private Set<ClientModel> clientModels = new HashSet<>();
	private Set<RoleModuleRightsModel> auditorRoleModuleRightsModel = new HashSet<>();

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getVerifyToken() {
		return verifyToken;
	}

	public void setVerifyToken(String verifyToken) {
		this.verifyToken = verifyToken;
	}

	public boolean isVerifyTokenUsed() {
		return verifyTokenUsed;
	}

	public void setVerifyTokenUsed(boolean verifyTokenUsed) {
		this.verifyTokenUsed = verifyTokenUsed;
	}

	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

	public boolean isResetPasswordTokenUsed() {
		return resetPasswordTokenUsed;
	}

	public void setResetPasswordTokenUsed(boolean resetPasswordTokenUsed) {
		this.resetPasswordTokenUsed = resetPasswordTokenUsed;
	}

	public Long getResetPasswordDate() {
		return resetPasswordDate;
	}

	public void setResetPasswordDate(Long resetPasswordDate) {
		this.resetPasswordDate = resetPasswordDate;
	}

	public String getTwofactorToken() {
		return twofactorToken;
	}

	public Set<RoleModuleRightsModel> getAuditorRoleModuleRightsModel() {
		return auditorRoleModuleRightsModel;
	}

	public void setAuditorRoleModuleRightsModel(Set<RoleModuleRightsModel> auditorRoleModuleRightsModel) {
		this.auditorRoleModuleRightsModel = auditorRoleModuleRightsModel;
	}

	public void setTwofactorToken(String twofactorToken) {
		this.twofactorToken = twofactorToken;
	}

	public boolean isTwofactorTokenUsed() {
		return twofactorTokenUsed;
	}

	public void setTwofactorTokenUsed(boolean twofactorTokenUsed) {
		this.twofactorTokenUsed = twofactorTokenUsed;
	}

	public Long getTwofactorDate() {
		return twofactorDate;
	}

	public void setTwofactorDate(Long twofactorDate) {
		this.twofactorDate = twofactorDate;
	}

	public String getTempPassword() {
		return tempPassword;
	}

	public void setTempPassword(String tempPassword) {
		this.tempPassword = tempPassword;
	}

	public boolean isHasLoggedIn() {
		return hasLoggedIn;
	}

	public void setHasLoggedIn(boolean hasLoggedIn) {
		this.hasLoggedIn = hasLoggedIn;
	}

	public boolean hasAccess(Long roleId, Long moduleId, Long rightsId) {
		RoleModuleRightsModel roleModuleRightsModel = new RoleModuleRightsModel(roleId, moduleId, rightsId);
		return this.getAuditorRoleModuleRightsModel().contains(roleModuleRightsModel);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<RoleModel> getRoleModels() {
		return roleModels;
	}

	public void setRoleModels(Set<RoleModel> roleModels) {
		this.roleModels = roleModels;
	}

	public void addRoleModel(RoleModel roleModel) {
		this.roleModels.add(roleModel);
	}

	public void removeRoleModel(RoleModel roleModel) {
		this.roleModels.remove(roleModel);
	}

	public RoleModel getUserRequestedRoleModel() {
		return userRequestedRoleModel;
	}

	public void setUserRequestedRoleModel(RoleModel userRequestedRoleModel) {
		this.userRequestedRoleModel = userRequestedRoleModel;
	}

	public Integer getOtp() {
		return otp;
	}

	public void setOtp(Integer otp) {
		this.otp = otp;
	}

	public Long getSendOtpDate() {
		return sendOtpDate;
	}

	public void setSendOtpDate(Long sendOtpDate) {
		this.sendOtpDate = sendOtpDate;
	}

	public boolean isOtpUsed() {
		return otpUsed;
	}

	public void setOtpUsed(boolean otpUsed) {
		this.otpUsed = otpUsed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isClientAdmin() {
		return clientAdmin;
	}

	public void setClientAdmin(boolean clientAdmin) {
		this.clientAdmin = clientAdmin;
	}

	public boolean isMasterAdmin() {
		return masterAdmin;
	}

	public void setMasterAdmin(boolean masterAdmin) {
		this.masterAdmin = masterAdmin;
	}

	public FileModel getProfilepic() {
		return profilepic;
	}

	public void setProfilepic(FileModel profilepic) {
		this.profilepic = profilepic;
	}

	public List<UserAddressModel> getUserAddressModels() {
		return userAddressModels;
	}

	public void setUserAddressModels(List<UserAddressModel> userAddressModels) {
		this.userAddressModels = userAddressModels;
	}

	public Set<ClientModel> getClientModels() {
		return clientModels;
	}

	public void setClientModels(Set<ClientModel> clientModels) {
		this.clientModels = clientModels;
	}

	public void addClientModel(ClientModel clientModel) {
		this.clientModels.add(clientModel);
	}

	public void removeClientModel(ClientModel clientModel) {
		this.clientModels.remove(clientModel);
	}

	public ClientModel getUserRequestedClientModel() {
		return userRequestedClientModel;
	}

	public void setUserRequestedClientModel(ClientModel userRequestedClientModel) {
		this.userRequestedClientModel = userRequestedClientModel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdentifierModel other = (IdentifierModel) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}
}
