package com.taotao.cloud.pinyin.support.tone;


import com.taotao.cloud.common.support.instance.impl.Instances;
import com.taotao.cloud.pinyin.spi.IPinyinTone;

public final class PinyinTones {

    private PinyinTones(){}

    /**
     * 默认实现
     * @return 默认实现
     */
    public static IPinyinTone defaults() {
        return Instances.singleton(DefaultPinyinTone.class);
    }

}
