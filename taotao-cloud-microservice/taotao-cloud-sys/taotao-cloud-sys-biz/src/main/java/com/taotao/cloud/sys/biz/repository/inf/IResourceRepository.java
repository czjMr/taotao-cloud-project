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
package com.taotao.cloud.sys.biz.repository.inf;

import com.taotao.cloud.common.exception.BusinessException;
import com.taotao.cloud.common.utils.exception.ExceptionUtils;
import com.taotao.cloud.sys.biz.model.entity.system.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CompanyMapper
 *
 * @author shuigedeng
 * @version 2022.03
 * @since 2021/10/13 22:50
 */
public interface IResourceRepository extends JpaRepository<Resource, Long> {

	public List<Resource> searchByComponent(String component);

	default List<Long> selectByComponent(String component){
		List<Resource> resources = searchByComponent(component);
		return Optional.ofNullable(resources)
			.stream()
			.filter(Objects::nonNull)
			.map(e -> e.get(0).getId())
			.collect(Collectors.toList());
	}
}
