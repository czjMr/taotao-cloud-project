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
package com.taotao.cloud.dingtalk.definition;

import com.taotao.cloud.dingtalk.model.DingerDefinition;
import com.taotao.cloud.dingtalk.model.DingerDefinitionGenerator;
import com.taotao.cloud.dingtalk.model.DingerDefinitionGeneratorContext;
import com.taotao.cloud.dingtalk.model.DingerDefinitionHandler;
import com.taotao.cloud.dingtalk.annatations.DingerImageText;
import com.taotao.cloud.dingtalk.annatations.DingerLink;
import com.taotao.cloud.dingtalk.annatations.DingerMarkdown;
import com.taotao.cloud.dingtalk.annatations.DingerText;
import com.taotao.cloud.dingtalk.enums.DingerDefinitionType;
import com.taotao.cloud.dingtalk.enums.DingerType;
import com.taotao.cloud.dingtalk.xml.MessageTag;

/**
 * 钉钉消息体定义生成类
 *
 * @author shuigedeng
 * @version 2022.07
 * @since 2022-07-06 15:18:54
 */
public class DingTalkDefinitionGenerator extends DingerDefinitionHandler {

	/**
	 * 生成生成注解文本消息体定义
	 *
	 * @author shuigedeng
	 * @version 2022.07
	 * @since 2022-07-06 15:18:54
	 */
	public static class AnotationText extends DingerDefinitionGenerator<DingerText> {

		/**
		 * 发电机
		 *
		 * @param context 上下文
		 * @return {@link DingerDefinition }
		 * @since 2022-07-06 15:18:54
		 */
		@Override
		public DingerDefinition generator(DingerDefinitionGeneratorContext<DingerText> context) {
			return dingerTextHandler(DingerType.DINGTALK, context);
		}
	}


	/**
	 * 生成注解Markdown消息体定义
	 *
	 * @author shuigedeng
	 * @version 2022.07
	 * @since 2022-07-06 15:18:54
	 */
	public static class AnnotationMarkdown extends DingerDefinitionGenerator<DingerMarkdown> {

		/**
		 * 发电机
		 *
		 * @param context 上下文
		 * @return {@link DingerDefinition }
		 * @since 2022-07-06 15:18:54
		 */
		@Override
		public DingerDefinition generator(
			DingerDefinitionGeneratorContext<DingerMarkdown> context) {
			return dingerMarkdownHandler(DingerType.DINGTALK, context);
		}
	}


	/**
	 * 生成XML文本消息体定义
	 *
	 * @author shuigedeng
	 * @version 2022.07
	 * @since 2022-07-06 15:18:54
	 */
	public static class XmlText extends DingerDefinitionGenerator<MessageTag> {

		/**
		 * 发电机
		 *
		 * @param context 上下文
		 * @return {@link DingerDefinition }
		 * @since 2022-07-06 15:18:54
		 */
		@Override
		public DingerDefinition generator(DingerDefinitionGeneratorContext<MessageTag> context) {
			return xmlHandler(DingerDefinitionType.DINGTALK_XML_TEXT, context);
		}
	}


	/**
	 * 生成XML Markdown消息体定义
	 *
	 * @author shuigedeng
	 * @version 2022.07
	 * @since 2022-07-06 15:18:54
	 */
	public static class XmlMarkdown extends DingerDefinitionGenerator<MessageTag> {

		/**
		 * 发电机
		 *
		 * @param context 上下文
		 * @return {@link DingerDefinition }
		 * @since 2022-07-06 15:18:54
		 */
		@Override
		public DingerDefinition generator(DingerDefinitionGeneratorContext<MessageTag> context) {
			return xmlHandler(DingerDefinitionType.DINGTALK_XML_MARKDOWN, context);
		}
	}


	/**
	 * 生成注解 ImageText消息体定义
	 *
	 * @author shuigedeng
	 * @version 2022.07
	 * @since 2022-07-06 15:18:54
	 */
	public static class AnnotationImageText extends DingerDefinitionGenerator<DingerImageText> {

		/**
		 * 发电机
		 *
		 * @param context 上下文
		 * @return {@link DingerDefinition }
		 * @since 2022-07-06 15:18:55
		 */
		@Override
		public DingerDefinition generator(
			DingerDefinitionGeneratorContext<DingerImageText> context) {
			return dingerImageTextHandler(DingerType.DINGTALK, context);
		}
	}


	/**
	 * 生成XML ImageText消息体定义
	 *
	 * @author shuigedeng
	 * @version 2022.07
	 * @since 2022-07-06 15:18:55
	 */
	public static class XmlImageText extends DingerDefinitionGenerator<MessageTag> {

		/**
		 * 发电机
		 *
		 * @param context 上下文
		 * @return {@link DingerDefinition }
		 * @since 2022-07-06 15:18:55
		 */
		@Override
		public DingerDefinition generator(DingerDefinitionGeneratorContext<MessageTag> context) {
			return xmlHandler(DingerDefinitionType.DINGTALK_XML_IMAGETEXT, context);
		}
	}


	/**
	 * 生成注解 Link消息体定义
	 *
	 * @author shuigedeng
	 * @version 2022.07
	 * @since 2022-07-06 15:18:55
	 */
	public static class AnnotationLink extends DingerDefinitionGenerator<DingerLink> {

		/**
		 * 发电机
		 *
		 * @param context 上下文
		 * @return {@link DingerDefinition }
		 * @since 2022-07-06 15:18:55
		 */
		@Override
		public DingerDefinition generator(DingerDefinitionGeneratorContext<DingerLink> context) {
			return dingerLinkHandler(DingerType.DINGTALK, context);
		}
	}


	/**
	 * 生成XML Link消息体定义
	 *
	 * @author shuigedeng
	 * @version 2022.07
	 * @since 2022-07-06 15:18:55
	 */
	public static class XmlLink extends DingerDefinitionGenerator<MessageTag> {

		/**
		 * 发电机
		 *
		 * @param context 上下文
		 * @return {@link DingerDefinition }
		 * @since 2022-07-06 15:18:55
		 */
		@Override
		public DingerDefinition generator(DingerDefinitionGeneratorContext<MessageTag> context) {
			return xmlHandler(DingerDefinitionType.DINGTALK_XML_LINK, context);
		}
	}
}
