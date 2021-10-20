/*
 * Copyright 2002-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.taotao.cloud.uc.api.dto.job;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

/**
 * 岗位添加对象
 *
 * @author shuigedeng
 * @version 1.0.0
 * @since 2020/9/30 08:49
 */
@Schema( description = "岗位添加对象")
public record JobSaveDTO(

	/**
	 * 岗位名称
	 */
	@Schema(description = "岗位名称")
	String name,

	/**
	 * 部门id
	 */
	@Schema(description = "部门id")
	Long deptId,

	/**
	 * 备注
	 */
	@Schema(description = "备注")
	String remark,

	/**
	 * 排序值
	 */
	@Schema(description = "排序值")
	Integer sortNum,

	/**
	 * 租户id
	 */
	@Schema(description = "租户id")
	String tenantId) implements Serializable {

	static final long serialVersionUID = -7605952923416404638L;


}
