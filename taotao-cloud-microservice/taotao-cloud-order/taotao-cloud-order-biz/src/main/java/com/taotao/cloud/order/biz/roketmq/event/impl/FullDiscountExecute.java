package com.taotao.cloud.order.biz.roketmq.event.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.taotao.cloud.common.enums.CachePrefix;
import com.taotao.cloud.common.enums.UserEnum;
import com.taotao.cloud.common.utils.common.IdGeneratorUtil;
import com.taotao.cloud.common.utils.log.LogUtil;
import com.taotao.cloud.goods.api.enums.GoodsTypeEnum;
import com.taotao.cloud.goods.api.feign.IFeignGoodsSkuService;
import com.taotao.cloud.goods.api.vo.GoodsSkuVO;
import com.taotao.cloud.member.api.enums.PointTypeEnum;
import com.taotao.cloud.member.api.feign.IFeignMemberService;
import com.taotao.cloud.order.api.dto.cart.TradeDTO;
import com.taotao.cloud.order.api.message.OrderMessage;
import com.taotao.cloud.order.api.dto.order.PriceDetailDTO;
import com.taotao.cloud.order.api.enums.order.CommentStatusEnum;
import com.taotao.cloud.order.api.enums.order.OrderComplaintStatusEnum;
import com.taotao.cloud.order.api.enums.order.OrderItemAfterSaleStatusEnum;
import com.taotao.cloud.order.api.enums.order.OrderPromotionTypeEnum;
import com.taotao.cloud.order.api.enums.order.OrderStatusEnum;
import com.taotao.cloud.order.api.enums.order.OrderTypeEnum;
import com.taotao.cloud.order.api.enums.order.PayStatusEnum;
import com.taotao.cloud.order.api.vo.cart.CartVO;
import com.taotao.cloud.order.biz.entity.order.Order;
import com.taotao.cloud.order.biz.entity.order.OrderItem;
import com.taotao.cloud.order.biz.entity.order.OrderLog;
import com.taotao.cloud.order.biz.roketmq.event.OrderStatusChangeEvent;
import com.taotao.cloud.order.biz.roketmq.event.TradeEvent;
import com.taotao.cloud.order.biz.service.order.IOrderItemService;
import com.taotao.cloud.order.biz.service.order.IOrderService;
import com.taotao.cloud.order.biz.service.trade.IOrderLogService;
import com.taotao.cloud.promotion.api.feign.IFeignMemberCouponService;
import com.taotao.cloud.redis.repository.RedisRepository;
import com.taotao.cloud.stream.framework.rocketmq.RocketmqSendCallbackBuilder;
import com.taotao.cloud.stream.framework.rocketmq.tags.OrderTagsEnum;
import com.taotao.cloud.stream.properties.RocketmqCustomProperties;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单状态处理类
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-05-16 17:35:00
 */
@Service
public class FullDiscountExecute implements TradeEvent, OrderStatusChangeEvent {

	@Autowired
	private RedisRepository redisRepository;

	@Autowired
	private IFeignMemberService memberService;

	@Autowired
	private IOrderService orderService;

	@Autowired
	private IOrderItemService orderItemService;

	@Autowired
	private IOrderLogService orderLogService;

	@Autowired
	private IFeignMemberCouponService memberCouponService;

	@Autowired
	private IFeignGoodsSkuService goodsSkuService;

	@Autowired
	private RocketmqCustomProperties rocketmqCustomProperties;

	@Autowired
	private RocketMQTemplate rocketMQTemplate;

	@Override
	public void orderCreate(TradeDTO tradeDTO) {
		tradeDTO.getCartList().forEach(
			cartVO -> {
				//有满减优惠，则记录信息
				if ((cartVO.getGiftList() != null && !cartVO.getGiftList().isEmpty())
					|| (cartVO.getGiftPoint() != null && cartVO.getGiftPoint() > 0)
					|| (cartVO.getGiftCouponList() != null && !cartVO.getGiftCouponList().isEmpty())) {
					redisRepository.set(CachePrefix.ORDER.getPrefix() + cartVO.getSn(), JSONUtil.toJsonStr(cartVO));
				}
			}
		);
	}

