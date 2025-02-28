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
package com.taotao.cloud.feign.properties;

import com.taotao.cloud.common.utils.common.PropertyUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * RibbonProperties 配置
 *
 * @author shuigedeng
 * @version 2022.03
 * @since 2017/11/17
 */
@RefreshScope
@ConfigurationProperties(prefix = LoadbalancerProperties.PREFIX)
public class LoadbalancerProperties {

	public static final String PREFIX = "taotao.cloud.feign.loadbalancer";

	/**
	 * 是否开启自定义隔离规则
	 */
	private boolean enabled = true;

	/**
	 * 是否开启隔离
	 */
	private boolean isolation = true;

	/**
	 * 默认 taotaoCloudVersion
	 */
	private String version;

	/**
	 * 默认 com.taotao.cloud.feign.loadbalancer.chooser.RoundRuleChooser
	 */
	private String chooser;

	public LoadbalancerProperties() {
		this.version = PropertyUtils.getProperty("taotaoCloudVersion");
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean getIsolation() {
		return isolation;
	}

	public void setIsolation(boolean isolation) {
		this.isolation = isolation;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getChooser() {
		return chooser;
	}

	public void setChooser(String chooser) {
		this.chooser = chooser;
	}
}
