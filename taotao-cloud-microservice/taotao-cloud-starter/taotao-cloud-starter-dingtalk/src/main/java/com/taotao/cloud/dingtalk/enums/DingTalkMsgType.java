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
package com.taotao.cloud.dingtalk.enums;

/**
 * DingTalk支持的消息类型
 *
 * @author shuigedeng
 * @version 2022.07
 * @since 2022-07-06 15:20:53
 */
public enum DingTalkMsgType {
	/**
	 * text类型
	 */
	TEXT("text"),

	/**
	 * link类型
	 */
	LINK("link"),

	/**
	 * markdown类型
	 */
	MARKDOWN("markdown"),

	/**
	 * ActionCard类型
	 */
	ACTION_CARD("actionCard"),

	/**
	 * FeedCard类型
	 */
	FEED_CARD("feedCard");

	private String type;

	DingTalkMsgType(String type) {
		this.type = type;
	}

	public String type() {
		return type;
	}
}