	@Override
	public void orderChange(OrderMessage orderMessage) {
		//如果订单已支付
		if (orderMessage.getNewStatus().equals(OrderStatusEnum.PAID)) {
			LogUtil.info("满减活动，订单状态操作 {}", CachePrefix.ORDER.getPrefix() + orderMessage.getOrderSn());
			renderGift(JSONUtil.toBean(redisRepository.get(CachePrefix.ORDER.getPrefix() + orderMessage.getOrderSn()).toString(), CartVO.class), orderMessage);
		}
	}

	/**
	 * 渲染优惠券信息
	 *
	 * @param cartVO       购物车签证官
	 * @param orderMessage 订单消息
	 * @since 2022-05-16 17:35:11
	 */
	private void renderGift(CartVO cartVO, OrderMessage orderMessage) {
		//没有优惠信息则跳过
		if (cartVO == null) {
			return;
		}

		Order order = orderService.getBySn(orderMessage.getOrderSn());
		//赠送积分判定
		try {
			if (cartVO.getGiftPoint() != null && cartVO.getGiftPoint() > 0) {
				memberService.updateMemberPoint(cartVO.getGiftPoint().longValue(), PointTypeEnum.INCREASE.name(),
					order.getMemberId(), "订单满优惠赠送积分" + cartVO.getGiftPoint());
			}
		} catch (Exception e) {
			LogUtil.error("订单赠送积分异常", e);
		}


		try {
			//优惠券判定
			if (cartVO.getGiftCouponList() != null && !cartVO.getGiftCouponList().isEmpty()) {
				cartVO.getGiftCouponList().forEach(couponId -> memberCouponService.receiveCoupon(couponId, order.getMemberId(), order.getMemberName()));
			}
		} catch (Exception e) {
			LogUtil.error("订单赠送优惠券异常", e);
		}

		try {
			//赠品潘迪ing
			if (cartVO.getGiftList() != null && !cartVO.getGiftList().isEmpty()) {
				generatorGiftOrder(cartVO.getGiftList(), order);
			}
		} catch (Exception e) {
			LogUtil.error("订单赠送赠品异常", e);
		}
	}

	/**
	 * 生成赠品订单
	 *
	 * @param skuIds      赠品sku信息
	 * @param originOrder 赠品原订单信息
	 * @since 2022-05-16 17:35:15
	 */
	private void generatorGiftOrder(List<String> skuIds, Order originOrder) {
		//获取赠品列表
		List<GoodsSkuVO> goodsSkus = goodsSkuService.getGoodsSkuByIdFromCache(skuIds);

		//赠品判定
		if (goodsSkus == null || goodsSkus.isEmpty()) {
			LogUtil.error("赠品不存在：{}", skuIds);
			return;
		}

		//赠品分类，分为实体商品/虚拟商品/电子卡券
		List<GoodsSkuVO> physicalSkus = goodsSkus.stream()
			.filter(goodsSku -> goodsSku.getGoodsType().equals(GoodsTypeEnum.PHYSICAL_GOODS.name()))
			.toList();
		List<GoodsSkuVO> virtualSkus = goodsSkus.stream()
			.filter(goodsSku -> goodsSku.getGoodsType().equals(GoodsTypeEnum.VIRTUAL_GOODS.name()))
			.toList();
		List<GoodsSkuVO> eCouponSkus = goodsSkus.stream()
			.filter(goodsSku -> goodsSku.getGoodsType().equals(GoodsTypeEnum.E_COUPON.name()))
			.toList();

		//如果赠品不为空，则生成对应的赠品订单
		if (!physicalSkus.isEmpty()) {
			giftOrderHandler(physicalSkus, originOrder, OrderTypeEnum.NORMAL);
		}
		if (!virtualSkus.isEmpty()) {
			giftOrderHandler(virtualSkus, originOrder, OrderTypeEnum.VIRTUAL);
		}
		if (!eCouponSkus.isEmpty()) {
			giftOrderHandler(eCouponSkus, originOrder, OrderTypeEnum.E_COUPON);
		}
	}

