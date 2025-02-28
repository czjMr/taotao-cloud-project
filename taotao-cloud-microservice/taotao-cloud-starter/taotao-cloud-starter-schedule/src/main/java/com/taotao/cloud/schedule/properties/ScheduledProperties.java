package com.taotao.cloud.schedule.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * ScheduledProperties
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2022-02-09 16:51:07
 */
@RefreshScope
@ConfigurationProperties(prefix = ScheduledProperties.PREFIX)
public class ScheduledProperties {

	public static final String PREFIX = "taotao.cloud.scheduled";

	//是否开启
	private Boolean enabled = true;

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
}
