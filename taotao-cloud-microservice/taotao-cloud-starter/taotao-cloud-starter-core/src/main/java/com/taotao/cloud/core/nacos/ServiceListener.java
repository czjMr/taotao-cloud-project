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
package com.taotao.cloud.core.nacos;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.nacos.api.naming.listener.Event;
import com.alibaba.nacos.api.naming.listener.EventListener;
import com.alibaba.nacos.api.naming.listener.NamingEvent;
import com.taotao.cloud.common.constant.StarterName;
import com.taotao.cloud.common.utils.LogUtil;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Configuration;

/**
 * ConfigService
 *
 * @author shuigedeng
 * @version 1.0.0
 * @since 2021/04/06 11:20
 */
@Configuration
public class ServiceListener implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		LogUtil.started(ServiceListener.class, StarterName.CORE_STARTER);
	}

	@Configuration
	public static class NacosServiceListenerHnadler implements InitializingBean {

		@Autowired
		private NacosServiceManager nacosServiceManager;
		@Autowired
		private NacosDiscoveryProperties properties;
		@Autowired
		private DiscoveryClient discoveryClient;

		@Override
		public void afterPropertiesSet() throws Exception {
			nacosServiceManager.getNamingService(new Properties()).subscribe(properties.getService(), properties.getGroup(),
				Arrays.asList(properties.getClusterName()), new EventListener() {
					@Override
					public void onEvent(Event event) {
						if (event instanceof NamingEvent) {
							List instances = ((NamingEvent) event).getInstances();

							LogUtil.info("");

							//Optional instanceOptional = NacosWatch.this.selectCurrentInstance(instances);
							//instanceOptional.ifPresent((currentInstance) -> {
							//	NacosWatch.this.resetIfNeeded(currentInstance);
							//});
						}

					}
				});


			List<String> services = discoveryClient.getServices();
			if (!services.isEmpty()) {
				for (String service : services) {
					nacosServiceManager.getNamingService(new Properties())
						.subscribe(service, this.properties.getGroup(),
							Arrays.asList(this.properties.getClusterName()),
							new EventListener() {
								@Override
								public void onEvent(Event event) {
									if (event instanceof NamingEvent) {
										List instances = ((NamingEvent) event).getInstances();

										LogUtil.info("");

										//Optional instanceOptional = NacosWatch.this.selectCurrentInstance(instances);
										//instanceOptional.ifPresent((currentInstance) -> {
										//	NacosWatch.this.resetIfNeeded(currentInstance);
										//});
									}

								}
							});
				}
			}

		}
	}
}
