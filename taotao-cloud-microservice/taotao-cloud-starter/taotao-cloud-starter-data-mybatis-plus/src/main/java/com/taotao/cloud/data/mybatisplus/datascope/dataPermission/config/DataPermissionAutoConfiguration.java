package com.taotao.cloud.data.mybatisplus.datascope.dataPermission.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.taotao.cloud.data.mybatisplus.datascope.dataPermission.aop.DataPermissionAnnotationAdvisor;
import com.taotao.cloud.data.mybatisplus.datascope.dataPermission.db.DataPermissionDatabaseInterceptor;
import com.taotao.cloud.data.mybatisplus.datascope.dataPermission.factory.DataPermissionRuleFactory;
import com.taotao.cloud.data.mybatisplus.datascope.dataPermission.factory.DataPermissionRuleFactoryImpl;
import com.taotao.cloud.data.mybatisplus.datascope.dataPermission.rule.DataPermissionRule;
import com.taotao.cloud.data.mybatisplus.utils.MpUtils;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * 数据权限的自动配置类
 */
@AutoConfiguration
public class DataPermissionAutoConfiguration {

	/**
	 * 数据权限规则工厂 管理数据权限规则
	 *
	 * @param rules 容器中的数据权限规则类
	 */
	@Bean
	public DataPermissionRuleFactory dataPermissionRuleFactory(List<DataPermissionRule> rules) {
		return new DataPermissionRuleFactoryImpl(rules);
	}

	/**
	 * 配置拦截器 重写sql
	 */
	@Bean
	public DataPermissionDatabaseInterceptor dataPermissionDatabaseInterceptor(MybatisPlusInterceptor interceptor,
																			   List<DataPermissionRule> rules) {
		// 数据权限规则工厂接口
		DataPermissionRuleFactory ruleFactory = dataPermissionRuleFactory(rules);

		// 创建 DataPermissionDatabaseInterceptor 拦截器
		DataPermissionDatabaseInterceptor inner = new DataPermissionDatabaseInterceptor(ruleFactory);

		// 需要加在分页插件前面
		MpUtils.addInterceptor(interceptor, inner, 0);
		return inner;
	}

	/**
	 * aop处理
	 */
	@Bean
	public DataPermissionAnnotationAdvisor dataPermissionAnnotationAdvisor() {
		return new DataPermissionAnnotationAdvisor();
	}

}
