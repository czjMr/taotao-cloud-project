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
package com.taotao.cloud.gateway.springcloud.configuration;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.taotao.cloud.gateway.springcloud.properties.ApiProperties;
import com.taotao.cloud.gateway.springcloud.properties.DynamicRouteProperties;
import com.taotao.cloud.gateway.springcloud.properties.FilterProperties;
import com.taotao.cloud.gateway.springcloud.properties.HttpsProperties;
import com.taotao.cloud.gateway.springcloud.properties.SecurityProperties;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.filter.reactive.HiddenHttpMethodFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * 全局配置
 *
 * @author shuigedeng
 * @version 2022.03
 * @since 2020/4/29 22:13
 */
@Configuration
@EnableConfigurationProperties({
	DynamicRouteProperties.class,
	ApiProperties.class,
	FilterProperties.class,
	SecurityProperties.class,
	HttpsProperties.class,
	NacosConfigProperties.class
})
public class WebConfiguration {

	@Bean(name = "userKeyResolver")
	public KeyResolver userKeyResolver() {
		return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
	}

	@Primary
	@Bean(name = "apiKeyResolver")
	public KeyResolver apiKeyResolver() {
		return exchange -> Mono.just(exchange.getRequest().getPath().value());
	}

	@Bean(name = "remoteAddrKeyResolver")
	public KeyResolver remoteAddrKeyResolver() {
		return exchange -> Mono.just(
			Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getAddress()
				.getHostAddress());
	}

	@Bean
	public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
		return new HiddenHttpMethodFilter() {
			@Override
			public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
				return chain.filter(exchange);
			}
		};
	}

	@Bean
	MeterRegistryCustomizer<MeterRegistry> configurer(
		@Value("${spring.application.name}") String applicationName) {
		return (registry) -> registry.config().commonTags("application", applicationName);
	}

}

