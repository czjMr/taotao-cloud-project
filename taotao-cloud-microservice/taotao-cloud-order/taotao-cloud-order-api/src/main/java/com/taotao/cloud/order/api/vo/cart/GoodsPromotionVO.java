package com.taotao.cloud.order.api.vo.cart;

import cn.lili.common.enums.PromotionTypeEnum;
import cn.lili.modules.promotion.entity.dos.PromotionGoods;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 商品促销VO
 */
@Data
@Schema(description = "商品促销VO 购物车中")
public class GoodsPromotionVO implements Serializable {


	private static final long serialVersionUID = 1622051257060817414L;
	@Schema(description = "活动开始时间")
	private Date startTime;

	@Schema(description = "活动结束时间")
	private Date endTime;

	@Schema(description = "活动id")
	private String promotionId;

	/**
	 * @see PromotionTypeEnum
	 */
	@Schema(description = "活动工具类型")
	private String promotionType;

	@Schema(description = "活动名称")
	private String title;


	@Schema(description = "限购数量")
	private Integer limitNum;

	public GoodsPromotionVO(PromotionGoods promotionGoods) {
		this.startTime = promotionGoods.getStartTime();
		this.endTime = promotionGoods.getEndTime();
		this.promotionId = promotionGoods.getPromotionId();
		this.setPromotionType(promotionGoods.getPromotionType());
		this.setLimitNum(promotionGoods.getLimitNum());
	}

	public GoodsPromotionVO() {

	}
}
