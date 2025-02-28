package com.taotao.cloud.stock.biz.infrastructure.persistence.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色DO
 *
 * @author shuigedeng
 * @date 2021-02-15
 */
@Data
@TableName("sys_role")
public class SysRoleDO extends BaseDO {

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String remarks;
}
