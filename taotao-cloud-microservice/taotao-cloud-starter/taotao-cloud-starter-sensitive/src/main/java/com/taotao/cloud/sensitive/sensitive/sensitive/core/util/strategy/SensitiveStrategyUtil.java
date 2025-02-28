package com.taotao.cloud.sensitive.sensitive.sensitive.core.util.strategy;


import com.taotao.cloud.common.constant.PunctuationConst;
import com.taotao.cloud.common.utils.lang.StringUtils;

/**
 * 脱敏策略工具类
 * （1）提供常见的脱敏策略
 * （2）主要供单独的字符串处理使用
 */
public final class SensitiveStrategyUtil {

    private SensitiveStrategyUtil(){}

    /**
     * 脱敏密码
     * @param password 原始密码
     * @return 结果
     */
    public static String password(final String password) {
        return null;
    }

    /**
     * 脱敏电话号码
     * @param phone 电话号码
     * @return 结果
     */
    public static String phone(final String phone) {
        final int prefixLength = 3;
        final String middle = "****";
        return StringUtils.buildString(phone, middle, prefixLength);
    }

    /**
     * 脱敏邮箱
     * @param email 邮箱
     * @return 结果
     */
    public static String email(final String email) {
        if(StringUtils.isEmpty(email)) {
            return null;
        }

        final int prefixLength = 3;

        final int atIndex = email.indexOf(PunctuationConst.AT);
        String middle = "****";

        if(atIndex > 0) {
            int middleLength = atIndex - prefixLength;
            middle = StringUtils.repeat(PunctuationConst.STAR, middleLength);
        }
        return StringUtils.buildString(email, middle, prefixLength);
    }

    /**
     * 脱敏中文名称
     * @param chineseName 中文名称
     * @return 脱敏后的结果
     */
    public static String chineseName(final String chineseName) {
        if(StringUtils.isEmpty(chineseName)) {
            return chineseName;
        }

        final int nameLength = chineseName.length();
        if(1 == nameLength) {
            return chineseName;
        }

        if(2 == nameLength) {
            return PunctuationConst.STAR + chineseName.charAt(1);
        }

        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append(chineseName.charAt(0));
        for(int i = 0; i < nameLength-2; i++) {
            stringBuffer.append(PunctuationConst.STAR);
        }
        stringBuffer.append(chineseName.charAt(nameLength -1));
        return stringBuffer.toString();
    }

    /**
     * 脱敏卡号
     * @param cardId 卡号
     * @return 脱敏结果
     */
    public static String cardId(final String cardId) {
        final int prefixLength = 6;
        final String middle = "**********";
        return StringUtils.buildString(cardId, middle, prefixLength);
    }

}
