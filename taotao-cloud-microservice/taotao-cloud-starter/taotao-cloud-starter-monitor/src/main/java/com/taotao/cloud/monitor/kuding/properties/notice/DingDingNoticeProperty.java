package com.taotao.cloud.monitor.kuding.properties.notice;

import com.taotao.cloud.monitor.kuding.pojos.dingding.DingDingMarkdownNotice;
import com.taotao.cloud.monitor.kuding.pojos.dingding.DingDingNotice;
import com.taotao.cloud.monitor.kuding.pojos.dingding.DingDingTextNotice;
import com.taotao.cloud.monitor.kuding.properties.enums.DingdingTextType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@ConfigurationProperties(prefix = DingDingNoticeProperty.PREFIX)
public class DingDingNoticeProperty {

	public static final String PREFIX = "taotao.cloud.monitor.notice.dingding";

	/**
	 * 是否开启钉钉通知
	 */
	private boolean enabled = false;

	/**
	 * 电话信息
	 */
	private String[] phoneNum;

	/**
	 * 钉钉机器人的accessToken
	 */
	private String accessToken;

	/**
	 * 是否开启验签
	 */
	private boolean enableSignatureCheck;

	/**
	 * 验签秘钥
	 */
	private String signSecret;

	/**
	 * 钉钉通知文本类型
	 */
	private DingdingTextType dingdingTextType = DingdingTextType.TEXT;

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the phoneNum
	 */
	public String[] getPhoneNum() {
		return phoneNum;
	}

	/**
	 * @param phoneNum the phoneNum to set
	 */
	public void setPhoneNum(String[] phoneNum) {
		this.phoneNum = phoneNum;
	}

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * @return the enableSignatureCheck
	 */
	public boolean isEnableSignatureCheck() {
		return enableSignatureCheck;
	}

	/**
	 * @param enableSignatureCheck the enableSignatureCheck to set
	 */
	public void setEnableSignatureCheck(boolean enableSignatureCheck) {
		this.enableSignatureCheck = enableSignatureCheck;
	}

	/**
	 * @return the signSecret
	 */
	public String getSignSecret() {
		return signSecret;
	}

	/**
	 * @param signSecret the signSecret to set
	 */
	public void setSignSecret(String signSecret) {
		this.signSecret = signSecret;
	}

	/**
	 * @return the dingdingTextType
	 */
	public DingdingTextType getDingdingTextType() {
		return dingdingTextType;
	}

	/**
	 * @param dingdingTextType the dingdingTextType to set
	 */
	public void setDingdingTextType(DingdingTextType dingdingTextType) {
		this.dingdingTextType = dingdingTextType;
	}

	public DingDingNotice generateDingdingNotice(String msg, String title) {
		switch (dingdingTextType) {
			case MARKDOWN:
				return new DingDingMarkdownNotice(msg, title, phoneNum);
			case TEXT:
				return new DingDingTextNotice(msg, phoneNum);
		}
		// never happen;
		return null;
	}

}
