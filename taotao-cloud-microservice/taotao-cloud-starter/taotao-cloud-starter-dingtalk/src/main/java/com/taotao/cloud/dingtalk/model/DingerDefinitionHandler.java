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
package com.taotao.cloud.dingtalk.model;

import static com.taotao.cloud.dingtalk.enums.ExceptionEnum.DINGERDEFINITIONTYPE_ERROR;

import com.taotao.cloud.dingtalk.annatations.DingerImageText;
import com.taotao.cloud.dingtalk.annatations.DingerLink;
import com.taotao.cloud.dingtalk.annatations.DingerMarkdown;
import com.taotao.cloud.dingtalk.annatations.DingerText;
import com.taotao.cloud.dingtalk.annatations.DingerTokenId;
import com.taotao.cloud.dingtalk.entity.DingerRequest;
import com.taotao.cloud.dingtalk.entity.MsgType;
import com.taotao.cloud.dingtalk.enums.AsyncExecuteType;
import com.taotao.cloud.dingtalk.enums.DingerDefinitionType;
import com.taotao.cloud.dingtalk.enums.DingerType;
import com.taotao.cloud.dingtalk.enums.MessageMainType;
import com.taotao.cloud.dingtalk.enums.MessageSubType;
import com.taotao.cloud.dingtalk.xml.BodyTag;
import com.taotao.cloud.dingtalk.xml.ConfigurationTag;
import com.taotao.cloud.dingtalk.xml.ContentTag;
import com.taotao.cloud.dingtalk.xml.MessageTag;
import com.taotao.cloud.dingtalk.xml.PhoneTag;
import com.taotao.cloud.dingtalk.xml.PhonesTag;
import com.taotao.cloud.dingtalk.xml.TokenId;
import com.taotao.cloud.dingtalk.exception.DingerException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * DefinitionGenerator
 *
 * @author shuigedeng
 * @version 2022.07
 * @since 2022-07-06 15:22:45
 */
public class DingerDefinitionHandler {

	/**
	 * 企业微信@所有人标识
	 */
	public static final String WETALK_AT_ALL = "@all";


	/**
	 * 处理注解-Text定义的Dinger消息
	 *
	 * @param dingerType Dinger类型 {@link DingerType}
	 * @param context    Dinger定义源
	 * @return dingerDefinition {@link DingerDefinition}
	 */
	protected static DingerDefinition dingerTextHandler(DingerType dingerType,
		DingerDefinitionGeneratorContext<DingerText> context) {
		String keyName = context.getKeyName();
		DingerText dinger = context.getSource();

		DingerDefinition dingerDefinition = annotationDingerDefition(keyName, dinger.tokenId(),
			dinger.asyncExecute());
		dingerDefinition.setDingerType(dingerType);
		dingerDefinition.setMessageSubType(MessageSubType.TEXT);

		DingerRequest request;

		if (dinger.atAll()) {
			request = DingerRequest.request(dinger.value(), true);
		} else {
			request = DingerRequest.request(dinger.value(), Arrays.asList(dinger.phones()));
		}

		MsgType msgType = dingerDefinition.messageSubType().msgType(
			dingerType, request);
		dingerDefinition.setMessage(msgType);

		return dingerDefinition;
	}

	/**
	 * 处理注解-Markdown定义的Dinger消息
	 *
	 * @param dingerType Dinger类型 {@link DingerType}
	 * @param context    Dinger定义源
	 * @return dingerDefinition {@link DingerDefinition}
	 */
	protected static DingerDefinition dingerMarkdownHandler(DingerType dingerType,
		DingerDefinitionGeneratorContext<DingerMarkdown> context) {
		String keyName = context.getKeyName();
		DingerMarkdown dinger = context.getSource();

		DingerDefinition dingerDefinition = annotationDingerDefition(keyName, dinger.tokenId(),
			dinger.asyncExecute());
		dingerDefinition.setDingerType(dingerType);
		dingerDefinition.setMessageSubType(MessageSubType.MARKDOWN);

		// markdown not support at all members
		DingerRequest request = DingerRequest.request(dinger.value(), dinger.title(),
			Arrays.asList(dinger.phones()));
		MsgType msgType = dingerDefinition.messageSubType().msgType(dingerType, request);
		dingerDefinition.setMessage(msgType);

		return dingerDefinition;
	}

	/**
	 * 处理注解-ImageText定义的Dinger消息
	 *
	 * @param dingerType Dinger类型 {@link DingerType}
	 * @param context    Dinger定义源
	 * @return dingerDefinition {@link DingerDefinition}
	 */
	protected static DingerDefinition dingerImageTextHandler(DingerType dingerType,
		DingerDefinitionGeneratorContext<DingerImageText> context) {
		String keyName = context.getKeyName();
		DingerImageText dinger = context.getSource();
		DingerDefinition dingerDefinition = annotationDingerDefition(keyName, dinger.tokenId(),
			dinger.asyncExecute());
		dingerDefinition.setDingerType(dingerType);
		dingerDefinition.setMessageSubType(MessageSubType.IMAGETEXT);

		MsgType msgType = dingerDefinition.messageSubType().msgType(dingerType, null);
		dingerDefinition.setMessage(msgType);

		return dingerDefinition;
	}

	/**
	 * 处理注解-Link定义的Dinger消息
	 *
	 * @param dingerType Dinger类型 {@link DingerType}
	 * @param context    Dinger定义源
	 * @return dingerDefinition {@link DingerDefinition}
	 */
	protected static DingerDefinition dingerLinkHandler(DingerType dingerType,
		DingerDefinitionGeneratorContext<DingerLink> context) {
		String keyName = context.getKeyName();
		DingerLink dinger = context.getSource();
		DingerDefinition dingerDefinition = annotationDingerDefition(keyName, dinger.tokenId(),
			dinger.asyncExecute());
		dingerDefinition.setDingerType(dingerType);
		dingerDefinition.setMessageSubType(MessageSubType.LINK);

		MsgType msgType = dingerDefinition.messageSubType().msgType(dingerType, null);
		dingerDefinition.setMessage(msgType);

		return dingerDefinition;
	}


