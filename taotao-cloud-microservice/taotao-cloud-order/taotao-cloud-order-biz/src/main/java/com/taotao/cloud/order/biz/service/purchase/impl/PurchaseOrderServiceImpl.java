package com.taotao.cloud.order.biz.service.purchase.impl;

import cn.hutool.core.util.PageUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.cloud.order.biz.model.entity.purchase.PurchaseOrder;
import com.taotao.cloud.order.biz.model.entity.purchase.PurchaseOrderItem;
import com.taotao.cloud.order.biz.mapper.purchase.IPurchaseOrderMapper;
import com.taotao.cloud.order.biz.service.purchase.IPurchaseOrderItemService;
import com.taotao.cloud.order.biz.service.purchase.IPurchaseOrderService;
import org.apache.shardingsphere.distsql.parser.autogen.CommonDistSQLStatementParser.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 采购单业务层实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 08:55:40
 */
@Service
public class PurchaseOrderServiceImpl extends ServiceImpl<IPurchaseOrderMapper, PurchaseOrder> implements
	IPurchaseOrderService {
    @Autowired
    private IPurchaseOrderItemService purchaseOrderItemService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PurchaseOrderVO addPurchaseOrder(PurchaseOrderVO purchaseOrderVO) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        BeanUtil.copyProperties(purchaseOrderVO, purchaseOrder);
        //添加采购单
        purchaseOrder.setStatus("OPEN");
        purchaseOrder.setMemberId(UserContext.getCurrentUser().getId());
        this.save(purchaseOrder);
        //添加采购单子内容
        purchaseOrderItemService.addPurchaseOrderItem(purchaseOrder.getId(), purchaseOrderVO.getPurchaseOrderItems());
        return purchaseOrderVO;
    }

    @Override
    public PurchaseOrderVO getPurchaseOrder(String id) {
        PurchaseOrderVO purchaseOrderVO = new PurchaseOrderVO();
        //获取采购单内容
        PurchaseOrder purchaseOrder = this.getById(id);
        BeanUtil.copyProperties(purchaseOrder, purchaseOrderVO);

        //获取采购单子内容
        purchaseOrderVO.setPurchaseOrderItems(purchaseOrderItemService.list(
                new LambdaQueryWrapper<PurchaseOrderItem>().eq(PurchaseOrderItem::getPurchaseOrderId,id)));
        return purchaseOrderVO;
    }

    @Override
    public IPage<PurchaseOrder> page(PurchaseOrderSearchParams purchaseOrderSearchParams) {

        LambdaQueryWrapper<PurchaseOrder> lambdaQueryWrapper = Wrappers.lambdaQuery();

        lambdaQueryWrapper.eq(purchaseOrderSearchParams.getMemberId() != null,
                PurchaseOrder::getMemberId, purchaseOrderSearchParams.getMemberId());
        lambdaQueryWrapper.eq(purchaseOrderSearchParams.getCategoryId() != null,
                PurchaseOrder::getCategoryId, purchaseOrderSearchParams.getCategoryId());
        lambdaQueryWrapper.eq(purchaseOrderSearchParams.getStatus() != null,
                PurchaseOrder::getStatus, purchaseOrderSearchParams.getStatus());
        lambdaQueryWrapper.orderByDesc(PurchaseOrder::getCreateTime);
        return this.page(PageUtil.initPage(purchaseOrderSearchParams), lambdaQueryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean close(String id) {
        PurchaseOrder purchaseOrder = this.getById(id);
        purchaseOrder.setStatus("CLOSE");

        UpdateWrapper<PurchaseOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("status", "CLOSE");

        return this.update(updateWrapper);
    }
}
