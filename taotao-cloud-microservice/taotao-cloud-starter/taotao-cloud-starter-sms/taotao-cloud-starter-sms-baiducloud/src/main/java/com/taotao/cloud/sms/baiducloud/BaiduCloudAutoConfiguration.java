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
package com.taotao.cloud.sms.baiducloud;

import com.taotao.cloud.sms.common.condition.ConditionalOnSmsEnabled;
import com.taotao.cloud.sms.common.configuration.SmsAutoConfiguration;
import com.taotao.cloud.sms.common.loadbalancer.SmsSenderLoadBalancer;
import com.taotao.cloud.sms.common.properties.SmsProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 百度云发送端点自动配置
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:50:25
 */
@AutoConfiguration(after = SmsAutoConfiguration.class)
@ConditionalOnSmsEnabled
@ConditionalOnProperty(prefix = SmsProperties.PREFIX, name = "type", havingValue = "BAIDUCLOUD")
@EnableConfigurationProperties(BaiduCloudProperties.class)
public class BaiduCloudAutoConfiguration {

	/**
	 * 构造百度云发送处理
	 *
	 * @param properties     配置对象
	 * @param loadbalancer   负载均衡器
	 * @param eventPublisher spring应用事件发布器
	 * @return 百度云发送处理
	 */
	@Bean
	@ConditionalOnBean(SmsSenderLoadBalancer.class)
	public BaiduCloudSendHandler baiduCloudSendHandler(BaiduCloudProperties properties,
		SmsSenderLoadBalancer loadbalancer, ApplicationEventPublisher eventPublisher) {
		BaiduCloudSendHandler handler = new BaiduCloudSendHandler(properties, eventPublisher);
		loadbalancer.addTarget(handler, true);
		loadbalancer.setWeight(handler, properties.getWeight());
		return handler;
	}

	public static class BaiduCloudSendHandlerCondition implements Condition {

		@Override
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			Boolean enable = context.getEnvironment()
				.getProperty(BaiduCloudProperties.PREFIX + ".enable", Boolean.class);
			return enable == null || enable;
		}
	}

}
