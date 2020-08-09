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
package com.emotome.common.user.service;

import com.emotome.common.service.BaseService;
import com.emotome.common.user.model.GroupModel;

/**
 * 
 * @author Nirav.Shah
 * @since  14/02/2018
 */
public interface GroupService extends BaseService<GroupModel> {
	String GROUP_MODEL = "groupModel";
}