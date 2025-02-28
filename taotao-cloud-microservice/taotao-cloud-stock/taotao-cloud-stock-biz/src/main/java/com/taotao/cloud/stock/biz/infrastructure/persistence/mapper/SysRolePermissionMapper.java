package com.taotao.cloud.stock.biz.infrastructure.persistence.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色权限关联Mapper
 *
 * @author shuigedeng
 * @date 2021-02-14
 */
@Mapper
public interface SysRolePermissionMapper extends BaseSuperMapper<SysRolePermissionDO> {

	/**
	 * 根据角色ID，批量删除
	 */
	int deleteByRoleIds(List<String> roleIds);

	/**
	 * 根据权限ID，批量删除
	 */
	int deleteByPermissionIds(List<String> permissionIds);
}
