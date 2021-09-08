/*
 * Copyright 2002-2021 the original author or authors.
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
package com.taotao.cloud.sentinel.model;

import static feign.Util.checkNotNull;

import com.alibaba.cloud.sentinel.feign.SentinelContractHolder;
import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.common.utils.LogUtil;
import feign.Feign;
import feign.InvocationHandlerFactory;
import feign.MethodMetadata;
import feign.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.cloud.openfeign.FallbackFactory;

/**
 * SentinelInvocationHandler
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-07 20:46:42
 */
public class SentinelInvocationHandler implements InvocationHandler {

	public static final String EQUALS = "equals";

	public static final String HASH_CODE = "hashCode";

	public static final String TO_STRING = "toString";

	private final Target<?> target;

	private final Map<Method, InvocationHandlerFactory.MethodHandler> dispatch;

	private FallbackFactory fallbackFactory;

	private Map<Method, Method> fallbackMethodMap;

	SentinelInvocationHandler(Target<?> target,
		Map<Method, InvocationHandlerFactory.MethodHandler> dispatch,
		FallbackFactory fallbackFactory) {
		this.target = checkNotNull(target, "target");
		this.dispatch = checkNotNull(dispatch, "dispatch");
		this.fallbackFactory = fallbackFactory;
		this.fallbackMethodMap = toFallbackMethod(dispatch);
	}

	SentinelInvocationHandler(Target<?> target,
		Map<Method, InvocationHandlerFactory.MethodHandler> dispatch) {
		this.target = checkNotNull(target, "target");
		this.dispatch = checkNotNull(dispatch, "dispatch");
	}

	@Override
	public Object invoke(final Object proxy, final Method method, final Object[] args)
		throws Throwable {
		if (EQUALS.equals(method.getName())) {
			try {
				Object otherHandler =
					args.length > 0 && args[0] != null ? Proxy.getInvocationHandler(args[0]) : null;
				return equals(otherHandler);
			} catch (IllegalArgumentException e) {
				return false;
			}
		} else if (HASH_CODE.equals(method.getName())) {
			return hashCode();
		} else if (TO_STRING.equals(method.getName())) {
			return toString();
		}

		Object result;
		InvocationHandlerFactory.MethodHandler methodHandler = this.dispatch.get(method);
		// only handle by HardCodedTarget
		if (target instanceof Target.HardCodedTarget) {
			Target.HardCodedTarget hardCodedTarget = (Target.HardCodedTarget) target;
			MethodMetadata methodMetadata = SentinelContractHolder.METADATA_MAP
				.get(hardCodedTarget.type().getName() + Feign
					.configKey(hardCodedTarget.type(), method));
			// resource default is HttpMethod:protocol://url
			if (methodMetadata == null) {
				result = methodHandler.invoke(args);
			} else {
				String resourceName =
					methodMetadata.template().method().toUpperCase() + ":" + hardCodedTarget.url()
						+ methodMetadata.template().path();
				Entry entry = null;
				try {
					ContextUtil.enter(resourceName);
					entry = SphU.entry(resourceName, EntryType.OUT, 1, args);
					result = methodHandler.invoke(args);
				} catch (Throwable ex) {
					// fallback handle
					if (!BlockException.isBlockException(ex)) {
						Tracer.trace(ex);
					}
					if (fallbackFactory != null) {
						try {
							Object fallbackResult = fallbackMethodMap.get(method)
								.invoke(fallbackFactory.create(ex),
									args);
							return fallbackResult;
						} catch (IllegalAccessException e) {
							// shouldn't happen as method is public due to being an
							// interface
							throw new AssertionError(e);
						} catch (InvocationTargetException e) {
							throw new AssertionError(e.getCause());
						}
					} else {
						// 若是R类型 执行自动降级返回R
						if (Result.class == method.getReturnType()) {
							LogUtil.error("feign 服务间调用异常", ex);
							return Result.fail(ex.getLocalizedMessage());
						} else {
							throw ex;
						}
					}
				} finally {
					if (entry != null) {
						entry.exit(1, args);
					}
					ContextUtil.exit();
				}
			}
		} else {
			// other target type using default strategy
			result = methodHandler.invoke(args);
		}

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof com.alibaba.cloud.sentinel.feign.SentinelInvocationHandler) {
			SentinelInvocationHandler other = (SentinelInvocationHandler) obj;
			return target.equals(other.target);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return target.hashCode();
	}

	@Override
	public String toString() {
		return target.toString();
	}

	static Map<Method, Method> toFallbackMethod(
		Map<Method, InvocationHandlerFactory.MethodHandler> dispatch) {
		Map<Method, Method> result = new LinkedHashMap<>();
		for (Method method : dispatch.keySet()) {
			method.setAccessible(true);
			result.put(method, method);
		}
		return result;
	}

}
