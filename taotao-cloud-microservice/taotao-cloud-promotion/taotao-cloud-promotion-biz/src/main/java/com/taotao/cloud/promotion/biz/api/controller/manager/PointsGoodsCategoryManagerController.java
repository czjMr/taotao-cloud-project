package com.taotao.cloud.promotion.biz.api.controller.manager;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.logger.annotation.RequestLogger;
import com.taotao.cloud.promotion.api.web.vo.PointsGoodsCategoryVO;
import com.taotao.cloud.promotion.biz.model.entity.PointsGoodsCategory;
import com.taotao.cloud.promotion.biz.service.PointsGoodsCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理端,积分商品分类接口
 *
 * @since 2021/1/14
 */
@RestController
@Tag(name = "管理端,积分商品分类接口")
@RequestMapping("/manager/promotion/pointsGoodsCategory")
public class PointsGoodsCategoryManagerController {

	@Autowired
	private PointsGoodsCategoryService pointsGoodsCategoryService;

	@RequestLogger
	@PreAuthorize("hasAuthority('sys:resource:info:roleId')")
	@PostMapping
	@Operation(summary = "添加积分商品分类")
	public Result<Object> add(PointsGoodsCategoryVO pointsGoodsCategory) {
		pointsGoodsCategoryService.addCategory(pointsGoodsCategory);
		return Result.success();
	}

	@RequestLogger
	@PreAuthorize("hasAuthority('sys:resource:info:roleId')")
	@PutMapping
	@Operation(summary = "修改积分商品分类")
	public Result<Object> update(PointsGoodsCategoryVO pointsGoodsCategory) {
		pointsGoodsCategoryService.updateCategory(pointsGoodsCategory);
		return Result.success();
	}

	@RequestLogger
	@PreAuthorize("hasAuthority('sys:resource:info:roleId')")
	@DeleteMapping("/{id}")
	@Operation(summary = "删除积分商品分类")
	public Result<Object> delete(@PathVariable String id) {
		pointsGoodsCategoryService.deleteCategory(id);
		return Result.success();
	}

	@RequestLogger
	@PreAuthorize("hasAuthority('sys:resource:info:roleId')")
	@GetMapping
	@Operation(summary = "获取积分商品分类分页")
	public Result<IPage<PointsGoodsCategory>> page(String name, PageVO page) {
		return Result.success(pointsGoodsCategoryService.getCategoryByPage(name, page));
	}

	@RequestLogger
	@PreAuthorize("hasAuthority('sys:resource:info:roleId')")
	@GetMapping("/{id}")
	@Operation(summary = "修改积分商品分类")
	public Result<Object> getById(@PathVariable String id) {
		return Result.success(pointsGoodsCategoryService.getCategoryDetail(id));
	}

}
