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
package com.taotao.cloud.logger.service.impl;

import com.taotao.cloud.common.utils.common.JsonUtils;
import com.taotao.cloud.common.utils.log.LogUtils;
import com.taotao.cloud.logger.model.RequestLogger;
import com.taotao.cloud.logger.service.IRequestLoggerService;

/**
 * 审计日志实现类-logger
 *
 * @author shuigedeng
 * @version 2022.03
 * @since 2020/5/2 11:18
 */
public class LoggerRequestLoggerServiceImpl implements IRequestLoggerService {

	@Override
	public void save(RequestLogger requestLogger) {
		LogUtils.info("本地日志记录成功：{}", JsonUtils.toJSONString(requestLogger));
	}
}
