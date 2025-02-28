package com.taotao.cloud.report.api.web.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 首页统计内容
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IndexStatisticsVO {

    @Schema(description =  "订单总数量")
    private Long orderNum;
    @Schema(description =  "商品总数量")
    private Long goodsNum;
    @Schema(description =  "会员总数量")
    private Long memberNum;
    @Schema(description =  "店铺总数量")
    private Long storeNum;

    /**
     * 流量概括
     */
    @Schema(description =  "今日访问数UV")
    private Integer todayUV;
    @Schema(description =  "昨日访问数UV")
    private Integer yesterdayUV;
    @Schema(description =  "前七日访问数UV")
    private Integer lastSevenUV;
    @Schema(description =  "三十日访问数UV")
    private Integer lastThirtyUV;

    /**
     * 今日信息概括
     */
    @Schema(description =  "今日订单数")
    private Long todayOrderNum;
    @Schema(description =  "今日下单金额")
    private BigDecimal todayOrderPrice;
    @Schema(description =  "今日新增会员数量")
    private Long todayMemberNum;
    @Schema(description =  "今日新增商品数量")
    private Long todayGoodsNum;
    @Schema(description =  "今日新增店铺数量")
    private Long todayStoreNum;
    @Schema(description =  "今日新增评论数量")
    private Long todayMemberEvaluation;
    @Schema(description =  "当前在线人数")
    private Long currentNumberPeopleOnline;
}
