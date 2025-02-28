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
package com.taotao.cloud.elasticsearch.service;

import com.taotao.cloud.common.model.PageResult;
import com.taotao.cloud.elasticsearch.model.IndexDto;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 索引服务
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:23:58
 */
public interface IIndexService {

	/**
	 * 创建索引
	 *
	 * @param indexDto indexDto
	 * @return boolean
	 * @since 2022-04-27 17:23:58
	 */
	boolean create(IndexDto indexDto) throws IOException;

	/**
	 * 删除索引
	 *
	 * @param indexName 索引名
	 * @return boolean
	 * @since 2022-04-27 17:23:58
	 */
	boolean delete(String indexName) throws IOException;

	/**
	 * 索引列表
	 *
	 * @param queryStr 搜索字符串
	 * @param indices  默认显示的索引名
	 * @return {@link PageResult }<{@link HashMap }<{@link String }, {@link String }>>
	 * @since 2022-04-27 17:23:58
	 */
	PageResult<HashMap<String, String>> list(String queryStr, String indices) throws IOException;


	/**
	 * 显示索引明细
	 *
	 * @param indexName 索引名
	 * @return {@link Map }<{@link String }, {@link Object }>
	 * @since 2022-04-27 17:23:58
	 */
	Map<String, Object> show(String indexName) throws IOException;
}
