package com.taotao.cloud.sensitive.sensitive.sensitive.core.api.strategory;

import com.taotao.cloud.common.utils.lang.ObjectUtils;
import com.taotao.cloud.sensitive.sensitive.sensitive.api.IContext;
import com.taotao.cloud.sensitive.sensitive.sensitive.api.IStrategy;
import com.taotao.cloud.sensitive.sensitive.sensitive.core.util.strategy.SensitiveStrategyUtil;

/**
 * 邮箱脱敏策略
 * 脱敏规则：
 * 保留前三位，中间隐藏4位。其他正常显示
 */
public class StrategyEmail implements IStrategy {

    @Override
    public Object des(Object original, IContext context) {
        return SensitiveStrategyUtil.email(ObjectUtils.objectToString(original));
    }

}
