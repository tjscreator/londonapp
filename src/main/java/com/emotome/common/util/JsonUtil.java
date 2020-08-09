/* Copyright -2018 @Emotome
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
package com.emotome.common.util;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.emotome.common.enums.ResponseCode;
import com.emotome.common.logger.LoggerService;
import com.emotome.common.view.IdentifierView;
import com.emotome.harbor.exception.HarborException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class is perform json parsing related operations.
 * 
 * @author Nirav.Shah
 * @since 26/09/2018
 */

public class JsonUtil {

	private JsonUtil() {
	}

	private static ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, Boolean.FALSE);
	}

	public static <T> T toObject(final byte[] json, Class aClass) throws HarborException {
		try {
			return (T) mapper.readValue(json, aClass);
		} catch (IOException e) {
			LoggerService.exception(e);
			throw new HarborException(ResponseCode.INVALID_JSON.getCode(), ResponseCode.INVALID_JSON.getMessage());
		}

	}

	public static String toJsonFromMap(Map<String, ? extends Object> map) throws HarborException {
		try {
			return mapper.writeValueAsString(map);
		} catch (IOException e) {
			LoggerService.exception(e);
			throw new HarborException(ResponseCode.INVALID_JSON.getCode(), ResponseCode.INVALID_JSON.getMessage());
		}
	}

	public static String toJson(Object aClass) throws HarborException {
		try {
			return mapper.writeValueAsString(aClass);
		} catch (IOException e) {
			LoggerService.exception(e);
			throw new HarborException(ResponseCode.INVALID_JSON.getCode(), ResponseCode.INVALID_JSON.getMessage());
		}
	}

	public static String getValueOfSpecificKeyFromJsonData(String jsonString, String key) throws HarborException {
		try {
			JsonNode actualObj = mapper.readTree(jsonString);
			if (actualObj != null) {
				JsonNode jsonNode = actualObj.get(key);
				if (jsonNode != null) {
					return jsonNode.asText();
				}
			}
		} catch (IOException e) {
			LoggerService.exception(e);
			throw new HarborException(ResponseCode.INVALID_JSON.getCode(), ResponseCode.INVALID_JSON.getMessage());
		}
		return null;
	}

	public static boolean isValidJSON(final String json) {
		try {
			mapper.readTree(json);
			return true;
		} catch (IOException e) {
			LoggerService.exception(e);
			return false;
		}
	}

	public static <K extends Object, V extends Object> Map<K, V> toSingleMap(String jsonStr) throws HarborException {
		try {
			return mapper.readValue(jsonStr, new TypeReference<HashMap<Object, Object>>() {
			});
		} catch (IOException e) {
			LoggerService.exception(e);
			throw new HarborException(ResponseCode.INVALID_JSON.getCode(), ResponseCode.INVALID_JSON.getMessage());
		}
	}

	public static <K extends Object, V extends Object> Map<K, V> toMap(String jsonStr) throws HarborException {
		try {
			return mapper.readValue(jsonStr, new TypeReference<HashMap<String, Map<String, String>>>() {
			});
		} catch (IOException e) {
			LoggerService.exception(e);
			throw new HarborException(ResponseCode.INVALID_JSON.getCode(), ResponseCode.INVALID_JSON.getMessage());
		}
	}

	public static <T extends IdentifierView> List<T> toList(String jsonStr, Class<T> aClass) throws HarborException {
		try {
			return Arrays.asList(mapper.readValue(jsonStr, aClass));
		} catch (IOException e) {
			LoggerService.exception(e);
			throw new HarborException(ResponseCode.INVALID_JSON.getCode(), ResponseCode.INVALID_JSON.getMessage());
		}
	}

	public static List<Integer> convertJsonStringArrayToList(String strArray) throws HarborException {
		try {
			return mapper.readValue(strArray, List.class);
		} catch (IOException e) {
			LoggerService.exception(e);
			throw new HarborException(ResponseCode.INVALID_JSON.getCode(), ResponseCode.INVALID_JSON.getMessage());
		}
	}

	public static void main(String arg[])
			throws ParseException, HarborException, JsonParseException, JsonMappingException, IOException {

	}
}