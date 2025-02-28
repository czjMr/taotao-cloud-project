package com.taotao.cloud.threadpool.configuration;

import com.dtp.core.spring.EnableDynamicTp;
import com.taotao.cloud.common.constant.StarterName;
import com.taotao.cloud.common.utils.log.LogUtils;
import com.taotao.cloud.threadpool.properties.ThreadPoolProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * DynamicTpAutoConfiguration
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2022-02-25 09:41:50
 */
@AutoConfiguration
@EnableDynamicTp
@ConditionalOnProperty(prefix = ThreadPoolProperties.PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties(ThreadPoolProperties.class)
//@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:dynamic-tp.yml")
public class DynamicTpAutoConfiguration implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		LogUtils.started(DynamicTpAutoConfiguration.class, StarterName.THREADPOOL_STARTER);
	}

	//@Bean
	//public DtpExecutor demo1Executor() {
	//	return DtpCreator.createDynamicFast("demo1-executor");
	//}
	//
	//@Bean
	//public ThreadPoolExecutor demo2Executor() {
	//	return ThreadPoolBuilder.newBuilder()
	//		.threadPoolName("demo2-executor")
	//		.corePoolSize(8)
	//		.maximumPoolSize(16)
	//		.keepAliveTime(50)
	//		.allowCoreThreadTimeOut(true)
	//		.workQueue(QueueTypeEnum.SYNCHRONOUS_QUEUE.getName(), null, false)
	//		.rejectedExecutionHandler(RejectedTypeEnum.CALLER_RUNS_POLICY.getName())
	//		.buildDynamic();
	//}

	//public static void main(String[] args) {
	//	DtpExecutor dtpExecutor = DtpRegistry.getExecutor("dynamic-tp-test-1");
	//	dtpExecutor.execute(() -> System.out.println("test"));
	//}

}
