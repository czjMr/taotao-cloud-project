package com.taotao.cloud.promotion.api.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.experimental.SuperBuilder;


/**
 * 砍价活动商品实体
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class KanjiaActivityLogDTO implements Serializable {
	private Long id;

	@Schema(description = "砍价活动参与记录id")
	private Long kanjiaActivityId;

	@Schema(description = "砍价会员id")
	private Long kanjiaMemberId;

	@Schema(description = "砍价会员名称")
	private String kanjiaMemberName;

	@Schema(description = "砍价会员头像")
	private String kanjiaMemberFace;

	@Schema(description = "砍价金额")
	private BigDecimal kanjiaPrice;

	@Schema(description = "剩余购买金额")
	private BigDecimal surplusPrice;
}
