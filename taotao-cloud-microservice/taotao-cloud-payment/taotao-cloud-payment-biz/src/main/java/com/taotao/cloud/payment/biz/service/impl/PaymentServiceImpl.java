package com.taotao.cloud.payment.biz.service.impl;

import com.taotao.cloud.common.utils.log.LogUtils;
import com.taotao.cloud.payment.biz.kit.CashierSupport;
import com.taotao.cloud.payment.biz.kit.dto.PaymentSuccessParams;
import com.taotao.cloud.payment.biz.kit.params.CashierExecute;
import com.taotao.cloud.payment.biz.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 支付日志 业务实现
 */

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private List<CashierExecute> cashierExecutes;
    @Autowired
    private CashierSupport cashierSupport;

    @Override
    public void success(PaymentSuccessParams paymentSuccessParams) {

        boolean paymentResult = cashierSupport.paymentResult(paymentSuccessParams.getPayParam());
        if (paymentResult) {
            LogUtils.info("订单支付状态后，调用支付成功接口，流水号：{}", paymentSuccessParams.getReceivableNo());
            return;
        }

		LogUtils.info("支付成功，第三方流水：{}", paymentSuccessParams.getReceivableNo());
        //支付结果处理
        for (CashierExecute cashierExecute : cashierExecutes) {
            cashierExecute.paymentSuccess(paymentSuccessParams);
        }
    }

    @Override
    public void adminPaySuccess(PaymentSuccessParams paymentSuccessParams) {
		LogUtils.info("支付状态修改成功->银行转账");
        //支付结果处理
        for (CashierExecute cashierExecute : cashierExecutes) {
            cashierExecute.paymentSuccess(paymentSuccessParams);
        }
    }
}
