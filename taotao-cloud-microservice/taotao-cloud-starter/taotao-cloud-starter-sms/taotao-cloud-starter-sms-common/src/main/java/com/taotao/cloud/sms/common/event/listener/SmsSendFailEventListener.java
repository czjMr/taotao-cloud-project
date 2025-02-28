/*
 * Copyright (c) 2018-2022 the original author or authors.
 *
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.gnu.org/licenses/lgpl-3.0.html
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.taotao.cloud.sms.common.event.listener;

import com.taotao.cloud.sms.common.event.SmsSendFailEvent;
import org.springframework.context.ApplicationListener;

/**
 * 发送失败事件监听接口
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:48:35
 */
public interface SmsSendFailEventListener extends ApplicationListener<SmsSendFailEvent> {
}
