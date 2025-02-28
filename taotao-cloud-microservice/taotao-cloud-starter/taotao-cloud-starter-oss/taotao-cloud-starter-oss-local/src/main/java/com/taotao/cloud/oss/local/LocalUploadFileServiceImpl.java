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
package com.taotao.cloud.oss.local;

import com.taotao.cloud.common.utils.log.LogUtils;
import com.taotao.cloud.oss.common.exception.UploadFileException;
import com.taotao.cloud.oss.common.model.UploadFileInfo;
import com.taotao.cloud.oss.common.service.AbstractUploadFileService;
import com.taotao.cloud.oss.common.util.FileUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * LocalUploadFileService
 *
 * @author shuigedeng
 * @version 2022.03
 * @since 2021/08/24 16:10
 */
public class LocalUploadFileServiceImpl extends AbstractUploadFileService {

	private final LocalProperties properties;

	public LocalUploadFileServiceImpl(LocalProperties ftpProperties) {
		this.properties = ftpProperties;
	}

	@Override
	protected UploadFileInfo uploadFile(MultipartFile file, UploadFileInfo uploadFileInfo) {
		try {
			String filePath = properties.getFilePath() + "/" + uploadFileInfo.getName();
			String s = FileUtil.saveFile(file, filePath);
			if (s == null) {
				throw new UploadFileException("[local]文件上传失败");
			}
			uploadFileInfo.setUrl(uploadFileInfo.getName());
			return uploadFileInfo;
		} catch (Exception e) {
			LogUtils.error("[local]文件上传失败:", e);
			throw new UploadFileException("[local]文件上传失败");
		}
	}

	@Override
	protected UploadFileInfo uploadFile(File file, UploadFileInfo uploadFileInfo) {
		try {
			String filePath = properties.getFilePath() + "/" + uploadFileInfo.getName();
			File newFile = cn.hutool.core.io.FileUtil.newFile(filePath);
			File copy = cn.hutool.core.io.FileUtil.copy(file, newFile, true);
			if (copy == null) {
				throw new UploadFileException("[local]文件上传失败");
			}
			uploadFileInfo.setUrl(uploadFileInfo.getName());
			return uploadFileInfo;
		} catch (Exception e) {
			LogUtils.error("[local]文件上传失败:", e);
			throw new UploadFileException("[local]文件上传失败");
		}
	}

	@Override
	public UploadFileInfo delete(UploadFileInfo uploadFileInfo) {
		try {
			String filePath = properties.getFilePath() + uploadFileInfo.getName();
			boolean b = FileUtil.deleteFile(filePath);
			if (!b) {
				throw new UploadFileException("[local]文件删除失败");
			}
		} catch (Exception e) {
			LogUtils.error("[local]文件删除失败:", e);
			throw new UploadFileException("[local]文件删除失败");
		}
		return uploadFileInfo;
	}
}
