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

package com.taotao.cloud.common.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.taotao.cloud.common.utils.exception.ExceptionUtils;
import com.taotao.cloud.common.utils.common.JsonUtils;
import okhttp3.*;
import okhttp3.internal.Util;

import javax.annotation.Nullable;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

/**
 * ok http 封装，相应结构体
 *
 */
public class HttpResponse implements ResponseSpec, Closeable {
	private final Request request;
	private final Response response;
	private final ResponseBody body;

	HttpResponse(final Response response) {
		this.request = response.request();
		this.response = response;
		this.body = ifNullBodyToEmpty(response.body());
	}

	@Override
	public int code() {
		return response.code();
	}

	@Override
	public String message() {
		return response.message();
	}

	@Override
	public boolean isOk() {
		return response.isSuccessful();
	}

	@Override
	public boolean isRedirect() {
		return response.isRedirect();
	}

	@Override
	public Headers headers() {
		return response.headers();
	}

	@Override
	public List<Cookie> cookies() {
		return Cookie.parseAll(request.url(), this.headers());
	}

	@Override
	public Request rawRequest() {
		return this.request;
	}

	@Override
	public Response rawResponse() {
		return this.response;
	}

	@Override
	public ResponseBody rawBody() {
		return this.body;
	}

	@Override
	public String asString() {
		try {
			return body.string();
		} catch (IOException e) {
			throw ExceptionUtils.unchecked(e);
		}
	}

	@Override
	public String asString(Charset charset) {
		return new String(asBytes(), charset);
	}

	@Override
	public byte[] asBytes() {
		try {
			return body.bytes();
		} catch (IOException e) {
			throw ExceptionUtils.unchecked(e);
		}
	}

	@Override
	public InputStream asStream() {
		return body.byteStream();
	}

	@Override
	public JsonNode asJsonNode() {
		return JsonUtils.readTree(asBytes());
	}

	@Override
	public <T> T asValue(Class<T> valueType) {
		return JsonUtils.readValue(asBytes(), valueType);
	}

	@Override
	public <T> T asValue(TypeReference<T> typeReference) {
		return JsonUtils.readValue(asBytes(), typeReference);
	}

	@Override
	public <T> List<T> asList(Class<T> valueType) {
		return JsonUtils.readList(asBytes(), valueType);
	}

	@Override
	public <K, V> Map<K, V> asMap(Class<?> keyClass, Class<?> valueType) {
		return JsonUtils.readMap(asBytes(), keyClass, valueType);
	}

	@Override
	public <V> Map<String, V> asMap(Class<?> valueType) {
		return this.asMap(String.class, valueType);
	}

	@Override
	public File toFile(File file) {
		toFile(file.toPath());
		return file;
	}

	@Override
	public Path toFile(Path path) {
		try {
			Files.copy(this.asStream(), path);
			return path;
		} catch (IOException e) {
			throw ExceptionUtils.unchecked(e);
		}
	}

	@Override
	public MediaType contentType() {
		return body.contentType();
	}

	@Override
	public long contentLength() {
		return body.contentLength();
	}

	@Override
	public String toString() {
		return response.toString();
	}

	static ResponseBody ifNullBodyToEmpty(@Nullable ResponseBody body) {
		return body == null ? Util.EMPTY_RESPONSE : body;
	}

	@Override
	public void close() throws IOException {
		Util.closeQuietly(this.body);
	}
}
