package com.taotao.cloud.gateway.springcloud.configuration;

import com.taotao.cloud.gateway.springcloud.service.IRuleCacheService;
import com.taotao.cloud.gateway.springcloud.service.impl.RuleCacheServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 规则配置
 */
@Configuration
public class RuleConfiguration {

	@Bean
	public IRuleCacheService ruleCacheService() {
		return new RuleCacheServiceImpl();
	}

}
