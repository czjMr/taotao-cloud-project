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
package com.taotao.cloud.web.utils;

import com.taotao.cloud.common.utils.log.LogUtils;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;

/**
 * Spring工具类
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-02 22:27:12
 */
public class SpringUtil implements ApplicationContextAware, DisposableBean {

	/**
	 * applicationContext
	 */
	private static ApplicationContext applicationContext = null;
	/**
	 * CALL_BACKS
	 */
	private static final List<CallBack> CALL_BACKS = new ArrayList<>();
	/**
	 * addCallback
	 */
	private static boolean addCallback = true;

	/**
	 * 针对 某些初始化方法，在SpringContextHolder 未初始化时 提交回调方法。 在SpringContextHolder 初始化后，进行回调使用
	 *
	 * @param callBack callBack
	 * @since 2021-09-02 22:27:35
	 */
	public synchronized static void addCallBacks(CallBack callBack) {
		if (addCallback) {
			SpringUtil.CALL_BACKS.add(callBack);
		} else {
			LogUtils.warn("CallBack：{} 已无法添加！立即执行", callBack.getCallBackName());
			callBack.executor();
		}
	}

	/**
	 * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 *
	 * @param name name
	 * @param <T>  T
	 * @return T
	 * @since 2021-09-02 22:27:49
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		assertContextInjected();
		return (T) applicationContext.getBean(name);
	}

	/**
	 * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 *
	 * @param requiredType requiredType
	 * @param <T>          T
	 * @return T
	 * @since 2021-09-02 22:27:57
	 */
	public static <T> T getBean(Class<T> requiredType) {
		assertContextInjected();
		return applicationContext.getBean(requiredType);
	}

	/**
	 * 获取SpringBoot 配置信息
	 *
	 * @param property     属性key
	 * @param defaultValue 默认值
	 * @param requiredType 返回类型
	 * @param <T>          T
	 * @return T
	 * @since 2021-09-02 22:28:17
	 */
	public static <T> T getProperties(String property, T defaultValue, Class<T> requiredType) {
		T result = defaultValue;
		try {
			result = getBean(Environment.class).getProperty(property, requiredType);
		} catch (Exception ignored) {
		}
		return result;
	}

	/**
	 * 获取SpringBoot 配置信息
	 *
	 * @param property 属性key
	 * @return {@link java.lang.String }
	 * @since 2021-09-02 22:28:29
	 */
	public static String getProperties(String property) {
		return getProperties(property, null, String.class);
	}

	/**
	 * 获取SpringBoot 配置信息
	 *
	 * @param property     属性key
	 * @param requiredType 返回类型
	 * @param <T>          T
	 * @return T
	 * @since 2021-09-02 22:28:40
	 */
	public static <T> T getProperties(String property, Class<T> requiredType) {
		return getProperties(property, null, requiredType);
	}

	/**
	 * 检查ApplicationContext不为空
	 *
	 * @since 2021-09-02 22:28:50
	 */
	private static void assertContextInjected() {
		if (applicationContext == null) {
			throw new IllegalStateException("applicationContext属性未注入, 请在applicationContext" +
					".xml中定义SpringContextHolder或在SpringBoot启动类中注册SpringContextHolder.");
		}
	}

	/**
	 * 清除SpringContextHolder中的ApplicationContext为Null
	 *
	 * @since 2021-09-02 22:28:55
	 */
	private static void clearHolder() {
		LogUtils.debug("清除SpringContextHolder中的ApplicationContext:"
				+ applicationContext);
		applicationContext = null;
	}

	@Override
	public void destroy() {
		SpringUtil.clearHolder();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (SpringUtil.applicationContext != null) {
			LogUtils.warn("SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:"
					+ SpringUtil.applicationContext);
		}
		SpringUtil.applicationContext = applicationContext;
		if (addCallback) {
			for (CallBack callBack : SpringUtil.CALL_BACKS) {
				callBack.executor();
			}
			CALL_BACKS.clear();
		}
		SpringUtil.addCallback = false;
	}

	/**
	 * 获取ApplicationContext
	 *
	 * @return {@link org.springframework.context.ApplicationContext }
	 * @since 2021-09-02 22:29:02
	 */
	public static ApplicationContext getApplicationContext() {
		return SpringUtil.applicationContext;
	}

	/**
	 * 回调方法 针对某些初始化方法，在SpringUtil 初始化前时，<br> 可提交一个 提交回调任务。<br> 在SpringUtil 初始化后，进行回调使用
	 *
	 * @author shuigedeng
	 * @version 2021.9
	 * @since 2021-09-02 22:29:11
	 */
	public interface CallBack {

		/**
		 * 回调执行方法
		 *
		 * @since 2021-09-02 22:29:21
		 */
		void executor();

		/**
		 * 本回调任务名称
		 *
		 * @return {@link java.lang.String }
		 * @since 2021-09-02 22:29:27
		 */
		default String getCallBackName() {
			return Thread.currentThread().getId() + ":" + this.getClass().getName();
		}
	}
}
