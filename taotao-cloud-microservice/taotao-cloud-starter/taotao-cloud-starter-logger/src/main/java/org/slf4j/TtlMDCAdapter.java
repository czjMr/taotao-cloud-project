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
package org.slf4j;

import ch.qos.logback.classic.util.LogbackMDCAdapter;
import com.alibaba.ttl.TransmittableThreadLocal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.slf4j.spi.MDCAdapter;

/**
 * 重构{@link LogbackMDCAdapter}类，搭配TransmittableThreadLocal实现父子线程之间的数据传递
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-02 20:00:47
 */
public class TtlMDCAdapter implements MDCAdapter {

	private final ThreadLocal<Map<String, String>> copyOnInheritThreadLocal = new TransmittableThreadLocal<>();

	private static final int WRITE_OPERATION = 1;
	private static final int MAP_COPY_OPERATION = 2;

	private static final TtlMDCAdapter mtcMDCAdapter;

	/**
	 * keeps track of the last operation performed
	 */
	private final ThreadLocal<Integer> lastOperation = new ThreadLocal<>();

	static {
		mtcMDCAdapter = new TtlMDCAdapter();
		MDC.mdcAdapter = mtcMDCAdapter;
	}

	public static MDCAdapter getInstance() {
		return mtcMDCAdapter;
	}

	private Integer getAndSetLastOperation(int op) {
		Integer lastOp = lastOperation.get();
		lastOperation.set(op);
		return lastOp;
	}

	private static boolean wasLastOpReadOrNull(Integer lastOp) {
		return lastOp == null || lastOp == MAP_COPY_OPERATION;
	}

	private Map<String, String> duplicateAndInsertNewMap(Map<String, String> oldMap) {
		Map<String, String> newMap = Collections.synchronizedMap(new HashMap<>());
		if (oldMap != null) {
			// we don't want the parent thread modifying oldMap while we are
			// iterating over it
			synchronized (oldMap) {
				newMap.putAll(oldMap);
			}
		}

		copyOnInheritThreadLocal.set(newMap);
		return newMap;
	}

	/**
	 * Put a context value (the <code>val</code> parameter) as identified with the
	 * <code>key</code> parameter into the current thread's context map. Note that
	 * contrary to log4j, the <code>val</code> parameter can be null.
	 * <p/>
	 * <p/>
	 * If the current thread does not have a context map it is created as a side effect of this
	 * call.
	 *
	 * @throws IllegalArgumentException in case the "key" parameter is null
	 */
	@Override
	public void put(String key, String val) {
		if (key == null) {
			throw new IllegalArgumentException("key cannot be null");
		}

		Map<String, String> oldMap = copyOnInheritThreadLocal.get();
		Integer lastOp = getAndSetLastOperation(WRITE_OPERATION);

		if (wasLastOpReadOrNull(lastOp) || oldMap == null) {
			Map<String, String> newMap = duplicateAndInsertNewMap(oldMap);
			newMap.put(key, val);
		} else {
			oldMap.put(key, val);
		}
	}

	/**
	 * Remove the the context identified by the <code>key</code> parameter.
	 * <p/>
	 */
	@Override
	public void remove(String key) {
		if (key == null) {
			return;
		}
		Map<String, String> oldMap = copyOnInheritThreadLocal.get();
		if (oldMap == null) {
			return;
		}

		Integer lastOp = getAndSetLastOperation(WRITE_OPERATION);

		if (wasLastOpReadOrNull(lastOp)) {
			Map<String, String> newMap = duplicateAndInsertNewMap(oldMap);
			newMap.remove(key);
		} else {
			oldMap.remove(key);
		}

	}


	/**
	 * Clear all entries in the MDC.
	 */
	@Override
	public void clear() {
		lastOperation.set(WRITE_OPERATION);
		copyOnInheritThreadLocal.remove();
	}

	/**
	 * Get the context identified by the <code>key</code> parameter.
	 * <p/>
	 */
	@Override
	public String get(String key) {
		final Map<String, String> map = copyOnInheritThreadLocal.get();
		if ((map != null) && (key != null)) {
			return map.get(key);
		} else {
			return null;
		}
	}

	/**
	 * Get the current thread's MDC as a map. This method is intended to be used internally.
	 */
	public Map<String, String> getPropertyMap() {
		lastOperation.set(MAP_COPY_OPERATION);
		return copyOnInheritThreadLocal.get();
	}

	/**
	 * Returns the keys in the MDC as a {@link Set}. The returned value can be null.
	 */
	public Set<String> getKeys() {
		Map<String, String> map = getPropertyMap();

		if (map != null) {
			return map.keySet();
		} else {
			return null;
		}
	}

	/**
	 * Return a copy of the current thread's context map. Returned value may be null.
	 */
	@Override
	public Map<String, String> getCopyOfContextMap() {
		Map<String, String> hashMap = copyOnInheritThreadLocal.get();
		if (hashMap == null) {
			return null;
		} else {
			return new HashMap<>(hashMap);
		}
	}

	@Override
	public void setContextMap(Map<String, String> contextMap) {
		lastOperation.set(WRITE_OPERATION);

		Map<String, String> newMap = Collections.synchronizedMap(new HashMap<>());
		newMap.putAll(contextMap);

		// the newMap replaces the old one for serialisation's sake
		copyOnInheritThreadLocal.set(newMap);
	}
}
