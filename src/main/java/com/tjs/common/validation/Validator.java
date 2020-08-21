/*******************************************************************************
 * Copyright -2018 Emotome
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
package com.tjs.common.validation;

import java.util.HashMap;
import java.util.Map;

import com.tjs.common.enums.EnumType;
import com.tjs.common.enums.ResponseCode;
import com.tjs.harbor.exception.HarborException;

/**
 * This is user validator which validates all fields for user module.
 * 
 * @author Nirav.Shah
 * @since 09/08/2018
 *
 */
public enum Validator implements EnumType {

	CITY_NAME(1, "name") {
		@Override
		public void isValid(InputField inputField) throws HarborException {
			if (!ValidationType.NOT_NULL.isValid(inputField)) {
				throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
						CITY_NAME.getName() + " " + ResponseCode.DATA_IS_MISSING.getMessage());
			} else if (!ValidationType.MAX_LENGTH.isValid(inputField)) {
				throw new HarborException(ResponseCode.MAX_LENGTH_EXCEED.getCode(), "Max " + inputField.getMaxLength()
						+ " " + ResponseCode.MAX_LENGTH_EXCEED.getMessage() + " in " + CITY_NAME.getName());
			}
		}
	},
	STATE_NAME(2, "name") {
		@Override
		public void isValid(InputField inputField) throws HarborException {
			if (!ValidationType.NOT_NULL.isValid(inputField)) {
				throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
						STATE_NAME.getName() + " " + ResponseCode.DATA_IS_MISSING.getMessage());
			} else if (!ValidationType.MAX_LENGTH.isValid(inputField)) {
				throw new HarborException(ResponseCode.MAX_LENGTH_EXCEED.getCode(), "Max " + inputField.getMaxLength()
						+ " " + ResponseCode.MAX_LENGTH_EXCEED.getMessage() + " in " + STATE_NAME.getName());
			}
		}
	},
	SETTING_KEY(3, "Property") {
		@Override
		public void isValid(InputField inputField) throws HarborException {
			if (!ValidationType.NOT_NULL.isValid(inputField)) {
				throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
						SETTING_KEY.getName() + " " + ResponseCode.DATA_IS_MISSING.getMessage());
			} else if (!ValidationType.MAX_LENGTH.isValid(inputField)) {
				throw new HarborException(ResponseCode.MAX_LENGTH_EXCEED.getCode(), "Max " + inputField.getMaxLength()
						+ " " + ResponseCode.MAX_LENGTH_EXCEED.getMessage() + " in " + SETTING_KEY.getName());
			}
		}
	},
	SETTING_VALUE(4, "Value") {
		@Override
		public void isValid(InputField inputField) throws HarborException {
			if (!ValidationType.NOT_NULL.isValid(inputField)) {
				throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
						SETTING_VALUE.getName() + " " + ResponseCode.DATA_IS_MISSING.getMessage());
			} else if (!ValidationType.MAX_LENGTH.isValid(inputField)) {
				throw new HarborException(ResponseCode.MAX_LENGTH_EXCEED.getCode(), "Max " + inputField.getMaxLength()
						+ " " + ResponseCode.MAX_LENGTH_EXCEED.getMessage() + " in " + SETTING_VALUE.getName());
			}
		}
	},
	ROLE_NAME(5, "Name") {
		@Override
		public void isValid(InputField inputField) throws HarborException {
			if (!ValidationType.NOT_NULL.isValid(inputField)) {
				throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
						ROLE_NAME.getName() + " " + ResponseCode.DATA_IS_MISSING.getMessage());
			}
			if (!ValidationType.MAX_LENGTH.isValid(inputField)) {
				throw new HarborException(ResponseCode.MAX_LENGTH_EXCEED.getCode(), "Max " + inputField.getMaxLength()
						+ " " + ResponseCode.MAX_LENGTH_EXCEED.getMessage() + " in " + ROLE_NAME.getName());
			}
			if (!ValidationType.REGEX.isValid(inputField)) {
				throw new HarborException(ResponseCode.DATA_IS_INVALID.getCode(),
						ROLE_NAME.getName() + " " + ResponseCode.DATA_IS_INVALID.getMessage());
			}
		}
	},
	ROLE_DESCRIPTION(6, "Description") {
		@Override
		public void isValid(InputField inputField) throws HarborException {
			if (!ValidationType.MAX_LENGTH.isValid(inputField)) {
				throw new HarborException(ResponseCode.MAX_LENGTH_EXCEED.getCode(), "Max " + inputField.getMaxLength()
						+ " " + ResponseCode.MAX_LENGTH_EXCEED.getMessage() + " in " + ROLE_DESCRIPTION.getName());
			}
		}
	},
	USER_EMAIL_ID(7, "Email Id") {
		@Override
		public void isValid(InputField inputField) throws HarborException {
			if (!ValidationType.NOT_NULL.isValid(inputField)) {
				throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
						USER_EMAIL_ID.getName() + " " + ResponseCode.DATA_IS_MISSING.getMessage());
			}
			if (!ValidationType.MAX_LENGTH.isValid(inputField)) {
				throw new HarborException(ResponseCode.MAX_LENGTH_EXCEED.getCode(), "Max " + inputField.getMaxLength()
						+ " " + ResponseCode.MAX_LENGTH_EXCEED.getMessage() + " in " + USER_EMAIL_ID.getName());
			}
			if (!ValidationType.REGEX.isValid(inputField)) {
				throw new HarborException(ResponseCode.DATA_IS_INVALID.getCode(),
						USER_EMAIL_ID.getName() + " " + ResponseCode.DATA_IS_INVALID.getMessage());
			}
		}
	},
	USER_PASSWORD(8, "Password") {
		@Override
		public void isValid(InputField inputField) throws HarborException {
			if (!ValidationType.NOT_NULL.isValid(inputField)) {
				throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
						USER_PASSWORD.getName() + " " + ResponseCode.DATA_IS_MISSING.getMessage());
			}
			if (!ValidationType.MAX_LENGTH.isValid(inputField)) {
				throw new HarborException(ResponseCode.MAX_LENGTH_EXCEED.getCode(), "Max " + inputField.getMaxLength()
						+ " " + ResponseCode.MAX_LENGTH_EXCEED.getMessage() + " in " + USER_PASSWORD.getName());
			}
		}
	},
	USER_NAME(9, "name") {
		@Override
		public void isValid(InputField inputField) throws HarborException {
			if (!ValidationType.NOT_NULL.isValid(inputField)) {
				throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
						USER_NAME.getName() + " " + ResponseCode.DATA_IS_MISSING.getMessage());
			}
			if (!ValidationType.MAX_LENGTH.isValid(inputField)) {
				throw new HarborException(ResponseCode.MAX_LENGTH_EXCEED.getCode(), "Max " + inputField.getMaxLength()
						+ " " + ResponseCode.MAX_LENGTH_EXCEED.getMessage() + " in " + USER_NAME.getName());
			}
			if (!ValidationType.REGEX.isValid(inputField)) {
				throw new HarborException(ResponseCode.DATA_IS_INVALID.getCode(),
						inputField.getRegex().getMessage() + " in " + USER_NAME.getName());
			}
		}
	},
	COUNTRY_NAME(13, "Country Name") {
		@Override
		public void isValid(InputField inputField) throws HarborException {
			if (!ValidationType.NOT_NULL.isValid(inputField)) {
				throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
						COUNTRY_NAME.getName() + " " + ResponseCode.DATA_IS_MISSING.getMessage());
			} else if (!ValidationType.MAX_LENGTH.isValid(inputField)) {
				throw new HarborException(ResponseCode.MAX_LENGTH_EXCEED.getCode(), "Max " + inputField.getMaxLength()
						+ " " + ResponseCode.MAX_LENGTH_EXCEED.getMessage() + " in " + COUNTRY_NAME.getName());
			}
		}
	},
	COUNTRY_SHORT_NAME(14, "Country Short Name") {
		@Override
		public void isValid(InputField inputField) throws HarborException {
			if (!ValidationType.NOT_NULL.isValid(inputField)) {
				throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
						COUNTRY_SHORT_NAME.getName() + " " + ResponseCode.DATA_IS_MISSING.getMessage());
			} else if (!ValidationType.MAX_LENGTH.isValid(inputField)) {
				throw new HarborException(ResponseCode.MAX_LENGTH_EXCEED.getCode(), "Max " + inputField.getMaxLength()
						+ " " + ResponseCode.MAX_LENGTH_EXCEED.getMessage() + " in " + COUNTRY_SHORT_NAME.getName());
			}
		}
	},
	EMAIL_ACCOUNT_NAME(21, "Email Account Name")

	{
		@Override
		public void isValid(InputField inputField) throws HarborException {
			if (!ValidationType.NOT_NULL.isValid(inputField)) {
				throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
						EMAIL_ACCOUNT_NAME.getName() + " " + ResponseCode.DATA_IS_MISSING.getMessage());
			} else if (!ValidationType.MAX_LENGTH.isValid(inputField)) {
				throw new HarborException(ResponseCode.MAX_LENGTH_EXCEED.getCode(), "Max " + inputField.getMaxLength()
						+ " " + ResponseCode.MAX_LENGTH_EXCEED.getMessage() + " in " + EMAIL_ACCOUNT_NAME.getName());
			}
		}
	},
	EMAIL_FROM(22, "Email From")

	{
		@Override
		public void isValid(InputField inputField) throws HarborException {
			if (!ValidationType.NOT_NULL.isValid(inputField)) {
				throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
						EMAIL_FROM.getName() + " " + ResponseCode.DATA_IS_MISSING.getMessage());
			}
			if (!ValidationType.MAX_LENGTH.isValid(inputField)) {
				throw new HarborException(ResponseCode.MAX_LENGTH_EXCEED.getCode(), "Max " + inputField.getMaxLength()
						+ " " + ResponseCode.MAX_LENGTH_EXCEED.getMessage() + " in " + EMAIL_FROM.getName());
			}
			if (!ValidationType.REGEX.isValid(inputField)) {
				throw new HarborException(ResponseCode.DATA_IS_INVALID.getCode(),
						EMAIL_FROM.getName() + " " + ResponseCode.DATA_IS_INVALID.getMessage());
			}
		}
	},
	REPLY_TO_EMAIL(23, "Reply To Email")

	{
		@Override
		public void isValid(InputField inputField) throws HarborException {
			if (!ValidationType.NOT_NULL.isValid(inputField)) {
				throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
						REPLY_TO_EMAIL.getName() + " " + ResponseCode.DATA_IS_MISSING.getMessage());
			}
			if (!ValidationType.MAX_LENGTH.isValid(inputField)) {
				throw new HarborException(ResponseCode.MAX_LENGTH_EXCEED.getCode(), "Max " + inputField.getMaxLength()
						+ " " + ResponseCode.MAX_LENGTH_EXCEED.getMessage() + " in " + REPLY_TO_EMAIL.getName());
			}
			if (!ValidationType.REGEX.isValid(inputField)) {
				throw new HarborException(ResponseCode.DATA_IS_INVALID.getCode(),
						EMAIL_FROM.getName() + " " + ResponseCode.DATA_IS_INVALID.getMessage());
			}
		}
	},
	EMAIL_CONTENT_NAME(24, "Email Content Name") {

		@Override
		public void isValid(InputField inputField) throws HarborException {
			if (!ValidationType.NOT_NULL.isValid(inputField)) {
				throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
						EMAIL_CONTENT_NAME.getName() + " " + ResponseCode.DATA_IS_MISSING.getMessage());
			} else if (!ValidationType.MAX_LENGTH.isValid(inputField)) {
				throw new HarborException(ResponseCode.MAX_LENGTH_EXCEED.getCode(), "Max " + inputField.getMaxLength()
						+ " " + ResponseCode.MAX_LENGTH_EXCEED.getMessage() + " in " + EMAIL_CONTENT_NAME.getName());
			}
		}

	},
	CONTENT(25, "Content") {

		@Override
		public void isValid(InputField inputField) throws HarborException {
			if (!ValidationType.NOT_NULL.isValid(inputField)) {
				throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
						CONTENT.getName() + " " + ResponseCode.DATA_IS_MISSING.getMessage());
			}
		}

	},
	SUBJECT(26, "Subject") {

		@Override
		public void isValid(InputField inputField) throws HarborException {
			if (!ValidationType.NOT_NULL.isValid(inputField)) {
				throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
						SUBJECT.getName() + " " + ResponseCode.DATA_IS_MISSING.getMessage());
			} else if (!ValidationType.MAX_LENGTH.isValid(inputField)) {
				throw new HarborException(ResponseCode.MAX_LENGTH_EXCEED.getCode(), "Max " + inputField.getMaxLength()
						+ " " + ResponseCode.MAX_LENGTH_EXCEED.getMessage() + " in " + SUBJECT.getName());
			}
		}

	},
	EMAIL_TO(27, "Email To") {

		@Override
		public void isValid(InputField inputField) throws HarborException {
			if (!ValidationType.NOT_NULL.isValid(inputField)) {
				throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
						EMAIL_TO.getName() + " " + ResponseCode.DATA_IS_MISSING.getMessage());
			}

			if (!ValidationType.REGEX.isValid(inputField)) {
				throw new HarborException(ResponseCode.DATA_IS_INVALID.getCode(),
						EMAIL_TO.getName() + " " + ResponseCode.DATA_IS_INVALID.getMessage());
			}
		}

	},
	BODY(28, "Body") {

		@Override
		public void isValid(InputField inputField) throws HarborException {
			if (!ValidationType.NOT_NULL.isValid(inputField)) {
				throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
						BODY.getName() + " " + ResponseCode.DATA_IS_MISSING.getMessage());
			}
		}

	},

	PINCODE(29, "Pincode") {
		@Override
		public void isValid(InputField inputField) throws HarborException {
			if (!ValidationType.NOT_NULL.isValid(inputField)) {
				throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
						PINCODE.getName() + " " + ResponseCode.DATA_IS_MISSING.getMessage());
			}
			if (!ValidationType.REGEX.isValid(inputField)) {
				throw new HarborException(ResponseCode.DATA_IS_INVALID.getCode(),
						PINCODE.getName() + " " + ResponseCode.DATA_IS_INVALID.getMessage());
			} else if (!ValidationType.MAX_LENGTH.isValid(inputField)) {
				throw new HarborException(ResponseCode.MAX_LENGTH_EXCEED.getCode(), "Max " + inputField.getMaxLength()
						+ " " + ResponseCode.MAX_LENGTH_EXCEED.getMessage() + " in " + PINCODE.getName());
			}
		}
	},
	USER_MOBILE(30, "mobile") {
		@Override
		public void isValid(InputField inputField) throws HarborException {
			if (!ValidationType.NOT_NULL.isValid(inputField)) {
				throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
						USER_MOBILE.getName() + " " + ResponseCode.DATA_IS_MISSING.getMessage());
			}
			if (!ValidationType.REGEX.isValid(inputField)) {
				throw new HarborException(ResponseCode.DATA_IS_INVALID.getCode(), inputField.getRegex().getMessage());
			}
		}
	},
	CLIENT_NAME(54, "Client Name") {
		@Override
		public void isValid(InputField inputField) throws HarborException {
			if (!ValidationType.NOT_NULL.isValid(inputField)) {
				throw new HarborException(ResponseCode.DATA_IS_MISSING.getCode(),
						CLIENT_NAME.getName() + " " + ResponseCode.DATA_IS_MISSING.getMessage());
			}
			if (!ValidationType.MAX_LENGTH.isValid(inputField)) {
				throw new HarborException(ResponseCode.MAX_LENGTH_EXCEED.getCode(), "Max " + inputField.getMaxLength()
						+ " " + ResponseCode.MAX_LENGTH_EXCEED.getMessage() + " in " + CLIENT_NAME.getName());
			}
			if (!ValidationType.REGEX.isValid(inputField)) {
				throw new HarborException(ResponseCode.DATA_IS_INVALID.getCode(),
						inputField.getRegex().getMessage() + " in " + CLIENT_NAME.getName());
			}
		}
	};

	private final int id;
	private final String name;
	private static Map<Integer, Validator> MAP = new HashMap<>();

	Validator(int id, String name) {
		this.id = id;
		this.name = name;
	}

	static {
		for (Validator userValidator : values()) {
			MAP.put(userValidator.getId(), userValidator);
		}
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	/**
	 * This methods is used to fetch Enum base on given id.
	 * 
	 * @param code
	 *            enum key
	 * @return CommonStatus enum
	 */
	public static Validator fromId(int id) {
		return MAP.get(id);
	}

	/**
	 * This method is called by enum to check given field value is valid or not.
	 * 
	 * @param inputField
	 * @return
	 * @throws HarborException
	 */
	public abstract void isValid(InputField inputField) throws HarborException;

}
