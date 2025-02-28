package com.taotao.cloud.promotion.biz.api.controller.buyer;

import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.logger.annotation.RequestLogger;
import com.taotao.cloud.promotion.api.web.vo.SeckillGoodsVO;
import com.taotao.cloud.promotion.api.web.vo.SeckillTimelineVO;
import com.taotao.cloud.promotion.biz.service.SeckillApplyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 买家端,秒杀活动接口
 *
 * @since 2020/11/17 2:30 下午
 */
@Tag(name = "买家端,秒杀活动接口")
@RestController
@RequestMapping("/buyer/promotion/seckill")
public class SeckillBuyerController {

	/**
	 * 秒杀活动
	 */
	@Autowired
	private SeckillApplyService seckillApplyService;

	@RequestLogger
	@PreAuthorize("hasAuthority('sys:resource:info:roleId')")
	@Operation(summary = "获取当天秒杀活动信息")
	@GetMapping
	public Result<List<SeckillTimelineVO>> getSeckillTime() {
		return Result.success(seckillApplyService.getSeckillTimeline());
	}

	@RequestLogger
	@PreAuthorize("hasAuthority('sys:resource:info:roleId')")
	@Operation(summary = "获取某个时刻的秒杀活动商品信息")
	@GetMapping("/{timeline}")
	public Result<List<SeckillGoodsVO>> getSeckillGoods(@PathVariable Integer timeline) {
		return Result.success(seckillApplyService.getSeckillGoods(timeline));
	}

}
