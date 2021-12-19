package com.taotao.cloud.store.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.cloud.web.base.entity.BaseSuperEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 店铺自提点
 *
 * 
 * @since 2020/12/7 15:09
 */
@Entity
@Table(name = StoreAddress.TABLE_NAME)
@TableName(StoreAddress.TABLE_NAME)
@org.hibernate.annotations.Table(appliesTo = StoreAddress.TABLE_NAME, comment = "店铺自提点表")
public class StoreAddress extends BaseSuperEntity<StoreAddress, Long> {

	public static final String TABLE_NAME = "li_store_address";

	@Column(name = "store_id", nullable = false, columnDefinition = "varchar(64) not null comment '店铺id'")
	private String storeId;

	@Column(name = "address_name", nullable = false, columnDefinition = "varchar(64) not null comment '自提点名称'")
	private String addressName;

	@Column(name = "center", nullable = false, columnDefinition = "varchar(64) not null comment '经纬度'")
	private String center;

	@Column(name = "address", nullable = false, columnDefinition = "varchar(64) not null comment '地址'")
	private String address;

	@Column(name = "mobile", nullable = false, columnDefinition = "varchar(64) not null comment '电话'")
	private String mobile;

}
