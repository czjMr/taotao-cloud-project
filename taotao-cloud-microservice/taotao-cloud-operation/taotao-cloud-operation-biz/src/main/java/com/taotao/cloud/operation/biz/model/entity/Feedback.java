package com.taotao.cloud.operation.biz.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.cloud.operation.api.enums.FeedbackTypeEnum;
import com.taotao.cloud.web.base.entity.BaseSuperEntity;
import com.taotao.cloud.web.enums.SensitiveStrategy;
import com.taotao.cloud.web.sensitive.desensitize.Sensitive;
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
 * 意见反馈
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
@Table(name = Feedback.TABLE_NAME)
@TableName(Feedback.TABLE_NAME)
@org.hibernate.annotations.Table(appliesTo = Feedback.TABLE_NAME, comment = "意见反馈表")
public class Feedback extends BaseSuperEntity<Feedback, Long> {

	public static final String TABLE_NAME = "tt_feedback";

	/**
	 * 会员名称
	 */
	@Column(name = "user_name", columnDefinition = "varchar(255) not null comment '会员名称 '")
	private String userName;
	/**
	 * 反馈内容
	 */
	@Column(name = "context", columnDefinition = "varchar(255) not null comment '反馈内容 '")
	private String context;
	/**
	 * 手机号
	 */
	@Column(name = "mobile", columnDefinition = "varchar(255) not null comment '手机号 '")
	@Sensitive(strategy = SensitiveStrategy.PHONE)
	private String mobile;
	/**
	 * 图片，多个图片使用：(，)分割
	 */
	@Column(name = "images", columnDefinition = "text not null comment '图片，多个图片使用：(，)分割 '")
	private String images;

	/**
	 * 业务类型
	 *
	 * @see FeedbackTypeEnum
	 */
	@Column(name = "biz_type", columnDefinition = "varchar(255) not null comment '业务类型 FUNCTION,OPTIMIZE,OTHER'")
	private String type;

	@Override
	public boolean equals(Object o) {
				if (this == o) {
			return true;
		}
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
			return false;
		}
		Feedback feedback = (Feedback) o;
		return getId() != null && Objects.equals(getId(), feedback.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
