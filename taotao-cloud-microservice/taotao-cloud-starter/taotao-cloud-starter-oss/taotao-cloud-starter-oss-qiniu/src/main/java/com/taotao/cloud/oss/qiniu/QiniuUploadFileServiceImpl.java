/*
 * Copyright (c) 2020-2030, Shuigedeng (981376577@qq.com & https://blog.taotaocloud.top/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.taotao.cloud.oss.qiniu;

import cn.hutool.json.JSONUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.taotao.cloud.common.utils.log.LogUtils;
import com.taotao.cloud.oss.common.exception.UploadFileException;
import com.taotao.cloud.oss.common.model.UploadFileInfo;
import com.taotao.cloud.oss.common.service.AbstractUploadFileService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * QiniuUploadFileService
 *
 * @author shuigedeng
 * @version 2022.03
 * @since 2021/08/24 16:15
 */
public class QiniuUploadFileServiceImpl extends AbstractUploadFileService {

	private final UploadManager uploadManager;
	private final BucketManager bucketManager;
	private final Auth auth;
	private final QiniuProperties properties;

	public QiniuUploadFileServiceImpl(UploadManager uploadManager, BucketManager bucketManager,
		Auth auth, QiniuProperties properties) {
		this.uploadManager = uploadManager;
		this.bucketManager = bucketManager;
		this.auth = auth;
		this.properties = properties;
	}

	@Override
	protected UploadFileInfo uploadFile(MultipartFile file, UploadFileInfo uploadFileInfo) {
		try {
			Response response = uploadManager.put(file.getBytes(), uploadFileInfo.getName(),
				auth.uploadToken(properties.getBucketName(), uploadFileInfo.getName()));
			DefaultPutRet putRet = JSONUtil.toBean(response.bodyString(), DefaultPutRet.class);
			uploadFileInfo.setUrl(properties.getDomain() + "/" + uploadFileInfo.getName());
			return uploadFileInfo;
		} catch (IOException e) {
			LogUtils.error("[qiniu]文件上传失败:", e);
			throw new UploadFileException("[qiniu]文件上传失败");
		}
	}

	@Override
	protected UploadFileInfo uploadFile(File file, UploadFileInfo uploadFileInfo) {
		try {
			Response response = uploadManager.put(file, uploadFileInfo.getName(),
				auth.uploadToken(properties.getBucketName(), uploadFileInfo.getName()));
			DefaultPutRet putRet = JSONUtil.toBean(response.bodyString(), DefaultPutRet.class);
			uploadFileInfo.setUrl(properties.getDomain() + "/" + uploadFileInfo.getName());
			return uploadFileInfo;
		} catch (QiniuException e) {
			LogUtils.error("[qiniu]文件上传失败:", e);
			throw new UploadFileException("[qiniu]文件上传失败");
		}
	}

	@Override
	public UploadFileInfo delete(UploadFileInfo uploadFileInfo) {
		try {
			bucketManager.delete(properties.getBucketName(), uploadFileInfo.getUrl());
		} catch (QiniuException e) {
			LogUtils.error("[qiniu]文件删除失败:", e);
			throw new UploadFileException("[qiniu]文件删除失败");
		}
		return uploadFileInfo;
	}
}

