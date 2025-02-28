package com.taotao.cloud.order.biz.aop.order;

import java.lang.annotation.*;

/**
 * 订单日志注解
 *
 * @OrderLogPoint(description = "'订单['+#orderSn+']修改价格，修改后价格为['+#orderPrice+']'", orderSn = "#orderSn")
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-07 20:29:25
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OrderLogPoint {

    /**
     * 日志名称
     */
    String description();

    /**
     * 订单编号
     */
    String orderSn();

}
