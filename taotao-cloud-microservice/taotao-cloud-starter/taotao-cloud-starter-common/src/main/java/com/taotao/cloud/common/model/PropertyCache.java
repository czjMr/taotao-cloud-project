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
package com.taotao.cloud.common.model;

import com.taotao.cloud.common.utils.bean.BeanUtils;
import com.taotao.cloud.common.enums.EventEnum;
import com.taotao.cloud.common.utils.common.PropertyUtils;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.boot.CommandLineRunner;

/**
 * 属性缓存层
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-02 20:37:32
 */
public class PropertyCache implements CommandLineRunner {

	private final Pubsub<HashMap<String, Object>> pubsub;

	public PropertyCache(Pubsub<HashMap<String, Object>> pubsub) {
		this.pubsub = pubsub;
	}

	/**
	 * cache
	 */
	private final ConcurrentHashMap<String, Object> cache = new ConcurrentHashMap<>();

	/**
	 * isStart
	 */
	private boolean isStart = false;

	@Override
	public void run(String... args) throws Exception {
		//启动结束后开始缓存
		clear();
		isStart = true;
	}

	/**
	 * 获取属性值
	 *
	 * @param key          key
	 * @param defaultValue defaultValue
	 * @return 属性值
	 * @since 2021-09-02 20:37:54
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(String key, T defaultValue) {
		if (!isStart) {
			String v = PropertyUtils.getProperty(key);
			if (v == null) {
				return defaultValue;
			} else {
				return (T) BeanUtils.convert(v, defaultValue.getClass());
			}
		}

		Object value = cache.get(key);
		if (value == null) {
			String v = PropertyUtils.getProperty(key);
			if (v != null) {
				cache.put(key, v);
			} else {
				cache.put(key, PropertyUtils.NULL);
			}
		}

		value = cache.get(key);
		if (PropertyUtils.NULL.equals(value)) {
			return defaultValue;
		} else {
			return (T) BeanUtils.convert(value, defaultValue.getClass());
		}
	}

	/**
	 * 更新属性
	 *
	 * @param key   键
	 * @param value 值
	 * @since 2021-09-02 20:38:10
	 */
	public void tryUpdateCache(String key, Object value) {
		if (!isStart) {
			return;
		}

		if (cache.containsKey(key)) {
			if (value == null) {
				cache.put(key, PropertyUtils.NULL);
			} else {
				cache.put(key, value);
			}
		}

		pubsub.pub(EventEnum.PropertyCacheUpdateEvent.toString(), new HashMap<>(1) {
			{
				put(key, value);
			}
		});
	}

	/**
	 * 监听更新缓存
	 *
	 * @param name   名称
	 * @param action action动作
	 * @since 2021-09-02 20:38:14
	 */
	public void listenUpdateCache(String name, Callable.Action1<HashMap<String, Object>> action) {
		pubsub.sub(EventEnum.PropertyCacheUpdateEvent, new Pubsub.Sub<>(name, action));
	}

	/**
	 * 清理
	 *
	 * @since 2021-09-02 20:38:20
	 */
	public void clear() {
		cache.clear();
	}


}
