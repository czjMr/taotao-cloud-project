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
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.lang3.StringUtils;
import org.apache.shenyu.common.exception.ShenyuException;

/**
 * MD5Util
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-02 17:54:26
 */
public class MD5Utils {

	private MD5Utils() {
	}

	/**
	 * 加密、解密方式
	 */
	private static final String MD5 = "MD5";

	/**
	 * MD5 加密
	 *
	 * @param text 明文
	 * @return 密文
	 * @since 2021-09-02 17:56:32
	 */
	public static String encrypt(String text) {
		if (StringUtils.isEmpty(text)) {
			return null;
		}
		char[] hexs = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f'};
		try {
			byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
			MessageDigest messageDigest = MessageDigest.getInstance(MD5);
			messageDigest.update(bytes);
			byte[] md = messageDigest.digest();
			int j = md.length;
			char[] chars = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				chars[k++] = hexs[byte0 >>> 4 & 0xf];
				chars[k++] = hexs[byte0 & 0xf];
			}
			return String.valueOf(chars);
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
		}
		return null;

		/**
		 * 首位是0会被忽略
		 */
		/**
		 * try {
		 *     MessageDigest messageDigest = MessageDigest.getInstance(MD5);
		 *     byte[] dataBytes = text.getBytes(StandardCharsets.UTF_8);
		 *     messageDigest.update(dataBytes);
		 *     return new BigInteger(1, messageDigest.digest()).toString(16);
		 * } catch (Exception e) {
		 *     log.error(e.getMessage(), e);
		 * }
		 * return null;
		 */
	}

	/**
	 * Md 5 string.
	 *
	 * @param src     the src
	 * @param charset the charset
	 *
	 * @return the string
	 */
	private static String md5(final String src, final String charset) {
		MessageDigest md5;
		StringBuilder hexValue = new StringBuilder(32);
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new ShenyuException("MD5 not supported", e);
		}
		byte[] byteArray = new byte[0];
		try {
			byteArray = src.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			LogUtils.error(e.getMessage(), e);
		}
		byte[] md5Bytes = md5.digest(byteArray);
		for (byte md5Byte : md5Bytes) {
			int val = ((int) md5Byte) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	/**
	 * Md 5 string.
	 *
	 * @param src the src
	 * @return the string
	 */
	public static String md5(final String src) {
		return md5(src, StandardCharsets.UTF_8.name());
	}
}
