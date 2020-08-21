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
package com.tjs.common.validation;

/**
 * This class is used to set fields values & the same will be validated against their data types.
 * @author Nirav.Shah
 * @since 09/08/2018
 */
public class InputField {
	private String value;
	private DataType dataType;
	private int minLength;
	private int maxLength;
	private RegexEnum regex;
	private String extraMessage;
	
	public InputField(String value, DataType dataType, int minLength, int maxLength, RegexEnum regex) {
		super();
		this.value = value;
		this.dataType = dataType;
		this.minLength = minLength;
		this.maxLength = maxLength;
		this.regex = regex;
	}
	
	public InputField(String value, DataType dataType, int maxLength, RegexEnum regex) {
		super();
		this.value = value;
		this.dataType = dataType;
		this.maxLength = maxLength;
		this.regex = regex;
	}
	
	public InputField(String value, DataType dataType, int maxLength, RegexEnum regex, String extraMessage) {
		super();
		this.value = value;
		this.dataType = dataType;
		this.maxLength = maxLength;
		this.regex = regex;
		this.extraMessage = extraMessage;
	}
	
	public InputField(String value, DataType dataType, RegexEnum regex) {
		super();
		this.value = value;
		this.dataType = dataType;
		this.regex = regex;
	}
	
	public InputField(String value, DataType dataType, int maxLength) {
		super();
		this.value = value;
		this.dataType = dataType;
		this.maxLength = maxLength;
	}
	
	
	public InputField(String value, DataType dataType) {
		super();
		this.value = value;
		this.dataType = dataType;
	}
	
	public InputField(String value, DataType dataType, RegexEnum regex, String extraMessage) {
		super();
		this.value = value;
		this.dataType = dataType;
		this.regex = regex;
		this.extraMessage = extraMessage;
	}
	
	public String getValue() {
		return value;
	}
	
	public DataType getDataType() {
		return dataType;
	}
	
	public int getMinLength() {
		return minLength;
	}
	
	public int getMaxLength() {
		return maxLength;
	}
	
	public RegexEnum getRegex() {
		return regex;
	}

	public String getExtraMessage() {
		return extraMessage;
	}
}
