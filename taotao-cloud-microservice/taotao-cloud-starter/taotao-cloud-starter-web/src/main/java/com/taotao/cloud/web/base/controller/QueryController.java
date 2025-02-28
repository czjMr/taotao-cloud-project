/*
 * Copyright (c) 2020-2030, Shuigedeng (981376577@qq.com & https://blog.taotaocloud.top/).
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
package com.taotao.cloud.web.base.controller;

import com.taotao.cloud.common.exception.BusinessException;
import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.common.utils.reflect.ReflectionUtils;
import com.taotao.cloud.data.mybatisplus.query.conditions.query.QueryWrap;
import com.taotao.cloud.logger.annotation.RequestLogger;
import com.taotao.cloud.web.base.entity.SuperEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * QueryController
 *
 * @param <T>        实体
 * @param <I>        id
 * @param <QueryDTO> 查询参数
 * @param <QueryVO>  查询返回参数
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-02 21:11:18
 */
public interface QueryController<T extends SuperEntity<T, I>, I extends Serializable, QueryDTO, QueryVO> extends
	PageController<T, I, QueryDTO, QueryVO> {

	/**
	 * 通用单体查询
	 *
	 * @param id 主键id
	 * @return 单体查询对象
	 * @since 2021-09-02 21:11:48
	 */
	@Operation(summary = "通用单体查询", description = "通用单体查询")
	@GetMapping("/{id:[0-9]*}")
	@RequestLogger("通用单体查询")
	//@PreAuthorize("@pms.hasPermission('get')")
	default Result<QueryVO> get(
		@Parameter(description = "id", required = true) @NotNull(message = "id不能为空")
		@PathVariable(value = "id") I id) {
		T t = service().getById(id);
		if (Objects.isNull(t)) {
			throw new BusinessException("未查询到数据");
		}

		return success(ReflectionUtils.copyPropertiesIfRecord(getQueryVOClass(), t));
	}

	/**
	 * 通用批量查询
	 *
	 * @param queryDTO 通用批量查询参数
	 * @return 批量查询结果
	 * @since 2021-09-02 21:12:04
	 */
	@Operation(summary = "通用批量查询", description = "通用批量查询")
	@PostMapping("/query")
	@RequestLogger("通用批量查询")
	//@PreAuthorize("@pms.hasPermission('query')")
	default Result<List<QueryVO>> query(
		@Parameter(description = "查询对象", required = true)
		@RequestBody @Validated QueryDTO queryDTO) {
		QueryWrap<T> wrapper = handlerWrapper(queryDTO);
		List<T> data = service().list(wrapper);
		List<QueryVO> result = Optional
			.ofNullable(data)
			.orElse(new ArrayList<>())
			.stream().filter(Objects::nonNull)
			.map(t -> ReflectionUtils.copyPropertiesIfRecord(getQueryVOClass(), t))
			.toList();
		return success(result);
	}
}
