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
package com.taotao.cloud.common.utils.number;

import com.taotao.cloud.common.utils.lang.ObjectUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Random;
import javax.annotation.Nullable;

/**
 * NumberUtil
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-02 16:32:13
 */
public class NumberUtils extends org.springframework.util.NumberUtils {

	private NumberUtils() {
	}

	/**
	 * <p>Convert a <code>String</code> to an <code>int</code>, returning
	 * <code>zero</code> if the conversion fails.</p>
	 *
	 * <p>If the string is <code>null</code>, <code>zero</code> is returned.</p>
	 *
	 * <pre>
	 *   NumberUtil.toInt(null) = 0
	 *   NumberUtil.toInt("")   = 0
	 *   NumberUtil.toInt("1")  = 1
	 * </pre>
	 *
	 * @param str the string to convert, may be null
	 * @return the int represented by the string, or <code>zero</code> if conversion fails
	 */
	public static int toInt(final String str) {
		return ObjectUtils.toInt(str, 0);
	}

	/**
	 * <p>Convert a <code>String</code> to an <code>int</code>, returning a
	 * default value if the conversion fails.</p>
	 *
	 * <p>If the string is <code>null</code>, the default value is returned.</p>
	 *
	 * <pre>
	 *   NumberUtil.toInt(null, 1) = 1
	 *   NumberUtil.toInt("", 1)   = 1
	 *   NumberUtil.toInt("1", 0)  = 1
	 * </pre>
	 *
	 * @param str          the string to convert, may be null
	 * @param defaultValue the default value
	 * @return the int represented by the string, or the default if conversion fails
	 */
	public static int toInt(@Nullable final String str, final int defaultValue) {
		return ObjectUtils.toInt(str, defaultValue);
	}

	/**
	 * <p>Convert a <code>String</code> to a <code>long</code>, returning
	 * <code>zero</code> if the conversion fails.</p>
	 *
	 * <p>If the string is <code>null</code>, <code>zero</code> is returned.</p>
	 *
	 * <pre>
	 *   NumberUtil.toLong(null) = 0L
	 *   NumberUtil.toLong("")   = 0L
	 *   NumberUtil.toLong("1")  = 1L
	 * </pre>
	 *
	 * @param str the string to convert, may be null
	 * @return the long represented by the string, or <code>0</code> if conversion fails
	 */
	public static long toLong(final String str) {
		return ObjectUtils.toLong(str, 0L);
	}

	/**
	 * <p>Convert a <code>String</code> to a <code>long</code>, returning a
	 * default value if the conversion fails.</p>
	 *
	 * <p>If the string is <code>null</code>, the default value is returned.</p>
	 *
	 * <pre>
	 *   NumberUtil.toLong(null, 1L) = 1L
	 *   NumberUtil.toLong("", 1L)   = 1L
	 *   NumberUtil.toLong("1", 0L)  = 1L
	 * </pre>
	 *
	 * @param str          the string to convert, may be null
	 * @param defaultValue the default value
	 * @return the long represented by the string, or the default if conversion fails
	 */
	public static long toLong(@Nullable final String str, final long defaultValue) {
		return ObjectUtils.toLong(str, defaultValue);
	}

	/**
	 * All possible chars for representing a number as a String
	 */
	public final static byte[] DIGITS = {
		'0', '1', '2', '3', '4', '5',
		'6', '7', '8', '9', 'a', 'b',
		'c', 'd', 'e', 'f', 'g', 'h',
		'i', 'j', 'k', 'l', 'm', 'n',
		'o', 'p', 'q', 'r', 's', 't',
		'u', 'v', 'w', 'x', 'y', 'z',
		'A', 'B', 'C', 'D', 'E', 'F',
		'G', 'H', 'I', 'J', 'K', 'L',
		'M', 'N', 'O', 'P', 'Q', 'R',
		'S', 'T', 'U', 'V', 'W', 'X',
		'Y', 'Z'
	};

	/**
	 * 将 long 转短字符串 为 62 进制
	 *
	 * @param i 数字
	 * @return 短字符串
	 */
	public static String to62Str(long i) {
		int radix = DIGITS.length;
		byte[] buf = new byte[65];
		int charPos = 64;
		i = -i;
		while (i <= -radix) {
			buf[charPos--] = DIGITS[(int) (-(i % radix))];
			i = i / radix;
		}
		buf[charPos] = DIGITS[(int) (-i)];
		return new String(buf, charPos, (65 - charPos), StandardCharsets.UTF_8);
	}


