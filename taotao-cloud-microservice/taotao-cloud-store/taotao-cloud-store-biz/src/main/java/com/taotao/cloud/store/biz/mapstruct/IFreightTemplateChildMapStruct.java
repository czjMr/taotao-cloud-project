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
package com.taotao.cloud.store.biz.mapstruct;

import com.taotao.cloud.store.api.web.vo.FreightTemplateChildVO;
import com.taotao.cloud.store.biz.model.entity.FreightTemplateChild;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * DeptMapStruct
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 13:39:18
 */
@Mapper(
	unmappedSourcePolicy = ReportingPolicy.IGNORE,
	unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IFreightTemplateChildMapStruct {

	/**
	 * 实例
	 */
	IFreightTemplateChildMapStruct INSTANCE = Mappers.getMapper(IFreightTemplateChildMapStruct.class);

	List<FreightTemplateChildVO> freightTemplateChildListToFreightTemplateChildVoList(List<FreightTemplateChild> freightTemplateChildList);

	List<FreightTemplateChild> freightTemplateChildVOListTofreightTemplateChildList(List<FreightTemplateChildVO> freightTemplateChildVOList);

}
