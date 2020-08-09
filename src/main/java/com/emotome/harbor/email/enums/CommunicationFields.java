/*******************************************************************************
 * Copyright -2017 @Emotome
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
package com.emotome.harbor.email.enums;

import java.util.HashMap;
import java.util.Map;

import com.emotome.common.modelenums.ModelEnum;

/**
 * This is enum type of all email common fields.
 * 
 * @author Vishwa.Shah
 * @since 10/08/2018
 */
public enum CommunicationFields implements ModelEnum {

	NAME(1, "name"), ACTIVATION_LINK(2, "activationlink"), EMAIL(3, "email"), PASSWORD(4, "password"),
	USERNAME(5, "username"), OTP(6, "otp"), HOSPITAL_NAME(7, "hospitalname"), DOCTOR_NAME(8, "doctorname"),
	SHIFT_NAME(9, "shiftname"), DATE(10, "date"), SLOT_TIME(11, "slottime"), TARIFF_NAME(12, "tariffname"),
	AMOUNT(13, "amount"), INVOICE_NUMBER(14, "invoicenumber"), PATIENT_APP_LINK(15, "patientapplink");

	private final Integer id;
	private final String name;

	public static final Map<Integer, CommunicationFields> MAP = new HashMap<>();

	static {
		for (CommunicationFields communicationFields : values()) {
			MAP.put(communicationFields.getId(), communicationFields);
		}
	}

	CommunicationFields(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	public static CommunicationFields fromId(Integer id) {
		return MAP.get(id);
	}
}