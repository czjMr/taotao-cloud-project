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
package com.taotao.cloud.rxjava.mvc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import io.reactivex.Single;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Tests the {@link SingleReturnValueHandler} class.
 *
 * @author Jakub Narloch
 */
public class SingleReturnValueHandlerTest {

	@Value("${local.server.port}")
	private int port = 0;

	private TestRestTemplate restTemplate = new TestRestTemplate();

	@Configuration
	@EnableAutoConfiguration
	@RestController
	protected static class Application {

		@RequestMapping(method = RequestMethod.GET, value = "/single")
		public Single<String> single() {
			return Single.just("single value");
		}

		@RequestMapping(method = RequestMethod.GET, value = "/singleWithResponse")
		public Single<ResponseEntity<String>> singleWithResponse() {
			return Single.just(new ResponseEntity<String>("single value", HttpStatus.NOT_FOUND));
		}

		@RequestMapping(method = RequestMethod.GET, value = "/throw")
		public Single<Object> error() {
			return Single.error(new RuntimeException("Unexpected"));
		}
	}

	@Test
	public void shouldRetrieveSingleValue() {

		// when
		ResponseEntity<String> response = restTemplate.getForEntity(path("/single"), String.class);

		// then
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("single value", response.getBody());
	}

	@Test
	public void shouldRetrieveSingleValueWithStatusCode() {

		// when
		ResponseEntity<String> response = restTemplate.getForEntity(path("/singleWithResponse"),
			String.class);

		// then
		assertNotNull(response);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("single value", response.getBody());
	}

	@Test
	public void shouldRetrieveErrorResponse() {

		// when
		ResponseEntity<Object> response = restTemplate.getForEntity(path("/throw"), Object.class);

		// then
		assertNotNull(response);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

	private String path(String context) {
		return String.format("http://localhost:%d%s", port, context);
	}
}
