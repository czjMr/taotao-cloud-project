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

package com.taotao.cloud.common.utils.spider;

import com.taotao.cloud.common.utils.exception.ExceptionUtils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.helper.DataUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.cglib.proxy.Enhancer;

/**
 * 爬虫 xml 转 bean 基于 jsoup
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-02 20:01:42
 */
public class DomMapper {

	/**
	 * Returns body to jsoup Document.
	 *
	 * @return Document
	 */
	//public static Document asDocument(ResponseSpec response) {
	//	return readDocument(response.asString());
	//}

	/**
	 * 将流读取为 jsoup Document
	 *
	 * @param inputStream InputStream
	 * @return Document
	 */
	public static Document readDocument(InputStream inputStream) {
		try {
			return DataUtil.load(inputStream, StandardCharsets.UTF_8.name(), "");
		} catch (IOException e) {
			throw ExceptionUtils.unchecked(e);
		}
	}

	/**
	 * 将 html 字符串读取为 jsoup Document
	 *
	 * @param html String
	 * @return Document
	 */
	public static Document readDocument(String html) {
		return Parser.parse(html, "");
	}

	/**
	 * 读取 xml 信息为 java Bean
	 *
	 * @param response ResponseSpec
	 * @param clazz    bean Class
	 * @param <T>      泛型
	 * @return 对象
	 */
	//public static <T> T readValue(ResponseSpec response, final Class<T> clazz) {
	//	return readValue(response.asStream(), clazz);
	//}

	/**
	 * 读取 xml 信息为 java Bean
	 *
	 * @param inputStream InputStream
	 * @param clazz       bean Class
	 * @param <T>         泛型
	 * @return 对象
	 */
	public static <T> T readValue(InputStream inputStream, final Class<T> clazz) {
		return readValue(readDocument(inputStream), clazz);
	}

	/**
	 * 读取 xml 信息为 java Bean
	 *
	 * @param html  html String
	 * @param clazz bean Class
	 * @param <T>   泛型
	 * @return 对象
	 */
	public static <T> T readValue(String html, final Class<T> clazz) {
		return readValue(readDocument(html), clazz);
	}

	/**
	 * 读取 xml 信息为 java Bean
	 *
	 * @param doc   xml element
	 * @param clazz bean Class
	 * @param <T>   泛型
	 * @return 对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T readValue(final Element doc, final Class<T> clazz) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(clazz);
		enhancer.setUseCache(true);
		enhancer.setContextClass(clazz);
		enhancer.setCallback(new CssQueryMethodInterceptor(clazz, doc));
		return (T) enhancer.create();
	}

	/**
	 * 读取 xml 信息为 java Bean
	 *
	 * @param <T>      泛型
	 * @param response ResponseSpec
	 * @param clazz    bean Class
	 * @return 对象
	 */
	//public static <T> List<T> readList(ResponseSpec response, final Class<T> clazz) {
	//	return readList(response.asStream(), clazz);
	//}

	/**
	 * 读取 xml 信息为 java Bean
	 *
	 * @param <T>         泛型
	 * @param inputStream InputStream
	 * @param clazz       bean Class
	 * @return 对象
	 */
	public static <T> List<T> readList(InputStream inputStream, final Class<T> clazz) {
		return readList(readDocument(inputStream), clazz);
	}

	/**
	 * 读取 xml 信息为 java Bean
	 *
	 * @param <T>   泛型
	 * @param html  html String
	 * @param clazz bean Class
	 * @return 对象
	 */
	public static <T> List<T> readList(String html, final Class<T> clazz) {
		return readList(readDocument(html), clazz);
	}

	/**
	 * 读取 xml 信息为 java Bean
	 *
	 * @param doc   xml element
	 * @param clazz bean Class
	 * @param <T>   泛型
	 * @return 对象列表
	 */
	public static <T> List<T> readList(Element doc, Class<T> clazz) {
		CssQuery annotation = clazz.getAnnotation(CssQuery.class);
		if (annotation == null) {
			throw new IllegalArgumentException(
				"DomMapper readList " + clazz + " mast has annotation @CssQuery.");
		}
		String cssQueryValue = annotation.value();
		Elements elements = doc.select(cssQueryValue);
		List<T> valueList = new ArrayList<>();
		for (Element element : elements) {
			valueList.add(readValue(element, clazz));
		}
		return valueList;
	}

}
