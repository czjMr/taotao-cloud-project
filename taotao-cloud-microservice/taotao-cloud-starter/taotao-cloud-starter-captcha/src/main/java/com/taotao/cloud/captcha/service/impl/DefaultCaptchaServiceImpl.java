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
package com.taotao.cloud.captcha.service.impl;


import cn.hutool.core.util.StrUtil;
import com.taotao.cloud.captcha.model.Captcha;
import com.taotao.cloud.captcha.model.CaptchaException;
import com.taotao.cloud.captcha.model.CaptchaCodeEnum;
import com.taotao.cloud.captcha.service.CaptchaService;
import com.taotao.cloud.common.utils.log.LogUtils;
import java.util.Properties;

/**
 * DefaultCaptchaServiceImpl 
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-03 21:02:02
 */
public class DefaultCaptchaServiceImpl extends AbstractCaptchaService {

	@Override
	public String captchaType() {
		return "default";
	}

	@Override
	public void init(Properties config) {
		for (String s : CaptchaServiceFactory.instances.keySet()) {
			if (captchaType().equals(s)) {
				continue;
			}
			getService(s).init(config);
		}
	}

	@Override
	public void destroy(Properties config) {
		for (String s : CaptchaServiceFactory.instances.keySet()) {
			if (captchaType().equals(s)) {
				continue;
			}
			getService(s).destroy(config);
		}
	}

	/**
	 * getService 
	 *
	 * @param captchaType captchaType 
	 * @return {@link com.taotao.cloud.captcha.service.CaptchaService }
	 * @since 2021-09-04 07:43:59
	 */
	private CaptchaService getService(String captchaType) {
		return CaptchaServiceFactory.instances.get(captchaType);
	}

	@Override
	public Captcha get(Captcha captcha) {
		if (captcha == null) {
			throw new CaptchaException(CaptchaCodeEnum.NULL_ERROR.parseError("captchaVO"));
		}
		if (StrUtil.isEmpty(captcha.getCaptchaType())) {
			throw new CaptchaException(CaptchaCodeEnum.NULL_ERROR.parseError("类型"));
		}
		return getService(captcha.getCaptchaType()).get(captcha);
	}

	@Override
	public Captcha check(Captcha captcha) {
		if (captcha == null) {
			throw new CaptchaException(CaptchaCodeEnum.NULL_ERROR.parseError("captchaVO"));
		}
		if (StrUtil.isEmpty(captcha.getCaptchaType())) {
			throw new CaptchaException(CaptchaCodeEnum.NULL_ERROR.parseError("二次校验参数"));
		}
		if (StrUtil.isEmpty(captcha.getToken())) {
			throw new CaptchaException(CaptchaCodeEnum.NULL_ERROR.parseError("token"));
		}
		return getService(captcha.getCaptchaType()).check(captcha);
	}

	@Override
	public Captcha verification(Captcha captcha) {
		if (captcha == null) {
			throw new CaptchaException(CaptchaCodeEnum.NULL_ERROR.parseError("captchaVO"));
		}
		if (StrUtil.isEmpty(captcha.getCaptchaVerification())) {
			throw new CaptchaException(CaptchaCodeEnum.NULL_ERROR.parseError("二次校验参数"));
		}

		try {
			String codeKey = String.format(REDIS_SECOND_CAPTCHA_KEY,
				captcha.getCaptchaVerification());
			if (!CaptchaServiceFactory.getCache(cacheType).exists(codeKey)) {
				throw new CaptchaException(CaptchaCodeEnum.API_CAPTCHA_INVALID);
			}
			//二次校验取值后，即刻失效
			CaptchaServiceFactory.getCache(cacheType).delete(codeKey);
		} catch (Exception e) {
			LogUtils.error("验证码坐标解析失败", e);
			throw new CaptchaException(e.getMessage());
		}
		return captcha;
	}

}
