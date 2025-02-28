package com.taotao.cloud.pay.configuration;

import com.taotao.cloud.common.constant.StarterName;
import com.taotao.cloud.common.utils.log.LogUtils;
import com.taotao.cloud.pay.configurers.DefalutPayMessageConfigurer;
import com.taotao.cloud.pay.configurers.MerchantDetailsServiceConfigurer;
import com.taotao.cloud.pay.configurers.PayMessageConfigurer;
import com.taotao.cloud.pay.merchant.MerchantDetailsService;
import com.taotao.cloud.pay.merchant.PaymentPlatform;
import com.taotao.cloud.pay.model.MerchantPayServiceManager;
import com.taotao.cloud.pay.model.PayServiceConfigurer;
import com.taotao.cloud.pay.model.PayServiceManager;
import com.taotao.cloud.pay.properties.PayProperties;
import com.taotao.cloud.pay.provider.PaymentPlatforms;
import java.util.List;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

/**
 * 支付转载配置
 */
//@ImportAutoConfiguration({
//	AliPaymentPlatform.class,
//	FuiouPaymentPlatform.class,
//	PayoneerPaymentPlatform.class,
//	PaypalPaymentPlatform.class,
//	UnionPaymentPlatform.class,
//	WxPaymentPlatform.class,
//	YoudianPaymentPlatform.class
//})
@AutoConfiguration
@ConditionalOnProperty(prefix = PayProperties.PREFIX, name = "enabled", havingValue = "true")
public class PayAutoConfiguration implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		LogUtils.started(PayAutoConfiguration.class, StarterName.PAY_STARTER);
	}

	@Autowired
	public void loadPaymentPlatforms(List<PaymentPlatform> platforms) {
		LogUtils.started(PaymentPlatform.class, StarterName.PAY_STARTER);
		for (PaymentPlatform platform : platforms) {
			PaymentPlatforms.loadPaymentPlatform(platform);
		}
	}

	@Bean
	@ConditionalOnMissingBean(MerchantDetailsServiceConfigurer.class)
	@ConditionalOnBean(PayServiceConfigurer.class)
	public MerchantDetailsServiceConfigurer detailsServiceConfigurer() {
		LogUtils.started(MerchantDetailsServiceConfigurer.class, StarterName.PAY_STARTER);

		return new MerchantDetailsServiceConfigurer();
	}

	@Bean
	@ConditionalOnMissingBean(MerchantDetailsService.class)
	@ConditionalOnBean(PayServiceConfigurer.class)
	protected MerchantDetailsService configure(PayServiceConfigurer configurer,
		MerchantDetailsServiceConfigurer merchantDetails,
		PayMessageConfigurer payMessageConfigurer) {
		LogUtils.started(MerchantDetailsService.class, StarterName.PAY_STARTER);

		configurer.configure(merchantDetails);
		configurer.configure(payMessageConfigurer);
		MerchantDetailsService detailsService = merchantDetails.getBuilder().build();
		return detailsService;
	}

	@Bean
	@Order
	@ConditionalOnBean(MerchantDetailsService.class)
	@ConditionalOnMissingBean(PayServiceManager.class)
	public PayServiceManager payServiceManager() {
		LogUtils.started(PayServiceManager.class, StarterName.PAY_STARTER);

		return new MerchantPayServiceManager();
	}


	@Bean
	@ConditionalOnMissingBean(PayMessageConfigurer.class)
	public PayMessageConfigurer messageHandlerConfigurer() {
		LogUtils.started(PayMessageConfigurer.class, StarterName.PAY_STARTER);

		return new DefalutPayMessageConfigurer();
	}


}
