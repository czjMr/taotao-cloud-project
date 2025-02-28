package com.taotao.cloud.payment.biz.bootx.core.paymodel.alipay.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taotao.cloud.payment.biz.bootx.core.paymodel.alipay.entity.AliPayment;
import org.apache.ibatis.annotations.Mapper;

/**   
* 支付宝支付
* @author xxm  
* @date 2021/2/26 
*/
@Mapper
public interface AliPaymentMapper extends BaseSuperMapper<AliPayment> {
}
