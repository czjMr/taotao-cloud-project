package com.taotao.cloud.payment.biz.kit.plugin.bank;

import com.taotao.cloud.common.enums.ClientTypeEnum;
import com.taotao.cloud.common.utils.log.LogUtils;
import com.taotao.cloud.payment.api.enums.PaymentMethodEnum;
import com.taotao.cloud.payment.biz.entity.RefundLog;
import com.taotao.cloud.payment.biz.kit.Payment;
import com.taotao.cloud.payment.biz.kit.dto.PayParam;
import com.taotao.cloud.payment.biz.kit.dto.PaymentSuccessParams;
import com.taotao.cloud.payment.biz.kit.params.dto.CashierParam;
import com.taotao.cloud.payment.biz.service.PaymentService;
import com.taotao.cloud.payment.biz.service.RefundLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 线下收款
 */

@Component
public class BankTransferPlugin implements Payment {
    /**
     * 退款日志
     */
    @Autowired
    private RefundLogService refundLogService;
    /**
     * 支付日志
     */
    @Autowired
    private PaymentService paymentService;

    @Override
    public void refund(RefundLog refundLog) {
        try {
            refundLog.setIsRefund(true);
            refundLogService.save(refundLog);
        } catch (Exception e) {
			LogUtils.error("线下收款错误",e);
        }
    }

    /**
     * 支付订单
     *
     * @param order 订单
     */
    public void callBack(Order order) {

        //收银参数
        CashierParam cashierParam = new CashierParam();
        cashierParam.setPrice(order.getFlowPrice());
        //支付参数
        PayParam payParam = new PayParam();
        payParam.setOrderType("ORDER");
        payParam.setSn(order.getSn());
        payParam.setClientType(ClientTypeEnum.PC.name());

        PaymentSuccessParams paymentSuccessParams = new PaymentSuccessParams(
                PaymentMethodEnum.BANK_TRANSFER.name(),
                "",
                order.getFlowPrice(),
                payParam
        );

        //记录支付日志
        paymentService.adminPaySuccess(paymentSuccessParams);
        log.info("支付回调通知：线上支付：{}", payParam);
    }

}
