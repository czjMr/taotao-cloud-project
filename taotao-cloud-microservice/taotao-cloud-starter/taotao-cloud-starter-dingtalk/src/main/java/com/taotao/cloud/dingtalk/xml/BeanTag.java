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
package com.taotao.cloud.dingtalk.xml;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * BeanTag
 *
 * @author shuigedeng
 * @version 2022.07
 * @since 2022-07-06 15:26:49
 */
@XmlRootElement(name = "dinger")
public class BeanTag {

	private String namespace;
	private List<MessageTag> messages;

	@XmlAttribute(required = true)
	public String getNamespace() {
		return namespace;
	}

	@XmlElements(value = {
		@XmlElement(name = "message", type = MessageTag.class)
	})
	public List<MessageTag> getMessages() {
		return messages;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public void setMessages(List<MessageTag> messages) {
		this.messages = messages;
	}
}
