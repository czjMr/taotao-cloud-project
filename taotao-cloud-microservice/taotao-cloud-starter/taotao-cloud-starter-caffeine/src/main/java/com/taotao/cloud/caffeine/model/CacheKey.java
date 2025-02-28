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
package com.taotao.cloud.caffeine.model;

import java.time.Duration;
import java.util.Objects;
import org.springframework.lang.NonNull;

/**
 * CacheKey 
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-07 21:15:45
 */
public class CacheKey {

	/**
	 * redis key
	 */
	private String key;

	/**
	 * 超时时间 秒
	 */
	private Duration expire;

	public CacheKey(final @NonNull String key) {
		this.key = key;
	}

	public CacheKey() {
	}

	public CacheKey(@NonNull String key, Duration expire) {
		this.key = key;
		this.expire = expire;
	}

	@Override
	public String toString() {
		return "CacheKey{" +
			"key='" + key + '\'' +
			", expire=" + expire +
			'}';
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CacheKey cacheKey = (CacheKey) o;
		return key.equals(cacheKey.key) && Objects.equals(expire, cacheKey.expire);
	}

	@Override
	public int hashCode() {
		return Objects.hash(key, expire);
	}

	@NonNull
	public String getKey() {
		return key;
	}

	public void setKey(@NonNull String key) {
		this.key = key;
	}

	public Duration getExpire() {
		return expire;
	}

	public void setExpire(Duration expire) {
		this.expire = expire;
	}
}
