package com.taotao.cloud.order.biz.service.purchase;


import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.cloud.order.biz.model.entity.purchase.PurchaseQuoted;

import java.util.List;

/**
 * 采购单报价业务层
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 08:55:34
 */
public interface IPurchaseQuotedService extends IService<PurchaseQuoted> {
	/**
	 * 添加报价单
	 *
	 * @param purchaseQuotedVO 报价单
	 * @return {@link PurchaseQuotedVO }
	 * @since 2022-04-28 08:55:34
	 */
	PurchaseQuotedVO addPurchaseQuoted(PurchaseQuotedVO purchaseQuotedVO);

	/**
	 * 根据采购单获取报价单列表
	 *
	 * @param purchaseOrderId 采购单ID
	 * @return {@link List }<{@link PurchaseQuoted }>
	 * @since 2022-04-28 08:55:34
	 */
	List<PurchaseQuoted> getByPurchaseOrderId(String purchaseOrderId);

	/**
	 * 获取采购单VO
	 *
	 * @param id 采购单ID
	 * @return {@link PurchaseQuotedVO }
	 * @since 2022-04-28 08:55:34
	 */
	PurchaseQuotedVO getById(String id);
}
