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
package com.taotao.cloud.sys.biz.config.redis.delegate;

import com.taotao.cloud.common.utils.LogUtil;
import com.taotao.cloud.sys.biz.service.IScheduledJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * QuartzJobTopicMessageDelegate
 *
 * @author shuigedeng
 * @version 1.0.0
 * @since 2022/02/09 20:45
 */
@Component
public class ScheduledJobTopicMessageDelegate {

	@Autowired
	private IScheduledJobService IScheduledJobService;

	public void updateCronScheduled(String message) {
		LogUtil.info(message);
	}

	public void addCronScheduled(String message) {
		LogUtil.info(message);
	}

	public void updateFixedDelayScheduled(String message) {
		LogUtil.info(message);
	}

	public void addFixedDelayScheduled(String message) {
		LogUtil.info(message);
	}

	public void updateFixedRateScheduled(String message) {
		LogUtil.info(message);
	}

	public void addFixedRateScheduled(String message) {
		LogUtil.info(message);
	}

	public void cancelScheduled(String message) {
		LogUtil.info(message);
	}

	public void runOnceScheduled(String message) {
		LogUtil.info(message);
	}

	public void callOffScheduled(String message) {
		LogUtil.info(message);
	}

}
