/*
 * Copyright 2002-2021 the original author or authors.
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
package com.taotao.cloud.gateway.filter.gateway;

import cn.hutool.core.util.StrUtil;
import com.taotao.cloud.common.constant.CommonConstant;
import com.taotao.cloud.common.constant.RedisConstant;
import com.taotao.cloud.common.constant.SecurityConstant;
import com.taotao.cloud.redis.repository.RedisRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

/**
 * 图形验证码
 *
 * @author dengtao
 * @since 2020/4/29 22:13
 * @version 1.0.0
 */
@Component
@AllArgsConstructor
public class ImageCodeGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {
    private static final String PARAM_CODE = "code";
    private static final String PARAM_T = "t";

    private static final String NOT_CODE_NULL = "验证码不能为空";
    private static final String NOT_LEGAL = "验证码不合法";
    private static final String INVALID = "验证码已失效";
    private static final String ERROR = "验证码错误";

    private final RedisRepository redisRepository;

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if (!StrUtil.containsAnyIgnoreCase(request.getURI().getPath(), SecurityConstant.OAUTH_TOKEN_URL)) {
                return chain.filter(exchange);
            }
            validateCode(request);
            return chain.filter(exchange);
        };
    }

    @SneakyThrows
    private void validateCode(ServerHttpRequest request) {
        MultiValueMap<String, String> params = request.getQueryParams();
        String code = params.getFirst(PARAM_CODE);
        String t = params.getFirst(PARAM_T);
        if (StrUtil.isBlank(code)) {
            throw new ValidateCodeException(NOT_CODE_NULL);
        }
        String key = RedisConstant.TAOTAO_CLOUD_CAPTCHA_KEY + t;
        if (!redisRepository.exists(key)) {
            throw new ValidateCodeException(NOT_LEGAL);
        }

        Object captcha = redisRepository.get(key);
        if (captcha == null) {
            throw new ValidateCodeException(INVALID);
        }
        if (!code.toLowerCase().equals(captcha)) {
            throw new ValidateCodeException(ERROR);
        }
    }
}
