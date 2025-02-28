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
package com.taotao.cloud.elasticsearch.model;


/**
 * 索引对象
 *
 * @author shuigedeng
 * @version 2022.03
 * @since 2020/5/3 07:48
 */
public class IndexDto {

	/**
	 * 索引名
	 */
	private String indexName;
	/**
	 * 分片数 number_of_shards
	 */
	private Integer numberOfShards;
	/**
	 * 副本数 number_of_replicas
	 */
	private Integer numberOfReplicas;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * mappings内容
	 */
	private String mappingsSource;


	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public Integer getNumberOfShards() {
		return numberOfShards;
	}

	public void setNumberOfShards(Integer numberOfShards) {
		this.numberOfShards = numberOfShards;
	}

	public Integer getNumberOfReplicas() {
		return numberOfReplicas;
	}

	public void setNumberOfReplicas(Integer numberOfReplicas) {
		this.numberOfReplicas = numberOfReplicas;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMappingsSource() {
		return mappingsSource;
	}

	public void setMappingsSource(String mappingsSource) {
		this.mappingsSource = mappingsSource;
	}
}
