package com.taotao.cloud.sensitive.sensitive.word.constant;

public final class AppConst {

    private AppConst(){}

    /**
     * 是否为结束标识
     * ps: 某种角度而言，我不是很喜欢这种风格。
     * （1）正常的 char 只會占用一個字符，这里直接给定两个字符即可，降低 Map 的容量。
     */
    public static final String IS_END = "ED";

    /**
     * 字典的大小
     */
    public static final int DICT_SIZE = 65275;

    /**
     * 英语词典的大小
     */
    public static final int DICT_EN_SIZE = 12;

    /**
     * 拒绝的词语
     */
    public static final String SENSITIVE_WORD_DENY_PATH = "/sensitive_word_deny.txt";

    /**
     * 用户允许的词语
     */
    public static final String SENSITIVE_WORD_ALLOW_PATH = "/sensitive_word_allow.txt";

}
