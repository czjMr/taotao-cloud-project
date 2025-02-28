/*
 * Copyright (c) 2020-2030, Shuigedeng (981376577@qq.com & https://blog.taotaocloud.top/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.taotao.cloud.data.mybatisplus.injector.methods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.springframework.util.StringUtils;

/**
 * AbstractInsertBatch
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-04 07:41:09
 */
public class AbstractInsertBatch extends AbstractMethod {

	private final String sqlTemp;
	private final String sqlMethod;

	public AbstractInsertBatch(String sqlTemp, String sqlMethod) {
		super((sqlMethod));

		this.sqlTemp = sqlTemp;
		this.sqlMethod = sqlMethod;
	}

	@Override
	public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass,
		TableInfo tableInfo) {
		KeyGenerator keyGenerator = new NoKeyGenerator();
		String keyProperty = null;
		String keyColumn = null;
		// 表包含主键处理逻辑,如果不包含主键当普通字段处理
		if (StringUtils.hasText(tableInfo.getKeyProperty())) {
			if (tableInfo.getIdType() == IdType.AUTO)
			//自增主键
			{
				keyGenerator = new Jdbc3KeyGenerator();
			}
			keyProperty = tableInfo.getKeyProperty();
			keyColumn = tableInfo.getKeyColumn();
		} else {
			if (null != tableInfo.getKeySequence()) {
				keyGenerator = TableInfoHelper.genKeyGenerator(sqlMethod, tableInfo,
					this.builderAssistant);
				keyProperty = tableInfo.getKeyProperty();
				keyColumn = tableInfo.getKeyColumn();
			}
		}
		// 所以字段
		String columnScript = SqlScriptUtils.convertTrim(getAllInsertSqlColumnMaybeIf(tableInfo),
			LEFT_BRACKET, RIGHT_BRACKET, null, COMMA);
		// 单条 sql 的脚本
		String valuesScript = SqlScriptUtils.convertTrim(getAllInsertSqlPropertyMaybeIf(tableInfo),
			LEFT_BRACKET, RIGHT_BRACKET, null, COMMA);
		// 列表 sql
		String valuesScripts = SqlScriptUtils.convertForeach(valuesScript, COLLECTION, null, ENTITY,
			COMMA);
		String sql = String.format(sqlTemp, tableInfo.getTableName(), columnScript, valuesScripts);
		SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
		return this.addInsertMappedStatement(mapperClass, modelClass, sqlMethod, sqlSource,
			keyGenerator, keyProperty, keyColumn);
	}

	/**
	 * 获取 insert 时候字段 sql 脚本片段
	 * <p>insert into table (字段) values (值)</p>
	 * <p>位于 "字段" 部位</p>
	 *
	 * <li> 自动选部位,根据规则会生成 if 标签 </li>
	 *
	 * @return sql 脚本片段
	 */
	private static String getAllInsertSqlColumnMaybeIf(TableInfo tableInfo) {
		List<TableFieldInfo> fieldList = tableInfo.getFieldList();
		return tableInfo.getKeyInsertSqlColumn(true, true) + fieldList.stream()
			.map(AbstractInsertBatch::getInsertSqlColumnMaybeIf)
			.collect(Collectors.joining(NEWLINE));
	}

	/**
	 * 获取 insert 时候字段 sql 脚本片段
	 * <p>insert into table (字段) values (值)</p>
	 * <p>位于 "字段" 部位</p>
	 *
	 * <li> 根据规则会生成 if 标签 </li>
	 *
	 * @return sql 脚本片段
	 */
	private static String getInsertSqlColumnMaybeIf(TableFieldInfo tableFieldInfo) {
		return tableFieldInfo.getInsertSqlColumn();
	}

	/**
	 * 获取所有 insert 时候插入值 sql 脚本片段
	 * <p>insert into table (字段) values (值)</p>
	 * <p>位于 "值" 部位</p>
	 *
	 * <li> 自动选部位,根据规则会生成 if 标签 </li>
	 *
	 * @return sql 脚本片段
	 */
	private static String getAllInsertSqlPropertyMaybeIf(final TableInfo tableInfo) {
		List<TableFieldInfo> fieldList = tableInfo.getFieldList();
		return tableInfo.getKeyInsertSqlProperty(true, ENTITY_DOT, true) + fieldList.stream()
			.map(AbstractInsertBatch::getInsertSqlPropertyMaybeIf)
			.collect(Collectors.joining(NEWLINE));
	}

	/**
	 * 获取 insert 时候插入值 sql 脚本片段
	 * <p>insert into table (字段) values (值)</p>
	 * <p>位于 "值" 部位</p>
	 *
	 * <li> 根据规则会生成 if 标签 </li>
	 *
	 * @return sql 脚本片段
	 */
	private static String getInsertSqlPropertyMaybeIf(TableFieldInfo tableFieldInfo) {
		return tableFieldInfo.getInsertSqlProperty(ENTITY_DOT);
	}
}
