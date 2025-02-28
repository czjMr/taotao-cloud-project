package com.taotao.cloud.sys.biz.model.entity.sms;

import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.cloud.web.base.entity.BaseSuperEntity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;


/**
 * 短信任务
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = SmsReach.TABLE_NAME)
@TableName(SmsReach.TABLE_NAME)
@org.hibernate.annotations.Table(appliesTo = SmsReach.TABLE_NAME, comment = "短信任务表")
public class SmsReach extends BaseSuperEntity<SmsReach, Long> {

	public static final String TABLE_NAME = "tt_sys_sms_reach";

	@Column(name = "sign_name", columnDefinition = "varchar(2000) not null comment '签名名称'")
	private String signName;

	@Column(name = "sms_name", columnDefinition = "varchar(2000) not null comment '模板名称'")
	private String smsName;

	@Column(name = "message_code", columnDefinition = "varchar(2000) not null comment '消息CODE'")
	private String messageCode;

	@Column(name = "context", columnDefinition = "varchar(2000) not null comment '消息内容'")
	private String context;

	@Column(name = "sms_range", columnDefinition = "varchar(2000) not null comment '接收人 1:全部会员，2：选择会员 '")
	private String smsRange;

	@Column(name = "num", columnDefinition = "varchar(2000) not null comment '预计发送条数'")
	private String num;
	@Builder
	public SmsReach(Long id, LocalDateTime createTime, Long createBy,
		LocalDateTime updateTime, Long updateBy, Integer version, Boolean delFlag,
		String signName, String smsName, String messageCode, String context, String smsRange,
		String num) {
		super(id, createTime, createBy, updateTime, updateBy, version, delFlag);
		this.signName = signName;
		this.smsName = smsName;
		this.messageCode = messageCode;
		this.context = context;
		this.smsRange = smsRange;
		this.num = num;
	}

	@Override
	public boolean equals(Object o) {
				if (this == o) {
			return true;
		}
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
			return false;
		}
		SmsReach smsReach = (SmsReach) o;
		return getId() != null && Objects.equals(getId(), smsReach.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
