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

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Base64工具类
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-02 17:52:02
 */
public class Base64Utils extends org.springframework.util.Base64Utils {

	/**
	 * 编码
	 *
	 * @param value 字符串
	 * @return {String}
	 */
	public static String encode(String value) {
		return Base64Utils.encode(value, StandardCharsets.UTF_8);
	}

	/**
	 * 编码
	 *
	 * @param value   字符串
	 * @param charset 字符集
	 * @return {String}
	 */
	public static String encode(String value, Charset charset) {
		byte[] val = value.getBytes(charset);
		return new String(Base64Utils.encode(val), charset);
	}

	/**
	 * 编码URL安全
	 *
	 * @param value 字符串
	 * @return {String}
	 */
	public static String encodeUrlSafe(String value) {
		return Base64Utils.encodeUrlSafe(value, StandardCharsets.UTF_8);
	}

	/**
	 * 编码URL安全
	 *
	 * @param value   字符串
	 * @param charset 字符集
	 * @return {String}
	 */
	public static String encodeUrlSafe(String value, Charset charset) {
		byte[] val = value.getBytes(charset);
		return new String(Base64Utils.encodeUrlSafe(val), charset);
	}

	/**
	 * 解码
	 *
	 * @param value 字符串
	 * @return {String}
	 */
	public static String decode(String value) {
		return Base64Utils.decode(value, StandardCharsets.UTF_8);
	}

	/**
	 * 解码
	 *
	 * @param value   字符串
	 * @param charset 字符集
	 * @return {String}
	 */
	public static String decode(String value, Charset charset) {
		byte[] val = value.getBytes(charset);
		byte[] decodedValue = Base64Utils.decode(val);
		return new String(decodedValue, charset);
	}

	/**
	 * 解码URL安全
	 *
	 * @param value 字符串
	 * @return {String}
	 */
	public static String decodeUrlSafe(String value) {
		return Base64Utils.decodeUrlSafe(value, StandardCharsets.UTF_8);
	}

	/**
	 * 解码URL安全
	 *
	 * @param value   字符串
	 * @param charset 字符集
	 * @return {String}
	 */
	public static String decodeUrlSafe(String value, Charset charset) {
		byte[] val = value.getBytes(charset);
		byte[] decodedValue = Base64Utils.decodeUrlSafe(val);
		return new String(decodedValue, charset);
	}

	private Base64Utils() {
	}

	///**
	// * Base64 编码
	// *
	// * @param text 明文
	// * @return 密文
	// * @author shuigedeng
	// * @since 2021-09-02 17:51:28
	// */
	//public static String encode(String text) {
	//	if (StringUtils.isBlank(text)) {
	//		return null;
	//	}
	//	return Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
	//}
	//
	///**
	// * Base64 解码
	// *
	// * @param ciphertext 密文
	// * @return 明文
	// * @author shuigedeng
	// * @since 2021-09-02 17:51:28
	// */
	//public static String decode(String ciphertext) {
	//	if (StringUtils.isBlank(ciphertext)) {
	//		return null;
	//	}
	//	final byte[] decode = Base64.getDecoder().decode(ciphertext);
	//	return new String(decode, StandardCharsets.UTF_8);
	//}



	private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
	/**
	 * 标准编码表
	 */
	private static final byte[] STANDARD_ENCODE_TABLE = {
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
		'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
		'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
		'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
		'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
		'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
		'w', 'x', 'y', 'z', '0', '1', '2', '3',
		'4', '5', '6', '7', '8', '9', '+', '/'
	};
	/**
	 * URL安全的编码表，将 + 和 / 替换为 - 和 _
	 */
	private static final byte[] URL_SAFE_ENCODE_TABLE = {
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
		'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
		'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
		'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
		'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
		'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
		'w', 'x', 'y', 'z', '0', '1', '2', '3',
		'4', '5', '6', '7', '8', '9', '-', '_'
	};

	//-------------------------------------------------------------------- encode

	/**
	 * 编码为Base64，非URL安全的
	 *
	 * @param arr     被编码的数组
	 * @param lineSep 在76个char之后是CRLF还是EOF
	 * @return 编码后的bytes
	 */
	public static byte[] encode(byte[] arr, boolean lineSep) {
		return encode(arr, lineSep, false);
	}

	/**
	 * 编码为Base64，URL安全的
	 *
	 * @param arr     被编码的数组
	 * @param lineSep 在76个char之后是CRLF还是EOF
	 * @return 编码后的bytes
	 */
	public static byte[] encodeUrlSafe(byte[] arr, boolean lineSep) {
		return encode(arr, lineSep, true);
	}

	// /**
	//  * base64编码
	//  *
	//  * @param source 被编码的base64字符串
	//  * @return 被加密后的字符串
	//  */
	// public static String encode(CharSequence source) {
	// 	return encode(source, DEFAULT_CHARSET);
	// }

