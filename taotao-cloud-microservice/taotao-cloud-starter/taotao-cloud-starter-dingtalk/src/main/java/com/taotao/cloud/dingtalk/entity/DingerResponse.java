/*
 * Copyright (c) ©2015-2021 Jaemon. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.taotao.cloud.dingtalk.entity;


import com.taotao.cloud.common.enums.ResultEnum;
import com.taotao.cloud.dingtalk.enums.DingerResponseCodeEnum;

/**
 * Dinger响应体
 *
 * @author shuigedeng
 * @version 2022.07
 * @since 2022-07-06 15:19:34
 */
public class DingerResponse {

	/**
	 * 响应码
	 */
	private String code;
	/**
	 * 响应消息
	 */
	private String message;
	/**
	 * 日志id
	 */
	private String logid;
	/**
	 * 响应数据
	 */
	private String data;


	private DingerResponse(DingerResponseCodeEnum resultCode) {
		this.code = resultCode.code();
		this.message = resultCode.message();
	}

	private DingerResponse(String logid, DingerResponseCodeEnum resultCode) {
		this(resultCode);
		this.logid = logid;
	}

	private DingerResponse(DingerResponseCodeEnum resultCode, String data) {
		this(resultCode);
		this.data = data;
	}

	private DingerResponse(DingerResponseCodeEnum resultCode, String logid, String data) {
		this(logid, resultCode);
		this.data = data;
	}

	public static <T> DingerResponse success(String logId, String data) {
		return new DingerResponse(DingerResponseCodeEnum.SUCCESS, logId, data);
	}

	public static <T> DingerResponse success(DingerResponseCodeEnum resultCode, String logId,
		String data) {
		return new DingerResponse(resultCode, logId, data);
	}

	public static DingerResponse failed(String logid) {
		return new DingerResponse(logid, DingerResponseCodeEnum.FAILED);
	}

	public static DingerResponse failed(String logid, DingerResponseCodeEnum resultCode) {
		return new DingerResponse(logid, resultCode);
	}

	public static DingerResponse failed(DingerResponseCodeEnum resultCode, String data) {
		return new DingerResponse(resultCode, data);
	}

	public static DingerResponse failed(DingerResponseCodeEnum resultCode) {
		return new DingerResponse(resultCode);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return String.format("[code=%s, message=%s, logid=%s, data=%s]",
			code, message, logid, data);
	}
}
