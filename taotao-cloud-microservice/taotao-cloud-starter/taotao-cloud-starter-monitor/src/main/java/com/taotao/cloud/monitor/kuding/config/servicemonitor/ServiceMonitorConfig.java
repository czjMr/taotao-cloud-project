package com.taotao.cloud.monitor.kuding.config.servicemonitor;

import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;

import com.taotao.cloud.monitor.kuding.properties.servicemonitor.ServiceMonitorProperties;
import com.taotao.cloud.monitor.kuding.config.annos.ConditionalOnServiceMonitor;
import com.taotao.cloud.monitor.kuding.microservice.components.InMemeryServiceNoticeRepository;
import com.taotao.cloud.monitor.kuding.microservice.interfaces.ServiceNoticeRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;


@Configuration
@ConditionalOnServiceMonitor
@EnableConfigurationProperties({ ServiceMonitorProperties.class })
public class ServiceMonitorConfig {

	private static final String SCHEDULE_BEAN_NAME = "promethuesMicroServiceScheduler";

	@Bean(name = SCHEDULE_BEAN_NAME)
	@ConditionalOnMissingBean(name = SCHEDULE_BEAN_NAME)
	@Qualifier
	public TaskScheduler promethuesMicroServiceScheduler(ServiceMonitorProperties serviceMonitorProperties) {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(serviceMonitorProperties.getMonitorServices().size() + 10);
		scheduler.setThreadNamePrefix("prometheus-ms-");
		scheduler.setErrorHandler(x -> x.printStackTrace());
		scheduler.setRejectedExecutionHandler(new CallerRunsPolicy());
		scheduler.setWaitForTasksToCompleteOnShutdown(true);
		scheduler.setAwaitTerminationSeconds(1);
		return scheduler;
	}


	@Bean
	@ConditionalOnMissingBean
	public ServiceNoticeRepository serviceNoticeRepository() {
		ServiceNoticeRepository serviceNoticeRepository = new InMemeryServiceNoticeRepository();
		return serviceNoticeRepository;
	}
}
