package com.taotao.cloud.store.biz.task;

import cn.hutool.core.date.DateUtil;
import com.taotao.cloud.store.api.web.dto.StoreSettlementDay;
import com.taotao.cloud.store.biz.service.BillService;
import com.taotao.cloud.store.biz.service.StoreDetailService;
import com.taotao.cloud.web.timetask.EveryDayExecute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 店铺结算执行
 */
@Component
public class BillExecute implements EveryDayExecute {

	/**
	 * 结算单
	 */
	@Autowired
	private BillService billService;
	/**
	 * 店铺详情
	 */
	@Autowired
	private StoreDetailService storeDetailService;

	/**
	 * 1.查询今日待结算的商家 2.查询商家上次结算日期，生成本次结算单 3.记录商家结算日
	 */
	@Override
	public void execute() {

		//获取当前天数
		int day = DateUtil.date().dayOfMonth();

		//获取待结算商家列表
		List<StoreSettlementDay> storeList = storeDetailService.getSettlementStore(day);

		//获取当前时间
		LocalDateTime endTime = LocalDateTime.now();
		//批量商家结算
		for (StoreSettlementDay storeSettlementDay : storeList) {

			//生成结算单
			billService.createBill(storeSettlementDay.getStoreId(),
				storeSettlementDay.getSettlementDay(), LocalDateTime.now());

			//修改店铺结算时间
			storeDetailService.updateSettlementDay(storeSettlementDay.getStoreId(), endTime);
		}
	}
}
