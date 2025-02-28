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
package com.taotao.cloud.oss.common.service;

import com.taotao.cloud.oss.common.exception.UploadFileException;
import com.taotao.cloud.oss.common.model.UploadFileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 文件上传接口
 *
 * @author shuigedeng
 * @version 2022.09
 * @since 2022-09-23 10:42:14
 */
public interface UploadFileService {

	/**
	 * 文件上传接口
	 *
	 * @param file 文件对象
	 * @return {@link UploadFileInfo }
	 * @throws UploadFileException 上传异常
	 * @since 2022-09-23 10:42:14
	 */
	UploadFileInfo upload(File file) throws UploadFileException;

	/**
	 * 文件上传接口
	 *
	 * @param file    file
	 * @param fileKey fileKey
	 * @return {@link UploadFileInfo }
	 * @throws UploadFileException 上传异常
	 * @since 2022-09-23 10:42:14
	 */
	UploadFileInfo upload(File file, String fileKey) throws UploadFileException;

	/**
	 * 文件上传接口
	 *
	 * @param file file
	 * @return {@link UploadFileInfo }
	 * @throws UploadFileException 上传异常
	 * @since 2022-09-23 10:42:14
	 */
	UploadFileInfo upload(MultipartFile file) throws UploadFileException;

	/**
	 * 文件上传接口
	 *
	 * @param file    file
	 * @param fileKey fileKey
	 * @return {@link UploadFileInfo }
	 * @throws UploadFileException 上传异常
	 * @since 2022-09-23 10:42:14
	 */
	UploadFileInfo upload(MultipartFile file, String fileKey) throws UploadFileException;

	/**
	 * 删除文件
	 *
	 * @param uploadFileInfo fileInfo
	 * @return {@link UploadFileInfo }
	 * @throws UploadFileException 上传异常
	 * @since 2022-09-23 10:42:14
	 */
	UploadFileInfo delete(UploadFileInfo uploadFileInfo) throws UploadFileException;
}
