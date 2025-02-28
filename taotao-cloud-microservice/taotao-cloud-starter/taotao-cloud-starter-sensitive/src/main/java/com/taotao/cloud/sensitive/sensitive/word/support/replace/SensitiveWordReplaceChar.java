package com.taotao.cloud.sensitive.sensitive.word.support.replace;

import com.taotao.cloud.common.utils.lang.CharUtils;
import com.taotao.cloud.sensitive.sensitive.word.api.ISensitiveWordReplace;
import com.taotao.cloud.sensitive.sensitive.word.api.ISensitiveWordReplaceContext;

/**
 * 指定字符的替换策略
 */
public class SensitiveWordReplaceChar implements ISensitiveWordReplace {

    private final char replaceChar;

    public SensitiveWordReplaceChar(char replaceChar) {
        this.replaceChar = replaceChar;
    }

    @Override
    public String replace(ISensitiveWordReplaceContext context) {
        int wordLength = context.wordLength();

        return CharUtils.repeat(replaceChar, wordLength);
    }

}
