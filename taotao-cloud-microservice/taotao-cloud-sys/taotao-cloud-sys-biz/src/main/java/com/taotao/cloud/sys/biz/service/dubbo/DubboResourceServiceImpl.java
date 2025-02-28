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
package com.taotao.cloud.sys.biz.service.dubbo;

import com.taotao.cloud.sys.api.dubbo.IDubboResourceService;
import com.taotao.cloud.sys.api.dubbo.request.DubboMenuQueryRequest;
import com.taotao.cloud.sys.biz.mapper.IResourceMapper;
import com.taotao.cloud.sys.biz.model.convert.ResourceConvert;
import com.taotao.cloud.sys.biz.model.entity.system.QResource;
import com.taotao.cloud.sys.biz.model.entity.system.Resource;
import com.taotao.cloud.sys.biz.repository.cls.ResourceRepository;
import com.taotao.cloud.sys.biz.repository.inf.IResourceRepository;
import com.taotao.cloud.sys.biz.service.business.IRoleService;
import com.taotao.cloud.web.base.service.impl.BaseSuperServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * MenuServiceImpl
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2021-10-09 20:41:23
 */
@Service
@DubboService(interfaceClass = IDubboResourceService.class, validation = "true")
public class DubboResourceServiceImpl extends
	BaseSuperServiceImpl<IResourceMapper, Resource, ResourceRepository, IResourceRepository, Long>
	implements IDubboResourceService {

	@Autowired
	private IRoleService sysRoleService;

	private final static QResource RESOURCE = QResource.resource;

	@Override
	public List<DubboMenuQueryRequest> queryAllById(Long id) {
		List<Resource> all = ir().findAll();
		return ResourceConvert.INSTANCE.convertListRequest(all);
	}


}
