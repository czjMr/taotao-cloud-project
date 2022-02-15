/**
 * Copyright (C) 2018-2020 All rights reserved, Designed By www.yixiang.co 注意：
 * 本软件为www.yixiang.co开发研制
 */
package com.taotao.cloud.sys.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.cloud.sys.api.vo.alipay.TradeVo;
import com.taotao.cloud.sys.biz.entity.AlipayConfig;
import com.taotao.cloud.sys.biz.mapper.IAlipayConfigMapper;
import com.taotao.cloud.sys.biz.service.IAlipayConfigService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;


@Service
//@CacheConfig(cacheNames = "alipayConfig")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AlipayConfigServiceImpl extends
	ServiceImpl<IAlipayConfigMapper, AlipayConfig> implements IAlipayConfigService {


	@Override
	public String toPayAsPc(AlipayConfig alipay, TradeVo trade) throws Exception {

		//if (alipay.getId() == null) {
		//    throw new BadRequestException("请先添加相应配置，再操作");
		//}
		//
		//AlipayClient alipayClient = new DefaultAlipayClient(alipay.getGatewayUrl(), alipay.getAppId(), alipay.getPrivateKey(), alipay.getFormat(), alipay.getCharset(), alipay.getPublicKey(), alipay.getSignType());
		//
		//// 创建API对应的request(电脑网页版)
		//AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
		//
		//// 订单完成后返回的页面和异步通知地址
		//request.setReturnUrl(alipay.getReturnUrl());
		//request.setNotifyUrl(alipay.getNotifyUrl());
		//// 填充订单参数
		//request.setBizContent("{" +
		//        "    \"out_trade_no\":\"" + trade.getOutTradeNo() + "\"," +
		//        "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
		//        "    \"total_amount\":" + trade.getTotalAmount() + "," +
		//        "    \"subject\":\"" + trade.getSubject() + "\"," +
		//        "    \"body\":\"" + trade.getBody() + "\"," +
		//        "    \"extend_params\":{" +
		//        "    \"sys_service_provider_id\":\"" + alipay.getSysServiceProviderId() + "\"" +
		//        "    }" +
		//        "  }");//填充业务参数
		//// 调用SDK生成表单, 通过GET方式，口可以获取url
		//return alipayClient.pageExecute(request, "GET").getBody();
		return "";

	}

	@Override
	public String toPayAsWeb(AlipayConfig alipay, TradeVo trade) throws Exception {
		//if (alipay.getId() == null) {
		//    throw new BadRequestException("请先添加相应配置，再操作");
		//}
		//AlipayClient alipayClient = new DefaultAlipayClient(alipay.getGatewayUrl(), alipay.getAppId(), alipay.getPrivateKey(), alipay.getFormat(), alipay.getCharset(), alipay.getPublicKey(), alipay.getSignType());
		//
		//double money = Double.parseDouble(trade.getTotalAmount());
		//double maxMoney = 5000;
		//if (money <= 0 || money >= maxMoney) {
		//    throw new BadRequestException("测试金额过大");
		//}
		//// 创建API对应的request(手机网页版)
		//AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
		//request.setReturnUrl(alipay.getReturnUrl());
		//request.setNotifyUrl(alipay.getNotifyUrl());
		//request.setBizContent("{" +
		//        "    \"out_trade_no\":\"" + trade.getOutTradeNo() + "\"," +
		//        "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
		//        "    \"total_amount\":" + trade.getTotalAmount() + "," +
		//        "    \"subject\":\"" + trade.getSubject() + "\"," +
		//        "    \"body\":\"" + trade.getBody() + "\"," +
		//        "    \"extend_params\":{" +
		//        "    \"sys_service_provider_id\":\"" + alipay.getSysServiceProviderId() + "\"" +
		//        "    }" +
		//        "  }");
		//return alipayClient.pageExecute(request, "GET").getBody();
		return "";
	}

	@Override
//    @Cacheable(key = "'1'")
	public AlipayConfig find() {
		AlipayConfig alipayConfig = this.list().get(0);
		return alipayConfig;
	}

	@Override
//    @CachePut(key = "'1'")
	@Transactional(rollbackFor = Exception.class)
	public void update(AlipayConfig alipayConfig) {
		this.save(alipayConfig);
	}
}
