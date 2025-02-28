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

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * SearchDto
 *
 * @author shuigedeng
 * @version 2022.03
 * @since 2020/5/3 07:49
 */
public class SearchDto implements Serializable {

	@Serial
	private static final long serialVersionUID = -2084416068307485742L;
	/**
	 * 搜索关键字
	 */
	private String queryStr;
	/**
	 * 当前页数
	 */
	private Integer page;
	/**
	 * 每页显示数
	 */
	private Integer limit;
	/**
	 * 排序字段
	 */
	private String sortCol;
	/**
	 * 是否显示高亮
	 */
	private Boolean isHighlighter;
	/**
	 * es的路由
	 */
	private String routing;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SearchDto searchDto = (SearchDto) o;
		return Objects.equals(queryStr, searchDto.queryStr) && Objects.equals(page,
			searchDto.page) && Objects.equals(limit, searchDto.limit)
			&& Objects.equals(sortCol, searchDto.sortCol) && Objects.equals(
			isHighlighter, searchDto.isHighlighter) && Objects.equals(routing,
			searchDto.routing);
	}

	@Override
	public int hashCode() {
		return Objects.hash(queryStr, page, limit, sortCol, isHighlighter, routing);
	}

	public String getQueryStr() {
		return queryStr;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getSortCol() {
		return sortCol;
	}

	public void setSortCol(String sortCol) {
		this.sortCol = sortCol;
	}

	public Boolean getHighlighter() {
		return isHighlighter;
	}

	public void setHighlighter(Boolean highlighter) {
		isHighlighter = highlighter;
	}

	public String getRouting() {
		return routing;
	}

	public void setRouting(String routing) {
		this.routing = routing;
	}
}
