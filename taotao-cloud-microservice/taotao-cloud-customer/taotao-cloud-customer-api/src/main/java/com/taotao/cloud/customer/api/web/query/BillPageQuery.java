package com.taotao.cloud.customer.api.web.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.taotao.cloud.common.model.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 结算单搜索参数
 *
 *
 * @since 2021/3/17 6:08 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "结算单搜索参数")
public class BillPageQuery extends PageParam {

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Schema(description = "起始日期")
	private String startDate;

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Schema(description = "结束日期")
	private String endDate;

	@Schema(description = "账单号")
	private String sn;

	@Schema(description = "状态：OUT(已出账),CHECK(已对账),EXAMINE(已审核),PAY(已付款)")
	private String billStatus;

	@Schema(description = "店铺名称")
	private String storeName;

	@Schema(description = "店铺ID", hidden = true)
	private Long storeId;

	public <T> QueryWrapper<T> queryWrapper() {
		QueryWrapper<T> wrapper = new QueryWrapper<>();

		//创建时间
		if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
			wrapper.between("create_time", startDate, endDate);
		}
		//账单号
		wrapper.eq(StringUtils.isNotEmpty(sn), "sn", sn);
		//结算状态
		wrapper.eq(StringUtils.isNotEmpty(billStatus), "bill_status", billStatus);
		//店铺名称
		wrapper.eq(StringUtils.isNotEmpty(storeName), "store_name", storeName);
		//按卖家查询
		// wrapper.eq(StringUtils.equals(UserContext.getCurrentUser().getRole().name(),
		// 		UserEnums.STORE.name()),
		// 	"store_id", UserContext.getCurrentUser().getStoreId());
		return wrapper;
	}

}
