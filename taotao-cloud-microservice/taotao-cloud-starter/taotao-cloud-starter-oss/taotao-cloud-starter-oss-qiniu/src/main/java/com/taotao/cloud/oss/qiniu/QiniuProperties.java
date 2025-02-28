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
package com.taotao.cloud.oss.qiniu;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 七牛云文件服务Properties
 *
 * @author shuigedeng
 * @version 2022.03
 * @since 2020/10/26 09:39
 */
@RefreshScope
@ConfigurationProperties(prefix = QiniuProperties.PREFIX)
public class QiniuProperties {

	public static final String PREFIX = "taotao.cloud.oss.qiniu";

	/**
	 * 七牛绑定的域名
	 */
	private String domain;
	/**
	 * 七牛ACCESS_KEY
	 */
	private String accessKey;
	/**
	 * 七牛SECRET_KEY
	 */
	private String secretKey;
	/**
	 * 七牛存储空间名
	 */
	private String bucketName;

	private String zone;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}
}
