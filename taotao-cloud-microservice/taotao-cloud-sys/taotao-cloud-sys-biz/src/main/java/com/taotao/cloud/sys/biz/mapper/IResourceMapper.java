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
package com.taotao.cloud.sys.biz.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.cloud.common.model.PageParam;
import com.taotao.cloud.data.mybatisplus.query.LambdaQueryWrapperX;
import com.taotao.cloud.sys.biz.model.entity.system.Resource;
import com.taotao.cloud.web.base.mapper.BaseSuperMapper;
import java.util.List;
import java.util.Set;
import org.apache.ibatis.annotations.Select;

/**
 * IMenuMapper
 *
 * @author shuigedeng
 * @version 2022.03
 * @since 2021/10/13 22:50
 */
public interface IResourceMapper extends BaseSuperMapper<Resource, Long> {

	@Select("select * from tt_resource where id in #{roleIds}")
	List<Resource> findMenuByRoleIds(Set<Long> roleIds);

	@Select("")
	List<Long> selectIdList(List<Long> pidList);

	/**
	 * 查询资源列表
	 */
	default IPage<Resource> selectResourceList(Resource resource, PageParam pageParam) {
		return this.selectPage(new LambdaQueryWrapperX<Resource>()
			.likeIfPresent(Resource::getName,resource.getName())
			.eqIfPresent(Resource::getParentId,resource.getParentId()),
			pageParam
		);
	}


}
