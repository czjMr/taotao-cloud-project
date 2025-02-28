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


import com.taotao.cloud.dingtalk.enums.DingTalkMsgType;
import java.io.Serializable;
import java.util.Map;

/**
 * Markdown 消息格式实体
 *
 * @author shuigedeng
 * @version 2022.07
 * @since 2022-07-06 15:19:44
 */
public class DingMarkDown extends Message {

	/**
	 * {@link MarkDown}
	 */
	private MarkDown markdown;

	public DingMarkDown(MarkDown markdown) {
		setMsgtype(DingTalkMsgType.MARKDOWN.type());
		this.markdown = markdown;
	}

	public MarkDown getMarkdown() {
		return markdown;
	}

	public void setMarkdown(MarkDown markdown) {
		this.markdown = markdown;
	}

	public static class MarkDown implements Serializable {

		/**
		 * 首屏会话透出的展示内容, 不会展示在具体的显示内容上
		 */
		private String title;
		/**
		 * markdown格式的消息
		 */
		private String text;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public MarkDown() {
		}

		public MarkDown(String title, String text) {
			this.title = title;
			this.text = text;
		}
	}

	@Override
	public void transfer(Map<String, Object> params) {
		this.markdown.text = replaceContent(this.markdown.text, params);
	}
}