	/**
	 * hanArr
	 */
	private static final String[] hanArr = {"零", "一", "二", "三", "四", "五", "六", "七", "八",
		"九"};
	/**
	 * unitArr
	 */
	private static final String[] unitArr = {"十", "百", "千", "万", "十", "白", "千", "亿", "十",
		"百", "千"};

	/**
	 * 数字转double
	 *
	 * @param number number
	 * @param scale  scale
	 * @return double
	 * @since 2021-09-02 16:32:28
	 */
	public static double scale(Number number, int scale) {
		if (Objects.nonNull(number)) {
			try {
				BigDecimal bg = BigDecimal.valueOf(number.doubleValue());
				return bg.setScale(scale, RoundingMode.HALF_UP).doubleValue();
			} catch (Exception e) {
				//todo  需要判断NAN
				//LogUtil.error(e);
				return 0;
			}
		}
		return 0;
	}

	/**
	 * String转成int的值， 若无法转换，默认返回0
	 *
	 * @param string string
	 * @return int
	 * @since 2021-09-02 16:33:31
	 */
	public static int stoi(String string) {
		return stoi(string, 0);
	}

	/**
	 * stoi
	 *
	 * @param string       string
	 * @param defaultValue defaultValue
	 * @return int
	 * @since 2021-09-02 16:33:43
	 */
	public static int stoi(String string, int defaultValue) {
		if ((string == null) || (string.equalsIgnoreCase("") || (string.equals("null")))) {
			return defaultValue;
		}
		int id;
		try {
			id = Integer.parseInt(string);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return defaultValue;
		}
		return id;
	}

	/**
	 * String转成long的值， 若无法转换，默认返回0
	 *
	 * @param string string
	 * @return long
	 * @since 2021-09-02 16:33:50
	 */
	public static long stol(String string) {
		return stol(string, 0);
	}

	/**
	 * stol
	 *
	 * @param string       string
	 * @param defaultValue defaultValue
	 * @return long
	 * @since 2021-09-02 16:33:53
	 */
	public static long stol(String string, long defaultValue) {
		if ((string == null) || (string.equalsIgnoreCase(""))) {
			return defaultValue;
		}
		long ret;
		try {
			ret = Long.parseLong(string);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return defaultValue;
		}

		return ret;
	}

	/**
	 * String转成double的值， 若无法转换，默认返回0.00
	 *
	 * @param string string
	 * @return double
	 * @since 2021-09-02 16:34:04
	 */
	public static double stod(String string) {
		return stod(string, 0.00);
	}

	/**
	 * stod
	 *
	 * @param string       string
	 * @param defaultValue defaultValue
	 * @return double
	 * @since 2021-09-02 16:34:07
	 */
	public static double stod(String string, double defaultValue) {
		if ((string == null) || (string.equalsIgnoreCase(""))) {
			return defaultValue;
		}
		double ret;
		try {
			ret = Double.parseDouble(string);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return defaultValue;
		}

		return ret;
	}

	/**
	 * 将整数转成中文表示
	 *
	 * @param number number
	 * @return 中文
	 * @since 2021-09-02 16:34:12
	 */
	public static String toChineseNum(int number) {
		String numStr = String.valueOf(number);
		StringBuilder result = new StringBuilder();
		int numLen = numStr.length();
		for (int i = 0; i < numLen; i++) {
			int num = numStr.charAt(i) - 48;
			if (i != numLen - 1 && num != 0) {
				result.append(hanArr[num]).append(unitArr[numLen - 2 - i]);
				if (number >= 10 && number < 20) {
					result = new StringBuilder(result.substring(1));
				}
			} else {
				if (!(number >= 10 && number % 10 == 0)) {
					result.append(hanArr[num]);
				}
			}
		}
		return result.toString();
	}


	/**
	 * 获取一个属于[min, max)中的随机数
	 *
	 * @param min min
	 * @param max max
	 * @return int
	 * @since 2021-09-02 16:34:18
	 */
	public static int random(int min, int max) {
		return new Random().nextInt(max - min) + min;
	}

	public static int compare(BigDecimal flowPrice, BigDecimal actualRefundPrice) {
		return flowPrice.compareTo(actualRefundPrice);
	}
}
