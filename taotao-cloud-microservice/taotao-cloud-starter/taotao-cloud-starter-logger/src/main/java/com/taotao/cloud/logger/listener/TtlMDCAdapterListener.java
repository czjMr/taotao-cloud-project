/*
 *  Copyright (c) 1999-2019 Seata.io Group.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.taotao.cloud.logger.listener;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.LifeCycle;
import com.yomahub.tlog.context.TLogContext;
import java.util.Objects;
import org.slf4j.TtlMDCAdapter;

/**
 * 系统属性记录器上下文侦听器
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:32:17
 */
public class TtlMDCAdapterListener extends ContextAwareBase implements LoggerContextListener, LifeCycle {

	@Override
	public void start() {
		TLogContext.setHasTLogMDC(true);
		TtlMDCAdapter.getInstance();

		Context context = getContext();
		if (Objects.nonNull(context)) {
			context.putProperty("RPC_PORT", System.getProperty("rpc_port", "10000"));
		}
	}

	@Override
	public void stop() {
	}

	@Override
	public boolean isStarted() {
		return false;
	}

	@Override
	public boolean isResetResistant() {
		return false;
	}

	@Override
	public void onStart(LoggerContext context) {
	}

	@Override
	public void onReset(LoggerContext context) {
	}

	@Override
	public void onStop(LoggerContext context) {
	}

	@Override
	public void onLevelChange(Logger logger, Level level) {
	}
}
