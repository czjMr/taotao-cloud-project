// package com.taotao.cloud.monitor.kuding.config.servicemonitor;
//
// import com.taotao.cloud.monitor.kuding.microservice.components.ConsulHealthCheckHandler;
// import com.taotao.cloud.monitor.kuding.config.annos.ConditionalOnServiceMonitor;
// import com.taotao.cloud.monitor.kuding.microservice.interfaces.HealthCheckHandler;
// import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
// import org.springframework.cloud.consul.discovery.ConditionalOnConsulDiscoveryEnabled;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// import com.ecwid.consul.v1.ConsulClient;
//
// @Configuration
// @ConditionalOnServiceMonitor
// @ConditionalOnConsulDiscoveryEnabled
// public class ConsulHealthCheckHandlerConfig {
//
// 	@Bean
// 	@ConditionalOnMissingBean
// 	public HealthCheckHandler healthCheckHandler(ConsulClient consulClient) {
// 		HealthCheckHandler handler = new ConsulHealthCheckHandler(consulClient);
// 		return handler;
// 	}
// }
