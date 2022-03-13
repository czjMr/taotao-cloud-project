package com.taotao.cloud.goods.biz.controller.seller;


import cn.hutool.core.util.PageUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.cloud.common.constant.CommonConstant;
import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.goods.biz.entity.GoodsUnit;
import com.taotao.cloud.goods.biz.service.GoodsUnitService;
import com.taotao.cloud.logger.annotation.RequestLogger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 店铺端,商品计量单位接口
 */
@Validated
@RestController
@Tag(name = "商户管理端-商品计量单位API", description = "商户管理端-商品计量单位API")
@RequestMapping("/goods/seller/store/goods/goodsUnit")
public class GoodsUnitStoreController {

	@Autowired
	private GoodsUnitService goodsUnitService;

	@Operation(summary = "分页获取商品计量单位", description = "分页获取商品计量单位", method = CommonConstant.GET)
	@RequestLogger(description = "分页获取商品计量单位")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping
	public Result<IPage<GoodsUnit>> getByPage(PageVO pageVO) {
		return Result.success(goodsUnitService.page(PageUtil.initPage(pageVO)));
	}


}
