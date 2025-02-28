package com.taotao.cloud.member.api.enums;

/**
 * 积分类型枚举
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-25 16:37:20
 */
public enum PointTypeEnum {
	/**
	 * 增加
	 */
	INCREASE("增加"),
	/**
	 * 减少
	 */
	REDUCE("减少");

	private final String description;

	public String description() {
		return description;
	}

	PointTypeEnum(String description) {
		this.description = description;
	}
}
