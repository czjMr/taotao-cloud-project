//package com.taotao.cloud.stream.consumer.event.impl;
//
//import cn.hutool.json.JSONUtil;
//import java.util.ArrayList;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// * 订单创建发票相关处理
// *
// */
//@Service
//public class OrderCreateReceiptExecute implements TradeEvent {
//
//    @Autowired
//    private ReceiptService receiptService;
//
//    @Override
//    public void orderCreate(TradeDTO tradeDTO) {
//        //根据交易sn查询订单信息
//        List<OrderVO> orderList = tradeDTO.getOrderVO();
//        //获取发票信息
//        ReceiptVO receiptVO = tradeDTO.getReceiptVO();
//        //如果需要获取发票则保存发票信息
//        if (Boolean.TRUE.equals(tradeDTO.getNeedReceipt()) && !orderList.isEmpty()) {
//            List<Receipt> receipts = new ArrayList<>();
//            for (OrderVO orderVO : orderList) {
//                Receipt receipt = new Receipt();
//                BeanUtil.copyProperties(receiptVO, receipt);
//                receipt.setMemberId(orderVO.getMemberId());
//                receipt.setMemberName(orderVO.getMemberName());
//                receipt.setStoreId(orderVO.getStoreId());
//                receipt.setStoreName(orderVO.getStoreName());
//                receipt.setOrderSn(orderVO.getSn());
//                receipt.setReceiptDetail(JSONUtil.toJsonStr(orderVO.getOrderItems()));
//                receipt.setReceiptPrice(orderVO.getFlowPrice());
//                receipt.setReceiptStatus(0);
//                receipts.add(receipt);
//            }
//            //保存发票
//            receiptService.saveBatch(receipts);
//        }
//    }
//}
