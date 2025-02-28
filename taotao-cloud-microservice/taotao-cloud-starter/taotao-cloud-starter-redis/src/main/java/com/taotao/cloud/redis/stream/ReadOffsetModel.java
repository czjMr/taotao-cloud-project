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

package com.taotao.cloud.redis.stream;

import org.springframework.data.redis.connection.stream.ReadOffset;

/**
 * stream read offset model
 *
 * @author shuigedeng
 * @version 2022.07
 * @since 2022-07-03 09:34:56
 */
public enum ReadOffsetModel {

	/**
	 * 从开始的地方读
	 */
	START(ReadOffset.from("0-0")),
	/**
	 * 从最近的偏移量读取。
	 */
	LATEST(ReadOffset.latest()),
	/**
	 * 读取所有新到达的元素，这些元素的id大于最后一个消费组的id。
	 */
	LAST_CONSUMED(ReadOffset.lastConsumed());

	/**
	 * readOffset
	 */
	private final ReadOffset readOffset;

	ReadOffsetModel(ReadOffset readOffset) {
		this.readOffset = readOffset;
	}

	public ReadOffset getReadOffset() {
		return readOffset;
	}
}
