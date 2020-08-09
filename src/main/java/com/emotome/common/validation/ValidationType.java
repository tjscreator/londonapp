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
package com.emotome.common.validation;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.emotome.common.enums.EnumType;



/**
 * This enum is used to do basic validation.
 * @author Nirav.Shah
 * @since 09/08/2018
 *
 */
public enum ValidationType implements EnumType{
	
	
	NOT_NULL(1,"NOT NULL"){
		@Override
		public boolean isValid(InputField inputField) {
			if(StringUtils.isBlank(inputField.getValue())){
				return false;
			}
			if(inputField.getDataType() == DataType.LONG && inputField.getValue() == "null") {
				return false;
			}			
			return true;
		}
	},
	
	MIN_LENGTH(2, "MIN_LENGTH"){
		@Override
		public boolean isValid(InputField inputField) {
			if(NOT_NULL.isValid(inputField) == false){
				return false;
			}
			if(inputField.getDataType() == DataType.STRING && inputField.getValue().length() < inputField.getMinLength()){
				return false;
			}else if(inputField.getDataType() == DataType.INT && Integer.parseInt(inputField.getValue()) < inputField.getMinLength()){
				return false;
			}else if(inputField.getDataType() == DataType.FLOAT && Float.parseFloat(inputField.getValue()) < inputField.getMinLength()){
				return false;
			}
			return true;
		}
	},
	
	MAX_LENGTH(3, "MAX_LENGTH"){
		@Override
		public boolean isValid(InputField inputField) {
			if(inputField.getDataType() == DataType.STRING && inputField.getValue().length() > inputField.getMaxLength()){
				return false;
			}else if(inputField.getDataType() == DataType.INT && Integer.parseInt(inputField.getValue()) > inputField.getMaxLength()){
				return false;
			}else if(inputField.getDataType() == DataType.FLOAT && Float.parseFloat(inputField.getValue()) > inputField.getMaxLength()){
				return false;
			}
			return true;
		}
	},
	
	REGEX(4,"REGEX"){
		@Override
		public boolean isValid(InputField inputField) {
			if(NOT_NULL.isValid(inputField) == false){
				return false;
			}
			Pattern pattern = Pattern.compile(inputField.getRegex().getValue());
			Matcher matcher = pattern.matcher(inputField.getValue());
			return matcher.matches();
		}
	};
	
	private final int id;
    private final String name;
    private static Map<Integer, ValidationType> MAP = new HashMap<>(); 
    
	ValidationType(int id, String name) {
        this.id = id;
        this.name = name;
    }
	
	static{
    	for(ValidationType validationType : values()){
    		MAP.put(validationType.getId(), validationType);
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
	 * @param code enum key
	 * @return CommonStatus enum
	 */
    public static ValidationType fromId(int id) {
    	return MAP.get(id);
    }

    /**
     * This method is called by enum to check given field value is valid or not.
     * @param inputField
     * @return boolean
     */
	public abstract boolean isValid(InputField inputField);
	
}
