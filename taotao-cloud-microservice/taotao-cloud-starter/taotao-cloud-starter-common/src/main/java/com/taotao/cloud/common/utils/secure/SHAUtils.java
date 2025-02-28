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
import java.security.MessageDigest;
import org.apache.commons.lang3.StringUtils;

/**
 * SHAUtil
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-02 17:55:47
 */
public class SHAUtils {

	private SHAUtils() {
	}

	/**
	 * 加密、解密方式：SHA-1、SHA-224、SHA-256、SHA-384、SHA-512
	 */
	private static final String SHA_1 = "SHA-1";
	private static final String SHA_224 = "SHA-224";
	private static final String SHA_256 = "SHA-256";
	private static final String SHA_384 = "SHA-384";
	private static final String SHA_512 = "SHA-512";

	/**
	 * SHA1 加密
	 *
	 * @param text 明文
	 * @return 密文
	 * @since 2021-09-02 17:58:16
	 */
	public static String encrypt(String text) {
		return encrypt(text, SHA_1);
	}

	/**
	 * SHA1 加密
	 *
	 * @param text 明文
	 * @return 密文
	 * @since 2021-09-02 17:58:16
	 */
	public static String encrypt224(String text) {
		return encrypt(text, SHA_224);
	}

	/**
	 * SHA1 加密
	 *
	 * @param text 明文
	 * @return 密文
	 * @since 2021-09-02 17:58:16
	 */
	public static String encrypt256(String text) {
		return encrypt(text, SHA_256);
	}

	/**
	 * SHA1 加密
	 *
	 * @param text 明文
	 * @return 密文
	 * @since 2021-09-02 17:58:16
	 */
	public static String encrypt384(String text) {
		return encrypt(text, SHA_384);
	}

	/**
	 * SHA1 加密
	 *
	 * @param text 明文
	 * @return 密文
	 * @since 2021-09-02 17:58:16
	 */
	public static String encrypt512(String text) {
		return encrypt(text, SHA_512);
	}

	/**
	 * SHA 通用加密算法
	 *
	 * @param text      明文
	 * @param algorithm 加密类型
	 * @return 密文
	 * @since 2021-09-02 17:58:16
	 */
	private static String encrypt(String text, String algorithm) {
		if (StringUtils.isBlank(text)) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.update(text.getBytes(StandardCharsets.UTF_8));
			byte[] bytes = messageDigest.digest();
			return bytes2Str(bytes);
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 字节转字符串
	 *
	 * @param bytes 字节
	 * @return 字符串
	 * @since 2021-09-02 17:58:16
	 */
	private static String bytes2Str(byte[] bytes) {
		StringBuilder stringBuilder = new StringBuilder();
		String str;
		for (int i = 0; i < bytes.length; i++) {
			str = Integer.toHexString(bytes[i] & 0xFF);
			if (str.length() == 1) {
				// 1得到一位的进行补0操作
				stringBuilder.append("0");
			}
			stringBuilder.append(str);
		}
		return stringBuilder.toString();
	}
}
