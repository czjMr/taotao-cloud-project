package com.taotao.cloud.promotion.api.web.query;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.taotao.cloud.promotion.api.enums.PromotionsApplyStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import lombok.experimental.SuperBuilder;

/**
 * 秒杀活动查询通用类
 */
@Setter
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SeckillPageQuery extends BasePromotionsSearchQuery implements Serializable {

	@Serial
	private static final long serialVersionUID = -4052716630253333681L;

	@Schema(description = "秒杀活动活动编号")
	private String seckillId;

	@Schema(description = "活动名称")
	private String promotionName;

	@Schema(description = "时刻")
	private Integer timeLine;

	@Schema(description = "商家id")
	private String[] storeIds;

	@Schema(description = "商家编号")
	private String storeId;

	@Schema(description = "商品名称")
	private String goodsName;

	@Schema(description = "商家编号")
	private String skuId;

	/**
	 * @see PromotionsApplyStatusEnum
	 */
	@Schema(description = "APPLY(\"申请\"), PASS(\"通过\"), REFUSE(\"拒绝\")")
	private String promotionApplyStatus;

	@Override
	public <T> QueryWrapper<T> queryWrapper() {
		QueryWrapper<T> queryWrapper = super.queryWrapper();
		if (CharSequenceUtil.isNotEmpty(goodsName)) {
			queryWrapper.like("goods_name", goodsName);
		}
		if (CharSequenceUtil.isNotEmpty(promotionName)) {
			queryWrapper.like("promotion_name", promotionName);
		}
		if (CharSequenceUtil.isNotEmpty(seckillId)) {
			queryWrapper.eq("seckill_id", seckillId);
		}
		if (storeIds != null) {
			queryWrapper.in("store_id", Arrays.asList(storeIds));
		}
		if (timeLine != null) {
			queryWrapper.eq("time_line", timeLine);
		}
		if (CharSequenceUtil.isNotEmpty(promotionApplyStatus)) {
			queryWrapper.eq("promotion_apply_status", PromotionsApplyStatusEnum.valueOf(promotionApplyStatus).name());
		}
		if (CharSequenceUtil.isNotEmpty(skuId)) {
			queryWrapper.eq("sku_id", skuId);
		}
		return queryWrapper;
	}

}
