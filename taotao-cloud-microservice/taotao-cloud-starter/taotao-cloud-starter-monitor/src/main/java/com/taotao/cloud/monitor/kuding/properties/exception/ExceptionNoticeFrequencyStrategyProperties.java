package com.taotao.cloud.monitor.kuding.properties.exception;

import com.taotao.cloud.monitor.kuding.properties.enums.NoticeFrequencyType;
import java.time.Duration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@ConfigurationProperties(prefix = ExceptionNoticeFrequencyStrategyProperties.PREFIX)
public class ExceptionNoticeFrequencyStrategyProperties {

	public static final String PREFIX = "taotao.cloud.monitor.exception.strategy";

	/**
	 * 是否开启异常通知策略
	 */
	private Boolean enabled = false;

	/**
	 * 通知频率类型：按时间或按次数
	 */
	private NoticeFrequencyType frequencyType = NoticeFrequencyType.TIMEOUT;

	/**
	 * 此次出现相同的异常时，与上次通知的时间做对比，假如超过此设定的值，则再次通知
	 */
	private Duration noticeTimeInterval = Duration.ofHours(8);

	/**
	 * 此次出现相同异常时，与上次通知的出现次数作对比，假如超过此设定的值，则再次通知
	 */
	private Long noticeShowCount = 100L;

	/**
	 * @return the frequencyType
	 */
	public NoticeFrequencyType getFrequencyType() {
		return frequencyType;
	}

	/**
	 * @param frequencyType the frequencyType to set
	 */
	public void setFrequencyType(NoticeFrequencyType frequencyType) {
		this.frequencyType = frequencyType;
	}

	/**
	 * @return the noticeTimeInterval
	 */
	public Duration getNoticeTimeInterval() {
		return noticeTimeInterval;
	}

	/**
	 * @param noticeTimeInterval the noticeTimeInterval to set
	 */
	public void setNoticeTimeInterval(Duration noticeTimeInterval) {
		this.noticeTimeInterval = noticeTimeInterval;
	}

	/**
	 * @return the noticeShowCount
	 */
	public Long getNoticeShowCount() {
		return noticeShowCount;
	}

	/**
	 * @param noticeShowCount the noticeShowCount to set
	 */
	public void setNoticeShowCount(Long noticeShowCount) {
		this.noticeShowCount = noticeShowCount;
	}

	/**
	 * @return the enabled
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "ExceptionNoticeFrequencyStrategy [enabled=" + enabled + ", frequencyType="
			+ frequencyType
			+ ", noticeTimeInterval=" + noticeTimeInterval + ", noticeShowCount=" + noticeShowCount
			+ "]";
	}

}