	// /**
	//  * base64编码，URL安全
	//  *
	//  * @param source 被编码的base64字符串
	//  * @return 被加密后的字符串
	//  */
	// public static String encodeUrlSafe(CharSequence source) {
	// 	return encodeUrlSafe(source, DEFAULT_CHARSET);
	// }
	//
	// /**
	//  * base64编码
	//  *
	//  * @param source  被编码的base64字符串
	//  * @param charset 字符集
	//  * @return 被加密后的字符串
	//  */
	// public static String encode(CharSequence source, Charset charset) {
	// 	return encode(bytes(source, charset));
	// }

	// /**
	//  * base64编码，URL安全的
	//  *
	//  * @param source  被编码的base64字符串
	//  * @param charset 字符集
	//  * @return 被加密后的字符串
	//  */
	// public static String encodeUrlSafe(CharSequence source, Charset charset) {
	// 	return encodeUrlSafe(bytes(source, charset));
	// }

	// /**
	//  * base64编码
	//  *
	//  * @param source 被编码的base64字符串
	//  * @return 被加密后的字符串
	//  */
	// public static String encode(byte[] source) {
	// 	return str(encode(source, false), DEFAULT_CHARSET);
	// }
	//
	// /**
	//  * base64编码,URL安全的
	//  *
	//  * @param source 被编码的base64字符串
	//  * @return 被加密后的字符串
	//  */
	// public static String encodeUrlSafe(byte[] source) {
	// 	return str(encodeUrlSafe(source, false), DEFAULT_CHARSET);
	// }

	/**
	 * 编码为Base64<br>
	 * 如果isMultiLine为<code>true</code>，则每76个字符一个换行符，否则在一行显示
	 *
	 * @param arr         被编码的数组
	 * @param isMultiLine 在76个char之后是CRLF还是EOF
	 * @param isUrlSafe   是否使用URL安全字符，一般为<code>false</code>
	 * @return 编码后的bytes
	 */
	public static byte[] encode(byte[] arr, boolean isMultiLine, boolean isUrlSafe) {
		if (null == arr) {
			return null;
		}

		int len = arr.length;
		if (len == 0) {
			return new byte[0];
		}

		int evenlen = (len / 3) * 3;
		int cnt = ((len - 1) / 3 + 1) << 2;
		int destlen = cnt + (isMultiLine ? (cnt - 1) / 76 << 1 : 0);
		byte[] dest = new byte[destlen];

		byte[] encodeTable = isUrlSafe ? URL_SAFE_ENCODE_TABLE : STANDARD_ENCODE_TABLE;

		for (int s = 0, d = 0, cc = 0; s < evenlen; ) {
			int i = (arr[s++] & 0xff) << 16 | (arr[s++] & 0xff) << 8 | (arr[s++] & 0xff);

			dest[d++] = encodeTable[(i >>> 18) & 0x3f];
			dest[d++] = encodeTable[(i >>> 12) & 0x3f];
			dest[d++] = encodeTable[(i >>> 6) & 0x3f];
			dest[d++] = encodeTable[i & 0x3f];

			if (isMultiLine && ++cc == 19 && d < destlen - 2) {
				dest[d++] = '\r';
				dest[d++] = '\n';
				cc = 0;
			}
		}

		//剩余位数
		int left = len - evenlen;
		if (left > 0) {
			int i = ((arr[evenlen] & 0xff) << 10) | (left == 2 ? ((arr[len - 1] & 0xff) << 2) : 0);

			dest[destlen - 4] = encodeTable[i >> 12];
			dest[destlen - 3] = encodeTable[(i >>> 6) & 0x3f];

			if (isUrlSafe) {
				//在URL Safe模式下，=为URL中的关键字符，不需要补充。空余的byte位要去掉。
				int urlSafeLen = destlen - 2;
				if (2 == left) {
					dest[destlen - 2] = encodeTable[i & 0x3f];
					urlSafeLen += 1;
				}
				byte[] urlSafeDest = new byte[urlSafeLen];
				System.arraycopy(dest, 0, urlSafeDest, 0, urlSafeLen);
				return urlSafeDest;
			} else {
				dest[destlen - 2] = (left == 2) ? encodeTable[i & 0x3f] : (byte) '=';
				dest[destlen - 1] = '=';
			}
		}
		return dest;
	}

	/**
	 * 编码字符串
	 *
	 * @param str     字符串
	 * @param charset 字符集，如果此字段为空，则解码的结果取决于平台
	 * @return 编码后的字节码
	 */
	public static byte[] bytes(CharSequence str, Charset charset) {
		if (str == null) {
			return null;
		}

		if (null == charset) {
			return str.toString().getBytes();
		}
		return str.toString().getBytes(charset);
	}

	/**
	 * 解码字节码
	 *
	 * @param data    字符串
	 * @param charset 字符集，如果此字段为空，则解码的结果取决于平台
	 * @return 解码后的字符串
	 */
	public static String str(byte[] data, Charset charset) {
		if (data == null) {
			return null;
		}

		if (null == charset) {
			return new String(data);
		}
		return new String(data, charset);
	}

}
