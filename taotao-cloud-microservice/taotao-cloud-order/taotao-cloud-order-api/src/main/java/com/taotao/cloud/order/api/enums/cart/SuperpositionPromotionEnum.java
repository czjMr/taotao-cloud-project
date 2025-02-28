package com.taotao.cloud.order.api.enums.cart;

/**
 * 活动叠加
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 09:21:46
 */
public enum SuperpositionPromotionEnum {

	/**
	 * 商品促销放在商品属性，这里只负责可叠加的其他促销 叠加促销枚举，每一个商品，以下每个参数都只能参加一个
	 */
	SELLER_COUPON("店铺优惠券"),
	PLATFORM_COUPON("平台优惠券"),
	FULL_DISCOUNT("满优惠");

	private final String description;

	SuperpositionPromotionEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
