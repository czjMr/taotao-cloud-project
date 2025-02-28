package com.taotao.cloud.common.model.query.code;

import cn.hutool.core.util.StrUtil;

/**
 * 参数类型
 */
public enum ParamTypeEnum {

    /** 数字 */
    NUMBER("number","数字"),

    /** 字符串 */
    STRING("string","字符串"),

    /** 布尔 */
    BOOLEAN("boolean","布尔"),

    /** 日期 */
    DATE("date","日期"),

    /** 时间 */
    TIME("time","时间"),

    /** 日期时间 */
    DATE_TIME("date_time","日期时间"),

    /** 列表 */
    LIST("list","列表");

    /** 类型编码 */
    private final String code;
    /** 类型名称 */
    private final String name;


    public static ParamTypeEnum getByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return null;
        }
        for (ParamTypeEnum val : values()) {
            if (val.getCode().equals(code)) {
                return val;
            }
        }
        return null;
    }

	ParamTypeEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
}
