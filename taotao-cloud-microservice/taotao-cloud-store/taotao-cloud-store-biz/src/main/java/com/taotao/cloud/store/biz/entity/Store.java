package com.taotao.cloud.store.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.cloud.web.base.entity.BaseSuperEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 店铺
 *
 * 
 * @since 2020-02-18 15:18:56
 */
@Entity
@Table(name = Store.TABLE_NAME)
@TableName(Store.TABLE_NAME)
@org.hibernate.annotations.Table(appliesTo = Store.TABLE_NAME, comment = "店铺表")
public class Store extends BaseSuperEntity<Store, Long> {

	public static final String TABLE_NAME = "li_store";

	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员Id'")
	private String memberId;

	@Column(name = "member_name", nullable = false, columnDefinition = "varchar(64) not null comment '会员名称'")
	private String memberName;

	@Column(name = "store_name", nullable = false, columnDefinition = "varchar(64) not null comment '店铺名称'")
	private String storeName;

	@Column(name = "store_end_time", columnDefinition = "TIMESTAMP comment '店铺关闭时间'")
	private LocalDateTime storeEndTime;

	/**
	 * @see StoreStatusEnum
	 */
	@Column(name = "store_disable", nullable = false, columnDefinition = "varchar(64) not null comment '店铺状态'")
	private String storeDisable;

	@Column(name = "self_operated", nullable = false, columnDefinition = "boolean not null default true comment '是否自营'")
	private Boolean selfOperated;

	@Column(name = "store_logo", nullable = false, columnDefinition = "varchar(64) not null comment '店铺logo'")
	private String storeLogo;

	@Column(name = "store_center", nullable = false, columnDefinition = "varchar(64) not null comment '经纬度'")
	private String storeCenter;

	@Column(name = "store_desc", nullable = false, columnDefinition = "varchar(64) not null comment '店铺简介'")
	private String storeDesc;

	@Column(name = "store_address_path", nullable = false, columnDefinition = "varchar(64) not null comment '地址名称 逗号分割'")
	private String storeAddressPath;

	@Column(name = "store_address_id_path", nullable = false, columnDefinition = "varchar(64) not null comment '地址id 逗号分割 '")
	private String storeAddressIdPath;

	@Column(name = "store_address_detail", nullable = false, columnDefinition = "varchar(64) not null comment '详细地址'")
	private String storeAddressDetail;

	@Column(name = "description_score", nullable = false, columnDefinition = "decimal(10,2) not null default 0 comment '描述评分'")
	private BigDecimal descriptionScore;

	@Column(name = "service_score", nullable = false, columnDefinition = "decimal(10,2) not null default 0 comment '服务评分'")
	private BigDecimal serviceScore;

	@Column(name = "delivery_score", nullable = false, columnDefinition = "decimal(10,2) not null default 0 comment '交付分数'")
	private BigDecimal deliveryScore;

	@Column(name = "goods_num", nullable = false, columnDefinition = "int not null default 0 comment '商品数量'")
	private Integer goodsNum;

	@Column(name = "collection_num", nullable = false, columnDefinition = "int not null default 0 comment '收藏数量'")
	private Integer collectionNum;

	@Column(name = "yzf_sign", nullable = false, columnDefinition = "varchar(64) not null comment '腾讯云智服唯一标识'")
	private String yzfSign;

	@Column(name = "yzf_mp_sign", nullable = false, columnDefinition = "varchar(64) not null comment '腾讯云智服小程序唯一标识'")
	private String yzfMpSign;


	@Column(name = "merchant_euid", nullable = false, columnDefinition = "varchar(64) not null comment 'udesk IM标识'")
	private String merchantEuid;

	//public Store(Member member) {
	//    this.memberId = member.getId();
	//    this.memberName = member.getUsername();
	//    storeDisable = StoreStatusEnum.APPLY.value();
	//    selfOperated = false;
	//    deliveryScore = 5.0;
	//    serviceScore = 5.0;
	//    descriptionScore = 5.0;
	//    goodsNum = 0;
	//    collectionNum = 0;
	//}
	//
	//public Store(Member member, AdminStoreApplyDTO adminStoreApplyDTO) {
	//    BeanUtil.copyProperties(adminStoreApplyDTO, this);
	//
	//    this.memberId = member.getId();
	//    this.memberName = member.getUsername();
	//    storeDisable = StoreStatusEnum.APPLYING.value();
	//    selfOperated = false;
	//    deliveryScore = 5.0;
	//    serviceScore = 5.0;
	//    descriptionScore = 5.0;
	//    goodsNum = 0;
	//    collectionNum = 0;
	//}

}
