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
package com.taotao.cloud.sys.api.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * 用户查询对象
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2021-10-09 15:19:37
 */
@Schema(description = "用户查询对象")
public record UserQueryVO(
	/**
	 * id
	 */
	@Schema(description = "id")
	Long id,
	/**
	 * 昵称
	 */
	@Schema(description = "昵称")
	String nickname,
	/**
	 * 真实用户名
	 */
	@Schema(description = "真实用户名")
	String username,
	/**
	 * 手机号
	 */
	@Schema(description = "手机号")
	String phone,
	/**
	 * 性别 1男 2女 0未知
	 */
	@Schema(description = "性别 1男 2女 0未知")
	Integer sex,
	/**
	 * 邮箱
	 */
	@Schema(description = "邮箱")
	String email,
	/**
	 * 部门ID
	 */
	@Schema(description = "部门ID")
	Long deptId,
	/**
	 * 岗位ID
	 */
	@Schema(description = "岗位ID")
	Long jobId,
	/**
	 * 头像
	 */
	@Schema(description = "头像")
	String avatar,
	/**
	 * 是否锁定 1-正常，2-锁定
	 */
	@Schema(description = "是否锁定 1-正常，2-锁定")
	Integer lockFlag,
	/**
	 * 角色列表
	 */
	@Schema(description = "角色列表")
	Set<String> roles,
	/**
	 * 权限列表
	 */
	@Schema(description = "权限列表")
	Set<String> permissions,
	/**
	 * 创建时间
	 */
	@Schema(description = "创建时间")
	LocalDateTime createTime,
	/**
	 * 最后修改时间
	 */
	@Schema(description = "最后修改时间")
	LocalDateTime lastModifiedTime) implements Serializable {

	@Serial
	static final long serialVersionUID = 5126530068827085130L;


}
