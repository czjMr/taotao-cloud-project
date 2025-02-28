package com.taotao.cloud.stock.biz.interfaces.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 系统日志Controller
 *
 * @author shuigedeng
 * @date 2021-02-04
 */
@Api(tags = "日志管理")
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogQueryService logQueryService;

    /**
     * 列表
     */
    @ApiOperation("分页查询日志")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('sys:log:list')")
    public Result list(@RequestParam Map<String, Object> params) {
        Page page = logQueryService.queryPage(params);
        return Result.ok().put(PageConstant.PAGE, page);
    }
}
