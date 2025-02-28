package com.taotao.cloud.promotion.api.web.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.taotao.cloud.common.enums.PromotionTypeEnum;
import com.taotao.cloud.promotion.api.enums.PromotionsScopeTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 促销活动商品实
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PromotionGoodsVO implements Serializable {

	public static final String TABLE_NAME = "tt_promotion_goods";

	@Schema(description = "商家ID")
	private Long storeId;

	@Schema(description = "商家名称")
	private String storeName;

	@Schema(description = "商品id")
	private Long goodsId;

	@Schema(description = "商品SkuId")
	private Long skuId;

	@Schema(description = "商品名称")
	private String goodsName;

	@Schema(description = "缩略图")
	private String thumbnail;

	@Schema(description = "活动开始时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startTime;

	@Schema(description = "活动结束时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endTime;

	@Schema(description = "活动id")
	private Long promotionId;

	/**
	 * @see PromotionTypeEnum
	 */
	@Schema(description = "促销工具类型")
	private String promotionType;

	@Schema(description = "商品类型")
	private String goodsType;

	@Schema(description = "活动标题")
	private String title;

	@Schema(description = "卖出的商品数量")
	private Integer num;

	@Schema(description = "原价")
	private BigDecimal originalPrice;

	@Schema(description = "促销价格")
	private BigDecimal price;

	@Schema(description = "兑换积分")
	private Long points;

	@Schema(description = "限购数量")
	private Integer limitNum;

	@Schema(description = "促销库存")
	private Integer quantity;

	@Schema(description = "分类path")
	private String categoryPath;

	/**
	 * @see PromotionsScopeTypeEnum
	 */
	@Schema(description = "关联范围类型")
	private String scopeType;


	@Schema(description = "范围关联的id")
	private String scopeId;
}
