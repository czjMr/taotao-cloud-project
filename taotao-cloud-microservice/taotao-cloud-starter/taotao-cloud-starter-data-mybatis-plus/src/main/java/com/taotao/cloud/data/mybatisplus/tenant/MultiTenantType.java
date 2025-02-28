package com.taotao.cloud.data.mybatisplus.tenant;


import org.openjdk.nashorn.internal.objects.annotations.Getter;

/**
 * 多租户类型
 * <p>
 * NONE、COLUMN、SCHEMA 模式开源
 * <p>
 * DATASOURCE 模式收费，购买咨询作者
 *
 */
public enum MultiTenantType {
    /**
     * 非租户模式
     */
    NONE("非租户模式"),
    /**
     * 字段模式
     * 在sql中拼接 tenant_code 字段
     */
    COLUMN("字段模式"),
    /**
     * 独立schema模式
     * 在sql中拼接 数据库 schema
     * <p>
     * 该模式暂不支持复杂sql、存储过程、函数等，欢迎大家提供解决方案。
     */
    SCHEMA("独立schema模式"),
    /**
     * 独立数据源模式
     * <p>
     * 该模式不开源，购买咨询作者。
     */
    DATASOURCE("独立数据源模式"),
    /**
     * 数据源 + 字段 混合模式
     * <p>
     * 该模式不开源，购买咨询作者。
     */
    DATASOURCE_COLUMN("数据源&字段混合模式"),
    /**
     * SCHEMA + 字段 混合模式
     */
    SCHEMA_COLUMN("SCHEMA&字段混合模式"),
    ;
    private String describe;

	public String getDescribe() {
		return describe;
	}

	MultiTenantType(String describe) {
        this.describe = describe;
    }

    public boolean eq(String val) {
        return this.name().equalsIgnoreCase(val);
    }

    public boolean eq(MultiTenantType val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }
}
