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
package com.taotao.cloud.web.utils;

import com.taotao.cloud.common.utils.context.ContextUtils;
import com.taotao.cloud.common.utils.log.LogUtils;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

/**
 * JasyptUtil
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-02 22:24:54
 */
public class JasyptUtil {

	/**
	 * stringEncryptor
	 */
	private static StringEncryptor stringEncryptor;

	static {
		stringEncryptor = ContextUtils.getBean(StringEncryptor.class, true);

		if (stringEncryptor == null) {
			try {
				String password = ContextUtils.getApplicationContext().getEnvironment()
					.getProperty("jasypt.encryptor.password", "taotao-cloud");
				stringEncryptor = getInstance(password);
			} catch (Exception e) {
				LogUtils.error(e);
			}
		}
	}

	/**
	 * 编码
	 *
	 * @param content 数据
	 * @return 编码结果
	 * @since 2021-09-02 22:25:10
	 */
	public String encrypt(String content) {
		return stringEncryptor.encrypt(content);
	}

	/**
	 * 解码
	 *
	 * @param content 数据
	 * @return 解码结果
	 * @author shuigedeng
	 * @since 2021-09-02 22:25:16
	 */
	public String decrypt(String content) {
		return stringEncryptor.decrypt(content);
	}

	/**
	 * 获取StringEncryptor
	 *
	 * @param password 秘钥不能为空
	 * @return StringEncryptor对象
	 * @since 2021-09-02 22:25:26
	 */
	public static StringEncryptor getInstance(String password) throws Exception {
		if (password == null || "".equals(password.trim())) {
			LogUtils.error("秘钥不能为空！");
			throw new Exception("org.jasypt.encryption.StringEncryptor秘钥不能为空！");
		}

		if (stringEncryptor == null) {
			PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
			SimpleStringPBEConfig config = new SimpleStringPBEConfig();
			config.setPassword(password);
			config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
			config.setKeyObtentionIterations("1000");
			config.setPoolSize("1");
			config.setProviderName("SunJCE");
			config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
			config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
			config.setStringOutputType("base64");
			encryptor.setConfig(config);

			stringEncryptor = encryptor;
		}
		return stringEncryptor;
	}
}
