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
package com.taotao.cloud.common.utils.secure;

import com.taotao.cloud.common.utils.log.LogUtils;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import org.apache.commons.lang3.StringUtils;

/**
 * DESUtil
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-02 17:52:29
 */
public class DESUtils {

	private DESUtils() {
	}

	/**
	 * 加密解密方式
	 */
	private static final String DES = "DES";

	/**
	 * 秘钥
	 */
	private static final String DEFAULT_KEY = "https://github.com/micyo202";

	/**
	 * DES 使用默认秘钥加密
	 *
	 * @param text 明文
	 * @return 密文
	 * @since 2021-09-02 17:51:28
	 */
	public static String encrypt(String text) {
		return encrypt(text, DEFAULT_KEY);
	}

	/**
	 * DES 使用自定义秘钥加密
	 *
	 * @param text 明文
	 * @param key  秘钥
	 * @return 密文
	 * @since 2021-09-02 17:51:28
	 */
	public static String encrypt(String text, String key) {
		if (StringUtils.isAnyBlank(text, key)) {
			return null;
		}
		try {
			// 生成一个可信任的随机数源
			SecureRandom secureRandom = new SecureRandom();
			// 从原始密钥数据创建DESKeySpec对象
			DESKeySpec keySpec = new DESKeySpec(key.getBytes(StandardCharsets.UTF_8));
			// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
			SecretKey securekey = keyFactory.generateSecret(keySpec);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance(DES);
			// 用密钥初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, secureRandom);
			byte[] bytes = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
			return Base64.getEncoder().encodeToString(bytes);
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
		}
		return null;
	}


	/**
	 * DES 使用默认秘钥解密
	 *
	 * @param ciphertext 密文
	 * @return 明文
	 * @since 2021-09-02 17:51:28
	 */
	public static String decrypt(String ciphertext) {
		return decrypt(ciphertext, DEFAULT_KEY);
	}

	/**
	 * DES 使用自定义秘钥解密
	 *
	 * @param ciphertext 密文
	 * @param key        秘钥
	 * @return 明文
	 * @since 2021-09-02 17:51:28
	 */
	public static String decrypt(String ciphertext, String key) {
		if (StringUtils.isAnyBlank(ciphertext, key)) {
			return null;
		}
		try {
			// 生成一个可信任的随机数源
			SecureRandom secureRandom = new SecureRandom();
			// 从原始密钥数据创建DESKeySpec对象
			DESKeySpec keySpec = new DESKeySpec(key.getBytes(StandardCharsets.UTF_8));
			// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
			SecretKey securekey = keyFactory.generateSecret(keySpec);
			// Cipher对象实际完成解密操作
			Cipher cipher = Cipher.getInstance(DES);
			// 用密钥初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, securekey, secureRandom);
			byte[] bytes = cipher.doFinal(
				Base64.getDecoder().decode(ciphertext.getBytes(StandardCharsets.UTF_8)));
			return new String(bytes, StandardCharsets.UTF_8);
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
		}
		return null;
	}
}
