package com.taotao.cloud.message.biz.controller.manager;

import cn.hutool.core.util.PageUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.cloud.common.constant.CommonConstant;
import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.logger.annotation.RequestLogger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 管理端,服务订阅消息接口
 */
@Validated
@RestController
@Tag(name = "平台管理端-服务订阅消息API", description = "平台管理端-服务订阅消息API")
@RequestMapping("/message/manager/serviceNotice")
public class ServiceNoticeManagerController {

	@Autowired
	private ServiceNoticeService serviceNoticeService;

	@Operation(summary = "查看服务订阅消息详情", description = "查看服务订阅消息详情")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/{id}")
	public Result<ServiceNotice> get(@PathVariable String id) {
		ServiceNotice serviceNotice = serviceNoticeService.getById(id);
		return Result.success(serviceNotice);
	}

	@Operation(summary = "分页获取服务订阅消息", description = "分页获取服务订阅消息")
	@RequestLogger("分页获取服务订阅消息")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/page")
	public Result<IPage<ServiceNotice>> getByPage(ServiceNotice entity,
		SearchVO searchVo, PageVO page) {
		IPage<ServiceNotice> data = serviceNoticeService.page(
			PageUtil.initPage(page), PageUtil.initWrapper(entity, searchVo));
		return Result.success(data);
	}

	@Operation(summary = "新增服务订阅消息", description = "新增服务订阅消息")
	@RequestLogger("新增服务订阅消息")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping
	public Result<ServiceNotice> save(ServiceNotice serviceNotice) {
		//标记平台消息
		serviceNotice.setStoreId("-1");
		serviceNoticeService.saveOrUpdate(serviceNotice);
		return Result.success(serviceNotice);
	}

	@Operation(summary = "更新服务订阅消息", description = "更新服务订阅消息")
	@RequestLogger("更新服务订阅消息")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PutMapping("/{id}")
	public Result<ServiceNotice> update(@PathVariable String id,
		ServiceNotice serviceNotice) {
		serviceNoticeService.saveOrUpdate(serviceNotice);
		return Result.success(serviceNotice);
	}

	@Operation(summary = "删除服务订阅消息", description = "删除服务订阅消息")
	@RequestLogger("删除服务订阅消息")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@DeleteMapping(value = "/{ids}")
	public Result<Object> delAllByIds(@PathVariable List ids) {
		serviceNoticeService.removeByIds(ids);
		return Result.success();
	}
}
