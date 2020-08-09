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
package com.emotome.common.location.operation;

import com.emotome.common.location.view.CountryView;
import com.emotome.common.operation.BaseOperation;
import com.emotome.common.response.Response;
import com.emotome.harbor.exception.HarborException;

/*
 * @author Dhruvang
 * @since 29/01/2018
 */

public interface CountryOperation extends BaseOperation<CountryView> {
	
	/**
	 * This method is used to prepare country drop down.
	 * @return
	 * @throws HarborException
	 */
	Response doDropdown() throws HarborException;
}