	/**
	 * 处理Xml定义的Dinger消息
	 *
	 * @param dingerDefinitionType Dinger消息体定义类型
	 * @param context              Dinger定义源
	 * @return dingerDefinition {@link DingerDefinition}
	 */
	protected static DingerDefinition xmlHandler(
		DingerDefinitionType dingerDefinitionType,
		DingerDefinitionGeneratorContext<MessageTag> context
	) {
		MessageMainType messageMainType = dingerDefinitionType.messageMainType();
		if (messageMainType != MessageMainType.XML) {
			throw new DingerException(
				DINGERDEFINITIONTYPE_ERROR, dingerDefinitionType, MessageMainType.XML,
				messageMainType
			);
		}

		String keyName = context.getKeyName();
		MessageTag messageTag = context.getSource();
		DingerDefinition dingerDefinition = new DefaultDingerDefinition();
		dingerDefinition.setDingerType(dingerDefinitionType.dingerType());
		dingerDefinition.setMessageMainType(messageMainType);
		dingerDefinition.setMessageSubType(dingerDefinitionType.messageSubType());
		dingerDefinition.setDingerName(keyName);

		// 处理DingerConfig逻辑
		DingerConfig dingerConfig = new DingerConfig();
		dingerDefinition.setDingerConfig(dingerConfig);
		Optional<ConfigurationTag> configuration = Optional.ofNullable(
			messageTag.getConfiguration());
		dingerConfig(dingerConfig, configuration);

		DingerRequest request = null;
		if (dingerDefinitionType.messageSubType().isSupport()) {
			Optional<BodyTag> body = Optional.ofNullable(messageTag.getBody());

			// 处理@成员逻辑
			Optional<PhonesTag> phonesTag = body.map(e -> e.getPhones());
			Boolean atAll = phonesTag.map(e -> e.getAtAll()).orElse(false);

			List<PhoneTag> phoneTags = phonesTag.map(e -> e.getPhones()).orElse(null);
			List<String> phones;
			if (phoneTags != null) {
				phones = phoneTags.stream().map(PhoneTag::getValue).collect(Collectors.toList());
			} else {
				phones = new ArrayList<>();
			}

			// 处理消息体逻辑
			Optional<ContentTag> contentTag = body.map(e -> e.getContent());
			String content = contentTag.map(e -> e.getContent()).orElse("");
			String title = contentTag.map(e -> e.getTitle()).orElse("Dinger Title");

			if (atAll) {
				request = DingerRequest.request(content, title, true);
			} else {
				request = DingerRequest.request(content, title, phones);
			}
		}

		MsgType message = dingerDefinitionType.messageSubType()
			.msgType(dingerDefinitionType.dingerType(), request);
		dingerDefinition.setMessage(message);

		return dingerDefinition;
	}


	/**
	 * 从注解内容提取dingerDefinition
	 *
	 * @param dingerName       dingerName
	 * @param dingerTokenId    dingerTokenId
	 * @param asyncExecuteType asyncExecuteType
	 * @return dingerDefinition {@link DingerDefinition}
	 */
	private static DingerDefinition annotationDingerDefition(
		String dingerName, DingerTokenId dingerTokenId,
		AsyncExecuteType asyncExecuteType
	) {
		DingerDefinition dingerDefinition = new DefaultDingerDefinition();

		dingerDefinition.setMessageMainType(MessageMainType.ANNOTATION);
		dingerDefinition.setDingerName(dingerName);
		DingerConfig dingerConfig = dingerConfig(dingerTokenId);
		dingerConfig.setAsyncExecute(
			asyncExecuteType == AsyncExecuteType.NONE ?
				null : asyncExecuteType.type()
		);
		dingerDefinition.setDingerConfig(dingerConfig);

		return dingerDefinition;
	}

	/**
	 * dingerConfig从configuration中获取
	 *
	 * @param dingerConfig  dingerConfig
	 * @param configuration xml中配置的configuration
	 */
	private static void dingerConfig(DingerConfig dingerConfig,
		Optional<ConfigurationTag> configuration) {
		if (configuration.isPresent()) {
			Optional<TokenId> tokenId = configuration.map(e -> e.getTokenId());
			if (tokenId.isPresent()) {
				dingerConfig.setTokenId(
					tokenId.map(TokenId::getValue).orElse(null)
				);
				dingerConfig.setDecryptKey(
					tokenId.map(TokenId::getDecryptKey).orElse(null)
				);
				dingerConfig.setSecret(
					tokenId.map(TokenId::getSecret).orElse(null)
				);
			}

			dingerConfig.setAsyncExecute(
				configuration.map(e -> (e.getAsync() == null)
					? e.getAsyncExecute() : e.getAsync()).orElse(false)
			);
			// do check dingtalk config by person
			dingerConfig.check();
		}
	}

	/**
	 * 从dingerTokenId解析DingerConfig
	 *
	 * @param dingerTokenId dingerTokenId
	 * @return dingerConfig
	 */
	private static DingerConfig dingerConfig(DingerTokenId dingerTokenId) {
		DingerConfig dingerConfig = new DingerConfig();
		dingerConfig.setTokenId(dingerTokenId.value());
		dingerConfig.setSecret(dingerTokenId.secret());
		dingerConfig.setDecryptKey(dingerTokenId.decryptKey());
		dingerConfig.check();
		return dingerConfig;
	}

}
