package com.emotome.common.client.operation;

import java.io.File;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.emotome.common.client.model.ClientModel;
import com.emotome.common.client.service.ClientService;
import com.emotome.common.client.view.ClientView;
import com.emotome.common.enums.ResponseCode;
import com.emotome.common.file.model.FileModel;
import com.emotome.common.file.service.FileService;
import com.emotome.common.file.view.FileView;
import com.emotome.common.location.model.CityModel;
import com.emotome.common.location.model.CountryModel;
import com.emotome.common.location.model.StateModel;
import com.emotome.common.location.service.CityService;
import com.emotome.common.location.service.CountryService;
import com.emotome.common.location.service.StateService;
import com.emotome.common.model.PageModel;
import com.emotome.common.operation.AbstractOperation;
import com.emotome.common.response.CommonResponse;
import com.emotome.common.response.PageResultResponse;
import com.emotome.common.response.Response;
import com.emotome.common.response.ViewResponse;
import com.emotome.common.service.BaseService;
import com.emotome.common.setting.model.SettingModel;
import com.emotome.common.user.enums.GroupEnum;
import com.emotome.common.user.enums.ModuleEnum;
import com.emotome.common.user.model.UserModel;
import com.emotome.common.user.model.UserPasswordModel;
import com.emotome.common.user.service.RoleService;
import com.emotome.common.user.service.UserPasswordService;
import com.emotome.common.user.service.UserService;
import com.emotome.common.user.view.UserView;
import com.emotome.common.util.FileUtility;
import com.emotome.common.util.HashUtil;
import com.emotome.common.util.Utility;
import com.emotome.common.view.IdNameView;
import com.emotome.harbor.exception.HarborException;

/**
 * This class used to perform all business operation on client model.
 * 
 * @author jaydip.golviya
 * @since 06/08/2020
 */
