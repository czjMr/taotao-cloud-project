package com.taotao.cloud.oss.up;


import com.taotao.cloud.oss.common.model.SliceConfig;
import com.taotao.cloud.oss.common.util.OssPathUtil;

/**
 * 了开源软件配置
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:43:41
 */
public class UpOssConfig {

    private String basePath;
    private String bucketName;
    private String userName;
    private String password;

    /**
     * 默认的超时时间：30秒
     */
    private int timeout = 30;
    /**
     * 默认为自动识别接入点
     */
    private ApiDomain apiDomain = ApiDomain.ED_AUTO;

    /**
     * 断点续传参数
     */
    private SliceConfig sliceConfig = new SliceConfig();

    public void init() {
        this.sliceConfig.init();
        basePath = OssPathUtil.valid(basePath);
    }

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public ApiDomain getApiDomain() {
		return apiDomain;
	}

	public void setApiDomain(ApiDomain apiDomain) {
		this.apiDomain = apiDomain;
	}

	public SliceConfig getSliceConfig() {
		return sliceConfig;
	}

	public void setSliceConfig(SliceConfig sliceConfig) {
		this.sliceConfig = sliceConfig;
	}
}
