package com.taotao.cloud.operation.biz.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.cloud.web.base.entity.BaseSuperEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;


/**
 * 专题活动
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-21 16:59:38
 */
@Getter
@Setter
@ToString(callSuper = true)
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = Special.TABLE_NAME)
@TableName(Special.TABLE_NAME)
@org.hibernate.annotations.Table(appliesTo = Special.TABLE_NAME, comment = "专题活动表")
public class Special extends BaseSuperEntity<Special, Long> {

	public static final String TABLE_NAME = "tt_special";
	/**
	 * 专题活动名称
	 */
	@Column(name = "special_name", columnDefinition = "varchar(255) not null comment '专题活动名称 '")
	private String specialName;

	/**
	 * 楼层对应连接端类型 PC,H5,WECHAT_MP,APP
	 * ClientTypeEnum
	 */
	@Column(name = "client_type", columnDefinition = "varchar(255) not null comment '楼层对应连接端类型 PC,H5,WECHAT_MP,APP'")
	private String clientType;
	/**
	 * 页面ID
	 */
	@Column(name = "page_data_id", columnDefinition = "varchar(255) not null comment '页面ID '")
	private String pageDataId;

	@Override
	public boolean equals(Object o) {
				if (this == o) {
			return true;
		}
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
			return false;
		}
		Special special = (Special) o;
		return getId() != null && Objects.equals(getId(), special.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
