package com.taotao.cloud.sensitive.sensitive.sensitive.core.api.strategory;

import com.taotao.cloud.common.utils.lang.ObjectUtils;
import com.taotao.cloud.sensitive.sensitive.sensitive.api.IContext;
import com.taotao.cloud.sensitive.sensitive.sensitive.api.IStrategy;
import com.taotao.cloud.sensitive.sensitive.sensitive.core.util.strategy.SensitiveStrategyUtil;

/**
 * 密码的脱敏策略：
 * 1. 直接返回 null
 */
public class StrategyPassword implements IStrategy {

    @Override
    public Object des(Object original, IContext context) {
        return SensitiveStrategyUtil.password(ObjectUtils.objectToString(original));
    }

}
