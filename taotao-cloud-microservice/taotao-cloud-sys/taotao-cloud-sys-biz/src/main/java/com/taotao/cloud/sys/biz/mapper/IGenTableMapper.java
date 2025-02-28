package com.taotao.cloud.sys.biz.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taotao.cloud.sys.biz.model.entity.gen.GenTable;
import com.taotao.cloud.web.base.mapper.BaseSuperMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 业务 数据层
 */
@InterceptorIgnore(dataPermission = "true")
public interface IGenTableMapper extends BaseSuperMapper<GenTable, Long> {

	Page<GenTable> selectPageDbTableList(@Param("page") IPage<GenTable> page, @Param(Constants.WRAPPER) Wrapper<Object> queryWrapper);

	/**
	 * 查询据库列表
	 *
	 * @param queryWrapper 查询条件
	 * @return 数据库表集合
	 */
	List<GenTable> selectDbTableList(@Param(Constants.WRAPPER) Wrapper<Object> queryWrapper);


	/**
	 * 查询据库列表
	 *
	 * @param tableNames 表名称组
	 * @return 数据库表集合
	 */
	List<GenTable> selectDbTableListByNames(String[] tableNames);

	/**
	 * 查询所有表信息
	 *
	 * @return 表信息集合
	 */
	List<GenTable> selectGenTableAll();

	/**
	 * 查询表ID业务信息
	 *
	 * @param id 业务ID
	 * @return 业务信息
	 */
	GenTable selectGenTableById(Long id);

	/**
	 * 查询表名称业务信息
	 *
	 * @param tableName 表名称
	 * @return 业务信息
	 */
	GenTable selectGenTableByName(String tableName);

}
