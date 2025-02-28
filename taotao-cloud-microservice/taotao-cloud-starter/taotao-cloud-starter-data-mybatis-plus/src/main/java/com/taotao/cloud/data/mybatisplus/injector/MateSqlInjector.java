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

package com.taotao.cloud.data.mybatisplus.injector;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import com.taotao.cloud.data.mybatisplus.injector.methods.InsertBatch;
import com.taotao.cloud.data.mybatisplus.injector.methods.InsertIgnore;
import com.taotao.cloud.data.mybatisplus.injector.methods.InsertIgnoreBatch;
import com.taotao.cloud.data.mybatisplus.injector.methods.Replace;
import com.taotao.cloud.data.mybatisplus.injector.methods.ReplaceBatch;
import com.taotao.cloud.data.mybatisplus.injector.methods.UpdateAllById;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * 自定义的 sql 注入 
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-04 07:44:06
 */
public class MateSqlInjector extends DefaultSqlInjector {

	@Override
	public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
		List<AbstractMethod> methodList = new ArrayList<>();
		methodList.add(new InsertBatch());
		methodList.add(new InsertIgnore());
		methodList.add(new InsertIgnoreBatch());
		methodList.add(new Replace());
		methodList.add(new ReplaceBatch());
		methodList.addAll(super.getMethodList(mapperClass, tableInfo));

		//增加自定义方法
		methodList.add(new InsertBatchSomeColumn(i -> i.getFieldFill() != FieldFill.UPDATE));
		methodList.add(new UpdateAllById(field -> !ArrayUtil.containsAny(new String[]{
			"create_time", "created_by"
		}, field.getColumn())));

		//methodList.add(new UpdateBatchMethod());

		return Collections.unmodifiableList(methodList);
	}
}
