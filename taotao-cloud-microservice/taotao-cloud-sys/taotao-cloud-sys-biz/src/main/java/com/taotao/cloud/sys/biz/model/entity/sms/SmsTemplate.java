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
 * 短信模板
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = SmsTemplate.TABLE_NAME)
@TableName(SmsTemplate.TABLE_NAME)
@org.hibernate.annotations.Table(appliesTo = SmsTemplate.TABLE_NAME, comment = "短信模板表")
public class SmsTemplate extends BaseSuperEntity<SmsTemplate, Long> {

	public static final String TABLE_NAME = "tt_sys_sms_template";

	@Column(name = "template_name", columnDefinition = "varchar(2000) not null comment '模板名称'")
	private String templateName;

	@Column(name = "template_type", columnDefinition = "varchar(2000) not null comment '短信类型'")
	private Integer templateType;

	@Column(name = "remark", columnDefinition = "varchar(2000) not null comment '短信模板申请说明'")
	private String remark;

	@Column(name = "template_content", columnDefinition = "varchar(2000) not null comment '模板内容'")
	private String templateContent;

	@Column(name = "template_status", columnDefinition =
		"int not null default 0 comment '模板审核状态  0：审核中。"
			+ "     * 1：审核通过。"
			+ "     * 2：审核失败，请在返回参数Reason中查看审核失败原因。'")
	private Integer templateStatus;

	@Column(name = "template_code", columnDefinition = "varchar(2000) not null comment '短信模板CODE'")
	private String templateCode;

	@Column(name = "reason", columnDefinition = "varchar(2000) not null comment '审核备注'")
	private String reason;
	@Builder
	public SmsTemplate(Long id, LocalDateTime createTime, Long createBy,
		LocalDateTime updateTime, Long updateBy, Integer version, Boolean delFlag,
		String templateName, Integer templateType, String remark, String templateContent,
		Integer templateStatus, String templateCode, String reason) {
		super(id, createTime, createBy, updateTime, updateBy, version, delFlag);
		this.templateName = templateName;
		this.templateType = templateType;
		this.remark = remark;
		this.templateContent = templateContent;
		this.templateStatus = templateStatus;
		this.templateCode = templateCode;
		this.reason = reason;
	}

	@Override
	public boolean equals(Object o) {
				if (this == o) {
			return true;
		}
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
			return false;
		}
		SmsTemplate that = (SmsTemplate) o;
		return getId() != null && Objects.equals(getId(), that.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
