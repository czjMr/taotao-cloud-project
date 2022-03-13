package com.taotao.cloud.order.biz.entity.aftersale;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.taotao.cloud.web.base.entity.BaseSuperEntity;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 售后
 */
@Data
@Entity
@Table(name = AfterSale.TABLE_NAME)
@TableName(AfterSale.TABLE_NAME)
@org.hibernate.annotations.Table(appliesTo = AfterSale.TABLE_NAME, comment = "售后")
public class AfterSale extends BaseSuperEntity<AfterSale, Long> {

	public static final String TABLE_NAME = "li_after_sale";
	/**
	 * 应用ID
	 */
	@ApiModelProperty(value = "售后服务单号")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private String sn;
	/**
	 * 应用ID
	 */
	@ApiModelProperty(value = "订单编号")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private String orderSn;
	/**
	 * 应用ID
	 */
	@ApiModelProperty(value = "订单货物编号")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private String orderItemSn;
	/**
	 * 应用ID
	 */
	@ApiModelProperty(value = "交易编号")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private String tradeSn;
	/**
	 * 应用ID
	 */
	@ApiModelProperty(value = "会员ID")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private String memberId;
	/**
	 * 应用ID
	 */
	@ApiModelProperty(value = "会员名称")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private String memberName;
	/**
	 * 应用ID
	 */
	@ApiModelProperty(value = "商家ID")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private String storeId;
	/**
	 * 应用ID
	 */
	@ApiModelProperty(value = "商家名称")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private String storeName;

	//商品信息
	/**
	 * 应用ID
	 */
	@ApiModelProperty(value = "商品ID")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private String goodsId;
	/**
	 * 应用ID
	 */
	@ApiModelProperty(value = "货品ID")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private String skuId;
	/**
	 * 应用ID
	 */
	@ApiModelProperty(value = "申请数量")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private Integer num;

	/**
	 * 应用ID
	 */
	@ApiModelProperty(value = "商品图片")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private String goodsImage;
	/**
	 * 应用ID
	 */
	@ApiModelProperty(value = "商品名称")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private String goodsName;
	/**
	 * 应用ID
	 */
	@ApiModelProperty(value = "规格json")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private String specs;
	/**
	 * 应用ID
	 */
	@ApiModelProperty(value = "实际金额")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private Double flowPrice;

	//交涉信息
	/**
	 * 应用ID
	 */
	@ApiModelProperty(value = "申请原因")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private String reason;
	/**
	 * 应用ID
	 */
	@ApiModelProperty(value = "问题描述")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private String problemDesc;
	/**
	 * 应用ID
	 */
	@ApiModelProperty(value = "评价图片")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private String afterSaleImage;

	/**
	 * @see cn.lili.modules.order.trade.entity.enums.AfterSaleTypeEnum
	 */
	@ApiModelProperty(value = "售后类型", allowableValues = "RETURN_GOODS,RETURN_MONEY")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private String serviceType;

	/**
	 * @see cn.lili.modules.order.trade.entity.enums.AfterSaleStatusEnum
	 */
	@ApiModelProperty(value = "售后单状态", allowableValues = "APPLY,PASS,REFUSE,BUYER_RETURN,SELLER_RE_DELIVERY,BUYER_CONFIRM,SELLER_CONFIRM,COMPLETE")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private String serviceStatus;

	//退款信息

	/**
	 * @see cn.lili.modules.order.trade.entity.enums.AfterSaleRefundWayEnum
	 */
	@ApiModelProperty(value = "退款方式", allowableValues = "ORIGINAL,OFFLINE")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private String refundWay;
	/**
	 * 应用ID
	 */
	@ApiModelProperty(value = "账号类型", allowableValues = "ALIPAY,WECHATPAY,BANKTRANSFER")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private String accountType;
	/**
	 * 应用ID
	 */
	@ApiModelProperty(value = "银行账户")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private String bankAccountNumber;
	/**
	 * 应用ID
	 */
	@ApiModelProperty(value = "银行开户名")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private String bankAccountName;
	/**
	 * 应用ID
	 */
	@ApiModelProperty(value = "银行开户行")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private String bankDepositName;
	/**
	 * 应用ID
	 */
	@ApiModelProperty(value = "商家备注")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private String auditRemark;
	/**
	 * 应用ID
	 */
	@ApiModelProperty(value = "订单支付方式返回的交易号")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private String payOrderNo;
	/**
	 * 应用ID
	 */
	@ApiModelProperty(value = "申请退款金额")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private Double applyRefundPrice;
	/**
	 * 应用ID
	 */
	@ApiModelProperty(value = "实际退款金额")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private Double actualRefundPrice;
	/**
	 * 应用ID
	 */
	@ApiModelProperty(value = "退还积分")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private Integer refundPoint;
	/**
	 * 应用ID
	 */
	@ApiModelProperty(value = "退款时间")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private Date refundTime;

	/**
	 * 买家物流信息
	 */
	/**
	 * 应用ID
	 */
	@ApiModelProperty(value = "发货单号")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private String mLogisticsNo;
	/**
	 * 应用ID
	 */
	@ApiModelProperty(value = "物流公司CODE")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private String mLogisticsCode;
	/**
	 * 应用ID
	 */
	@ApiModelProperty(value = "物流公司名称")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private String mLogisticsName;
	/**
	 * 应用ID
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(value = "买家发货时间")
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private Date mDeliverTime;

}
