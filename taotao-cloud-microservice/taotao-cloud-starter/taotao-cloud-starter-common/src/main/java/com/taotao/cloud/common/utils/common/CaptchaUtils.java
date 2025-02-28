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
package com.taotao.cloud.common.utils.common;

import com.taotao.cloud.common.utils.log.LogUtils;
import com.wf.captcha.ArithmeticCaptcha;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * 生成验证码工具类
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-02 17:44:44
 */
public final class CaptchaUtils {

	private CaptchaUtils() {
	}

	/**
	 * width
	 */
	private static final int width = 200;
	/**
	 * height
	 */
	private static final int height = 50;
	/**
	 * random
	 */
	private static Random random;

	static {
		try {
			random = SecureRandom.getInstanceStrong();
		} catch (NoSuchAlgorithmException e) {
			LogUtils.error(e);
		}
	}

	/**
	 * 获取验证码
	 *
	 * @return 验证码
	 * @since 2021-09-02 17:45:06
	 */
	public static ArithmeticCaptcha getArithmeticCaptcha() {
		ArithmeticCaptcha captcha = new ArithmeticCaptcha(width, height);
		captcha.setLen(2);
		return captcha;
	}

	/**
	 * 生成图片
	 *
	 * @return 图片对象
	 * @since 2021-09-02 17:45:13
	 */
	public static BufferedImage createImage() {
		//生成对应宽高的初始图片
		return new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}

	/**
	 * 随机画图
	 *
	 * @param verifyImg 图片对象
	 * @return 字符串
	 * @since 2021-09-02 17:45:18
	 */
	public static String drawRandomText(BufferedImage verifyImg) {
		Graphics2D graphics = (Graphics2D) verifyImg.getGraphics();
		//设置画笔颜色-验证码背景色
		graphics.setColor(Color.WHITE);
		//填充背景
		graphics.fillRect(0, 0, width, height);
		graphics.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		//数字和字母的组合
		String baseNumLetter = "123456789abcdefghijklmnopqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";
		StringBuilder sBuffer = new StringBuilder();
		//旋转原点的 x 坐标
		int x = 10;
		String ch;
		for (int i = 0; i < 4; i++) {
			graphics.setColor(getRandomColor());
			//设置字体旋转角度
			//角度小于30度
			int degree = random.nextInt() % 30;
			int dot = random.nextInt(baseNumLetter.length());
			ch = baseNumLetter.charAt(dot) + "";
			sBuffer.append(ch);
			//正向旋转
			graphics.rotate(degree * Math.PI / 180, x, 45);
			graphics.drawString(ch, x, 45);
			//反向旋转
			graphics.rotate(-degree * Math.PI / 180, x, 45);
			x += 48;
		}

		//画干扰线
		for (int i = 0; i < 6; i++) {
			// 设置随机颜色
			graphics.setColor(getRandomColor());
			// 随机画线
			graphics.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width),
				random.nextInt(height));
		}

		//添加噪点
		for (int i = 0; i < 30; i++) {
			int x1 = random.nextInt(width);
			int y1 = random.nextInt(height);
			graphics.setColor(getRandomColor());
			graphics.fillRect(x1, y1, 2, 1);
		}
		return sBuffer.toString();
	}

	/**
	 * 随机取色
	 *
	 * @return 色彩对象
	 * @since 2021-09-02 17:45:27
	 */
	private static Color getRandomColor() {
		return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
	}

	/**
	 * 默认key策略
	 *
	 * @param key        key
	 * @param targetName targetName
	 * @param methodName methodName
	 * @param arguments  arguments
	 * @return key
	 * @since 2021-09-02 19:41:46
	 */
	public String getCacheKey(String key, String targetName, String methodName,
		Object[] arguments) {
		StringBuilder sb = new StringBuilder();
		if (key != null && key.length() > 0) {
			sb.append(key);
		} else {
			sb.append(targetName).append(".").append(methodName);
		}
		if (arguments != null && (arguments.length != 0)) {
			sb.append("#").append(JsonUtils.toJSONString(arguments));
		}
		return sb.toString().replace("[", "").replace("\"", "").replace("]", "")
			.replace("com.gofun.", "");
	}
}
