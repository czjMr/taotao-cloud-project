package com.taotao.cloud.common.utils.common;


import com.taotao.cloud.common.utils.lang.StringUtils;

/**
 * 表情工具类
 */
public final class EmojiUtils {

    private EmojiUtils(){}

    /**
     * 替换掉 emoji 表情
     * @param text 文本
     * @param replaceText 替换的文本
     * @return 结果
     */
    public static String replaceEmoji(final String text, final String replaceText) {
        if(StringUtils.isEmpty(text)) {
            return text;
        }

        return text.replaceAll("[\ud800\udc00-\udbff\udfff\ud800-\udfff]", replaceText);
    }

    /**
     * 替换掉 emoji 表情
     * @param text 文本
     * @return 结果
     */
    public static String replaceEmoji(final String text) {
        return replaceEmoji(text, "");
    }

}