@Component(value = "clientOperation")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class ClientOperationImpl extends AbstractOperation<ClientModel, ClientView> implements ClientOperation {

	@Autowired
	private ClientService clientService;

	@Autowired
	private FileService fileService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserPasswordService userPasswordService;

	@Autowired
	private CityService cityService;

	@Autowired
	private StateService stateService;

	@Autowired
	private CountryService countryService;

	@Override
	public Response doAdd() throws HarborException {
		return null;
	}

	@Override
	public Response doView(Long id) throws HarborException {
		ClientModel clientModel = clientService.getLight(id);
		if (clientModel == null) {
			throw new HarborException(ResponseCode.NO_DATA_FOUND.getCode(), ResponseCode.NO_DATA_FOUND.getMessage());
		}
		return ViewResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(),
				fromModel(clientModel));
	}

	@Override
	public Response doEdit(Long id) throws HarborException {
		return doView(id);
	}

	@Override
	protected ClientModel loadModel(ClientView hospitalView) {
		return clientService.get(hospitalView.getId());
	}

	@Override
	public Response doDelete(Long id) throws HarborException {
		ClientModel clientModel = clientService.get(id);
		if (clientModel == null) {
			throw new HarborException(ResponseCode.NO_DATA_FOUND.getCode(), ResponseCode.NO_DATA_FOUND.getMessage());
		}
		clientModel.setArchive(true);
		clientService.delete(clientModel);
		return CommonResponse.create(ResponseCode.DELETE_SUCCESSFULLY.getCode(),
				ResponseCode.DELETE_SUCCESSFULLY.getMessage());
	}

	@Override
	public Response doActiveInActive(Long id) throws HarborException {
		return null;
	}

	@Override
	public ClientModel toModel(ClientModel clientModel, ClientView clientView) {
		clientModel.setName(clientView.getName());
		clientModel.setMobile(clientView.getMobile());
		if (clientView.getLogoFileView() != null && !StringUtils.isBlank(clientView.getLogoFileView().getFileId())) {
			FileModel fileModel = fileService.getByFileId(clientView.getLogoFileView().getFileId());
			if (fileModel != null) {
				clientModel.setLogoFileModel(fileModel);
			}
		}
		clientModel.setAddress(clientView.getAddress());
		clientModel.setPincode(clientView.getPincode());
		if (clientView.getCityView() != null && clientView.getCityView().getId() != null) {
			CityModel cityModel = cityService.get(clientView.getCityView().getId());
			if (cityModel != null) {
				clientModel.setCityModel(cityModel);
			}
		}
		if (clientView.getStateView() != null && clientView.getStateView().getId() != null) {
			StateModel stateModel = stateService.get(clientView.getStateView().getId());
			if (stateModel != null) {
				clientModel.setStateModel(stateModel);
			}
		}
		if (clientView.getCountryView() != null && clientView.getCountryView().getId() != null) {
			CountryModel countryModel = countryService.get(clientView.getCountryView().getId());
			if (countryModel != null) {
				clientModel.setCountryModel(countryModel);
			}
		}
		return clientModel;
	}

	@Override
	protected ClientModel getNewModel() {
		return new ClientModel();
	}

	@Override
	public ClientView fromModel(ClientModel clientModel) {
		ClientView clientView = new ClientView();
		clientView.setName(clientModel.getName());
		clientView.setMobile(clientModel.getMobile());
		clientView.setActive(clientModel.isActive());
		clientView.setArchive(clientModel.isArchive());
		if (clientModel.getLogoFileModel() != null
				&& !StringUtils.isBlank(clientModel.getLogoFileModel().getFileId())) {
			FileModel fileModel = fileService.getByFileId(clientModel.getLogoFileModel().getFileId());
			if (fileModel != null) {
				clientView.setLogoFileView(
						new FileView(fileModel.getFileId(), fileModel.getName(), fileModel.isPublicfile()));
			}
		}
		clientView.setAddress(clientModel.getAddress());
		clientView.setPincode(clientModel.getPincode());
		if (clientModel.getCityModel() != null && clientModel.getCityModel().getId() != null) {
			CityModel cityModel = cityService.get(clientModel.getCityModel().getId());
			if (cityModel != null) {
				clientView.setCityView(new IdNameView(cityModel.getId(), cityModel.getName()));
			}
		}
		if (clientModel.getStateModel() != null && clientModel.getStateModel().getId() != null) {
			StateModel stateModel = stateService.get(clientModel.getStateModel().getId());
			if (stateModel != null) {
				clientView.setStateView(new IdNameView(stateModel.getId(), stateModel.getName()));
			}
		}
		if (clientModel.getCountryModel() != null && clientModel.getCountryModel().getId() != null) {
			CountryModel countryModel = countryService.get(clientModel.getCountryModel().getId());
			if (countryModel != null) {
				clientView.setCountryView(new IdNameView(countryModel.getId(), countryModel.getName()));
			}
		}
		return clientView;

	}

	private FileView setFileView(FileModel fileModel, FileView fileView) {
		fileView.setFileId(fileModel.getFileId());
		fileView.setName(fileModel.getName());
		return fileView;
	}

	@Override
	public BaseService getService() {
		return clientService;
	}

	@Override
	protected void checkInactive(ClientModel hospitalModel) throws HarborException {

	}

	@Override
	public Response doSave(ClientView clientView) throws HarborException {
		ClientModel clientModel = toModel(getNewModel(), clientView);
		clientModel
				.setApiKey(HashUtil.generateApiKey(clientView.getName().replaceAll("[^A-Za-z0-9]+", "").toLowerCase()));

		clientService.create(clientModel);
		createSpocUser(clientModel, clientView);
		return CommonResponse.create(ResponseCode.REGISTER_SUCCESSFULLY_USERID_PASSWORD_WOULD_BE_MESSEGED.getCode(),
				ResponseCode.REGISTER_SUCCESSFULLY_USERID_PASSWORD_WOULD_BE_MESSEGED.getMessage());
	}

	/**
	 * this method for create user for client.
	 * 
	 * @param clientModel
	 * @param clientView
	 * @return
	 * @throws HarborException
	 */
	private UserModel createSpocUser(ClientModel clientModel, ClientView clientView) throws HarborException {
		UserModel userModel = userService.getByEmail(clientView.getUserView().getEmail());
		if (userModel == null) {
			userModel = userService.getByMobile(clientView.getUserView().getMobile());
			if (userModel == null) {
				userModel = new UserModel();
				userModel.setName(clientView.getUserView().getName());
				userModel.setMobile(clientView.getUserView().getMobile());
				userModel.setEmail(clientView.getUserView().getEmail());
				userModel.setActive(true);
				createThumbNailImage(userModel);
				setPassword(userModel, clientView);
				userService.create(userModel);
				userModel.addClientModel(clientModel);
				userModel.addRoleModel(roleService.getByGroup(GroupEnum.fromId(GroupEnum.CLIENT_ADMIN.getId())));
			}
			userModel.getClientModels().add(clientModel);
			userModel.getRoleModels().add(roleService.getByGroup(GroupEnum.fromId(GroupEnum.CLIENT_ADMIN.getId())));
		} else {
			userModel.getClientModels().add(clientModel);
			userModel.getRoleModels().add(roleService.getByGroup(GroupEnum.fromId(GroupEnum.CLIENT_ADMIN.getId())));
		}
		userModel.setClientAdmin(true);
		userService.update(userModel);
		return userModel;
	}

	/**
	 * this method create thumbnail for client user
	 * 
	 * @param userModel
	 * @throws HarborException
	 */
	private void createThumbNailImage(UserModel userModel) throws HarborException {
		File profilePic = FileUtility.createDefaultThumbNail(UserView.getShortName(userModel.getName()),
				SettingModel.getFilePath() + ModuleEnum.USER.getName());
		FileModel fileModel = new FileModel();
		fileModel.setFileModel(profilePic.getName(), ModuleEnum.USER, true);
		fileService.create(fileModel);
		userModel.setProfilepic(fileModel);
	}

	/**
	 * this method for set password for user.
	 * 
	 * @param userModel
	 * @param clientView
	 * @throws HarborException
	 */
	private void setPassword(UserModel userModel, ClientView clientView) throws HarborException {
		String tempPassword = Utility.generateToken(8);
		userModel.setTempPassword(tempPassword);
		UserPasswordModel userPasswordModel = new UserPasswordModel();
		userPasswordModel.setUserModel(userModel);
		userPasswordModel.setPassword(HashUtil.hash(tempPassword));
		userPasswordModel.setCreate(Instant.now().getEpochSecond());
		userPasswordService.create(userPasswordModel);
	}

	public Response doUpdate(ClientView clientView) throws HarborException {
		ClientModel clientModel = clientService.get(clientView.getId());
		if (clientModel == null) {
			throw new HarborException(ResponseCode.NO_DATA_FOUND.getCode(), ResponseCode.NO_DATA_FOUND.getMessage());
		}
		toModel(clientModel, clientView);
		clientService.update(clientModel);
		return CommonResponse.create(ResponseCode.UPDATE_SUCCESSFULLY.getCode(),
				ResponseCode.UPDATE_SUCCESSFULLY.getMessage());
	}

	@Override
	public Response doSearch(ClientView clientView, Integer start, Integer recordSize) throws HarborException {
		PageModel pageModel = clientService.searchLight(clientView, start, recordSize);
		if (pageModel == null || (pageModel.getList() != null && pageModel.getList().isEmpty())) {
			return PageResultResponse.create(ResponseCode.NO_DATA_FOUND.getCode(),
					ResponseCode.NO_DATA_FOUND.getMessage(), 0, Collections.emptyList());
		}
		return PageResultResponse.create(ResponseCode.SUCCESSFUL.getCode(), ResponseCode.SUCCESSFUL.getMessage(),
				pageModel.getRecords(), fromModelList((List<ClientModel>) pageModel.getList()));
	}

}