	/**
	 * 赠品订单处理
	 *
	 * @param skuList       赠品列表
	 * @param originOrder   原始订单
	 * @param orderTypeEnum 订单类型
	 * @since 2022-05-16 17:35:18
	 */
	private void giftOrderHandler(List<GoodsSkuVO> skuList, Order originOrder, OrderTypeEnum orderTypeEnum) {
		//初始化订单对象/订单日志/自订单
		Order order = new Order();
		List<OrderItem> orderItems = new ArrayList<>();
		List<OrderLog> orderLogs = new ArrayList<>();
		//初始化价格详情
		PriceDetailDTO priceDetailDTO = new PriceDetailDTO();
		//复制通用属性
		BeanUtil.copyProperties(originOrder, order, "id");
		BeanUtil.copyProperties(priceDetailDTO, order, "id");
		//生成订单参数
		order.setSn(IdGeneratorUtil.createStr("G"));
		order.setOrderPromotionType(OrderPromotionTypeEnum.GIFT.name());
		order.setOrderStatus(OrderStatusEnum.UNPAID.name());
		order.setPayStatus(PayStatusEnum.PAID.name());
		order.setOrderType(orderTypeEnum.name());
		order.setNeedReceipt(false);
		order.setPriceDetailDTO(priceDetailDTO);
		order.setClientType(originOrder.getClientType());
		//订单日志
		String message = "赠品订单[" + order.getSn() + "]创建";
		orderLogs.add(new OrderLog(order.getSn(), originOrder.getMemberId(), UserEnum.MEMBER.name(), originOrder.getMemberName(), message));

		//生成子订单
		for (GoodsSkuVO goodsSku : skuList) {
			OrderItem orderItem = new OrderItem();
			BeanUtil.copyProperties(goodsSku, orderItem, "id");
			BeanUtil.copyProperties(priceDetailDTO, orderItem, "id");
			orderItem.setAfterSaleStatus(OrderItemAfterSaleStatusEnum.NEW.name());
			orderItem.setCommentStatus(CommentStatusEnum.NEW.name());
			orderItem.setComplainStatus(OrderComplaintStatusEnum.NEW.name());
			orderItem.setNum(1);
			orderItem.setOrderSn(order.getSn());
			orderItem.setImage(goodsSku.getThumbnail());
			orderItem.setGoodsName(goodsSku.getGoodsName());
			orderItem.setSkuId(goodsSku.getId());
			orderItem.setCategoryId(Long.valueOf(goodsSku.getCategoryPath().substring(
				goodsSku.getCategoryPath().lastIndexOf(",") + 1
			)));
			orderItem.setGoodsPrice(goodsSku.getPrice());
			orderItem.setPriceDetailDTO(priceDetailDTO);
			orderItems.add(orderItem);
		}

		//保存订单
		orderService.save(order);
		orderItemService.saveBatch(orderItems);
		orderLogService.saveBatch(orderLogs);

		//发送订单已付款消息（PS:不在这里处理逻辑是因为期望加交给消费者统一处理库存等等问题）
		OrderMessage orderMessage = new OrderMessage();
		orderMessage.setOrderSn(order.getSn());
		orderMessage.setPaymentMethod(order.getPaymentMethod());
		orderMessage.setNewStatus(OrderStatusEnum.PAID);

		String destination = rocketmqCustomProperties.getOrderTopic() + ":" + OrderTagsEnum.STATUS_CHANGE.name();
		//发送订单变更mq消息
		rocketMQTemplate.asyncSend(destination, JSONUtil.toJsonStr(orderMessage), RocketmqSendCallbackBuilder.commonCallback());
	}
}
