package com.taotao.cloud.operation.biz.api.controller.manger;

import cn.hutool.core.util.PageUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.logger.annotation.RequestLogger;
import com.taotao.cloud.operation.biz.model.entity.Special;
import com.taotao.cloud.operation.biz.service.SpecialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
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
 * 管理端,专题活动接口
 */
@RestController
@Tag(name = "管理端,专题活动接口")
@RequestMapping("/manager/order/special")
public class SpecialManagerController {

	@Autowired
	private SpecialService specialService;

	@RequestLogger
	@PreAuthorize("hasAuthority('sys:resource:info:roleId')")
	@Operation(summary = "添加专题活动")
	@PostMapping("/addSpecial")
	public Result<Special> addSpecial(@Valid Special special) {
		return Result.success(specialService.addSpecial(special));
	}

	@RequestLogger
	@PreAuthorize("hasAuthority('sys:resource:info:roleId')")
	@Operation(summary = "修改专题活动")
	@PutMapping("/updateSpecial")
	public Result<Special> updateSpecial(@Parameter(description = "专题ID") @PathVariable Long id,
		@Valid Special special) {
		special.setId(id);
		specialService.updateById(special);
		return Result.success(special);
	}

	@RequestLogger
	@PreAuthorize("hasAuthority('sys:resource:info:roleId')")
	@Operation(summary = "删除专题活动")
	@DeleteMapping("/{id}")
	public Result<Boolean> deleteSpecial(@Parameter(description = "专题ID") @PathVariable String id) {
		specialService.removeSpecial(id);
		return Result.success(true);
	}

	@RequestLogger
	@PreAuthorize("hasAuthority('sys:resource:info:roleId')")
	@Operation(summary = "分页获取专题活动")
	@GetMapping
	public Result<IPage<Special>> getSpecials(PageVO pageVo) {
		return Result.success(specialService.page(PageUtil.initPage(pageVo)));
	}

	@RequestLogger
	@PreAuthorize("hasAuthority('sys:resource:info:roleId')")
	@Operation(summary = "获取专题活动列表")
	@GetMapping("/getSpecialsList")
	public Result<List<Special>> getSpecialsList() {
		return Result.success(specialService.list());
	}

	@RequestLogger
	@PreAuthorize("hasAuthority('sys:resource:info:roleId')")
	@Operation(summary = "获取专题活动")
	@GetMapping(value = "/{id}")
	public Result<Special> getSpecialsList(
		@Parameter(description = "专题ID", required = true) @PathVariable String id) {
		return Result.success(specialService.getById(id));
	}

}
