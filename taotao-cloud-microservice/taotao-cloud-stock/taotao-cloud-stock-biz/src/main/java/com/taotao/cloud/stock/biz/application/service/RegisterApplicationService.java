package com.taotao.cloud.stock.biz.application.service;


/**
 * 注册应用服务接口
 *
 * @author shuigedeng
 * @date 2021-06-23
 */
public interface RegisterApplicationService {

    /**
     * 注册租户
     *
     * @param registerTenantCommand
     */
    void registerTenant(RegisterTenantCommand registerTenantCommand);
}
