package com.taotao.cloud.sensitive.sensitive.sensitive.core.api.strategory;

import com.taotao.cloud.common.utils.lang.ObjectUtils;
import com.taotao.cloud.sensitive.sensitive.sensitive.api.IContext;
import com.taotao.cloud.sensitive.sensitive.sensitive.api.IStrategy;
import com.taotao.cloud.sensitive.sensitive.sensitive.core.util.strategy.SensitiveStrategyUtil;

/**
 * 二代身份证号脱敏加密：
 * XXXXXX XXXXXXXX XXXX
 * 脱敏规则：421002**********34
 *
 * 只保留前6位和后2位，其他用*代替。
 */
public class StrategyCardId implements IStrategy {

    @Override
    public Object des(Object original, IContext context) {
        return SensitiveStrategyUtil.cardId(ObjectUtils.objectToString(original));
    }

}
