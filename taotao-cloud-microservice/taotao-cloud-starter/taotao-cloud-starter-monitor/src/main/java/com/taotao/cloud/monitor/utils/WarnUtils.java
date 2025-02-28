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
package com.taotao.cloud.monitor.utils;


import com.taotao.cloud.common.utils.context.ContextUtils;
import com.taotao.cloud.common.utils.reflect.ReflectionUtils;

/**
 * WarnUtils
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-02 20:58:30
 */
public class WarnUtils {

	/**
	 * 错误报警
	 */
	public static final String ALARM_ERROR = "ERROR";
	/**
	 * 警告
	 */
	public static final String ALARM_WARN = "WARN";
	/**
	 * 通知
	 */
	public static final String ALARM_INFO = "INFO";

	/**
	 * 即时发送报警通知
	 *
	 * @param alarm_type 报警类型
	 * @param title      报警标题
	 * @param content    报警内容
	 * @author shuigedeng
	 * @since 2021-09-02 20:59:11
	 */
	public static void notifynow(String alarm_type, String title, String content) {
		notify(alarm_type, title, content, true);
	}

	/**
	 * 发送报警
	 *
	 * @param alarm_type 告警类型
	 * @param title      告警标题
	 * @param content    告警内容
	 * @author shuigedeng
	 * @since 2021-09-02 20:59:11
	 */
	public static void notify(String alarm_type, String title, String content) {
		notify(alarm_type, title, content, false);
	}

	/**
	 * 发送报警
	 *
	 * @param alarmType 告警类型
	 * @param title     告警标题
	 * @param content   告警内容
	 * @param isNow     是否即时发送
	 * @author shuigedeng
	 * @since 2021-09-02 20:59:11
	 */
	public static void notify(String alarmType, String title, String content, boolean isNow) {
		Class<?> clazz = ReflectionUtils.classForName("com.taotao.cloud.monitor.warn.WarnProvider");
		Object bean = ContextUtils.getBean(clazz, false);
		if (bean != null) {
			if (isNow) {
				ReflectionUtils.callMethodWithParams(bean, "notifynow",
					new String[]{alarmType, title, content}, String.class, String.class,
					String.class);
			} else {
				ReflectionUtils.callMethodWithParams(bean, "notify",
					new String[]{alarmType, title, content}, String.class, String.class,
					String.class);
			}
		}
	}

}
