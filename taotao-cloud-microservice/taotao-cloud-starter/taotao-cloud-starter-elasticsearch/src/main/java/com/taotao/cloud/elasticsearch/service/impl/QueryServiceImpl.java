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
package com.taotao.cloud.elasticsearch.service.impl;

import cn.hutool.core.util.StrUtil;
import com.taotao.cloud.common.model.PageResult;
import com.taotao.cloud.elasticsearch.model.LogicDelDto;
import com.taotao.cloud.elasticsearch.model.SearchDto;
import com.taotao.cloud.elasticsearch.service.IAggregationService;
import com.taotao.cloud.elasticsearch.service.IQueryService;
import com.taotao.cloud.elasticsearch.service.ISearchService;
import java.io.IOException;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 查询服务实现
 *
 * @author shuigedeng
 * @version 2022.03
 * @since 2020/6/15 11:27
 */
public class QueryServiceImpl implements IQueryService {

	@Autowired(required = false)
	private ISearchService searchService;

	@Resource
	private IAggregationService aggregationService;

	@Override
	public PageResult<String> strQuery(String indexName, SearchDto searchDto) throws IOException {
		return strQuery(indexName, searchDto, null);
	}

	@Override
	public PageResult<String> strQuery(String indexName, SearchDto searchDto,
                                       LogicDelDto logicDelDto) throws IOException {
		setLogicDelQueryStr(searchDto, logicDelDto);
//		return searchService.strQuery(indexName, searchDto);
		return null;
	}

	/**
	 * 拼装逻辑删除的条件
	 *
	 * @param searchDto   搜索dto
	 * @param logicDelDto 逻辑删除dto
	 */
	private void setLogicDelQueryStr(SearchDto searchDto, LogicDelDto logicDelDto) {
		if (logicDelDto != null
			&& StrUtil.isNotEmpty(logicDelDto.getLogicDelField())
			&& StrUtil.isNotEmpty(logicDelDto.getLogicNotDelValue())) {
			String result;
			//搜索条件
			String queryStr = searchDto.getQueryStr();
			//拼凑逻辑删除的条件
			String logicStr =
				logicDelDto.getLogicDelField() + ":" + logicDelDto.getLogicNotDelValue();
			if (StrUtil.isNotEmpty(queryStr)) {
				result = "(" + queryStr + ") AND " + logicStr;
			} else {
				result = logicStr;
			}
			searchDto.setQueryStr(result);
		}
	}

	@Override
	public Map<String, Object> requestStatAgg(String indexName, String routing) throws IOException {
		return aggregationService.requestStatAgg(indexName, routing);
	}
}
