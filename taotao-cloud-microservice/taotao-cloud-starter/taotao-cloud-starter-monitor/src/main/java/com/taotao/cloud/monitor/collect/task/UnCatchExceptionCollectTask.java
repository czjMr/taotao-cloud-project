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
package com.taotao.cloud.monitor.collect.task;

import com.taotao.cloud.common.utils.exception.ExceptionUtils;
import com.taotao.cloud.common.utils.lang.StringUtils;
import com.taotao.cloud.common.utils.log.LogUtils;
import com.taotao.cloud.monitor.annotation.FieldReport;
import com.taotao.cloud.monitor.collect.AbstractCollectTask;
import com.taotao.cloud.monitor.collect.CollectInfo;
import com.taotao.cloud.monitor.enums.WarnTypeEnum;
import com.taotao.cloud.monitor.properties.CollectTaskProperties;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * UnCatchExceptionCollectTask
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-10 19:17:37
 */
public class UnCatchExceptionCollectTask extends AbstractCollectTask {

	private static final String TASK_NAME = "taotao.cloud.monitor.collect.unCatchException";

	private Throwable lastException = null;
	private final CollectTaskProperties properties;

	public UnCatchExceptionCollectTask(CollectTaskProperties properties) {
		this.properties = properties;

		//注入异常处理
		UncaughtExceptionHandler handler = Thread.getDefaultUncaughtExceptionHandler();
		if (!(handler instanceof DefaultUncaughtExceptionHandler)) {
			Thread.setDefaultUncaughtExceptionHandler(
				new DefaultUncaughtExceptionHandler(this, handler));
		}
	}

	@Override
	public int getTimeSpan() {
		return -1;
	}

	@Override
	public String getDesc() {
		return this.getClass().getName();
	}

	@Override
	public String getName() {
		return TASK_NAME;
	}

	@Override
	public boolean getEnabled() {
		return properties.isUncatchEnabled();
	}

	@Override
	protected CollectInfo getData() {
		return new UnCatchInfo(StringUtils.nullToEmpty(ExceptionUtils.trace2String(lastException)));
	}

	public static class DefaultUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

		private final Thread.UncaughtExceptionHandler lastUncaughtExceptionHandler;
		private final UnCatchExceptionCollectTask unCatchExceptionCheckTask;

		public DefaultUncaughtExceptionHandler(
			UnCatchExceptionCollectTask unCatchExceptionCheckTask,
			Thread.UncaughtExceptionHandler lastUncaughtExceptionHandler) {
			this.unCatchExceptionCheckTask = unCatchExceptionCheckTask;
			this.lastUncaughtExceptionHandler = lastUncaughtExceptionHandler;
		}

		@Override
		public void uncaughtException(Thread t, Throwable e) {
			try {
				if (e != null) {
					unCatchExceptionCheckTask.lastException = e;

					notifyMessage(WarnTypeEnum.ERROR, "未捕获错误", ExceptionUtils.trace2String(e));
					LogUtils.error(e, "未捕获错误");
				}
			} catch (Exception e2) {
				if(LogUtils.isErrorEnabled()){
					LogUtils.error(e);
				}
			}

			if (lastUncaughtExceptionHandler != null) {
				lastUncaughtExceptionHandler.uncaughtException(t, e);
			}
		}
	}

	@Override
	public void close() throws Exception {
		UncaughtExceptionHandler handler = Thread.getDefaultUncaughtExceptionHandler();
		if (handler instanceof DefaultUncaughtExceptionHandler) {
			Thread.setDefaultUncaughtExceptionHandler(
				((DefaultUncaughtExceptionHandler) handler).lastUncaughtExceptionHandler);
		}
	}

	private static class UnCatchInfo implements CollectInfo{

		@FieldReport(name = TASK_NAME + ".trace", desc = "未捕获错误堆栈")
		private String trace = "";

		public UnCatchInfo(String trace) {
			this.trace = trace;
		}
	}
}
