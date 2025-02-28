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
package com.taotao.cloud.rxjava.async;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

import com.taotao.cloud.rxjava.dto.EventDto;
import io.reactivex.Observable;
import java.util.Date;
import java.util.GregorianCalendar;
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
 * Tests the {@link ObservableSseEmitter} class.
 *
 * @author Jakub Narloch
 */
public class ObservableSseEmitterTest {

	@Value("${local.server.port}")
	private int port = 0;

	private TestRestTemplate restTemplate = new TestRestTemplate();

	@Configuration
	@EnableAutoConfiguration
	@RestController
	protected static class Application {

		@RequestMapping(method = RequestMethod.GET, value = "/sse")
		public ObservableSseEmitter<String> single() {
			return new ObservableSseEmitter<String>(Observable.just("single value"));
		}

		@RequestMapping(method = RequestMethod.GET, value = "/messages")
		public ObservableSseEmitter<String> messages() {
			return new ObservableSseEmitter<String>(
				Observable.just("message 1", "message 2", "message 3"));
		}

		@RequestMapping(method = RequestMethod.GET, value = "/events")
		public ObservableSseEmitter<EventDto> event() {
			return new ObservableSseEmitter<EventDto>(APPLICATION_JSON_UTF8, Observable.just(
				new EventDto("Spring.io", getDate(2016, 5, 11)),
				new EventDto("JavaOne", getDate(2016, 9, 22))
			));
		}
	}

	@Test
	public void shouldRetrieveSse() {

		// when
		ResponseEntity<String> response = restTemplate.getForEntity(path("/sse"), String.class);

		// then
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("data:single value\n\n", response.getBody());
	}

	@Test
	public void shouldRetrieveSseWithMultipleMessages() {

		// when
		ResponseEntity<String> response = restTemplate.getForEntity(path("/messages"),
			String.class);

		// then
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("data:message 1\n\ndata:message 2\n\ndata:message 3\n\n", response.getBody());
	}

	@Test
	public void shouldRetrieveJsonOverSseWithMultipleMessages() {

		// when
		ResponseEntity<String> response = restTemplate.getForEntity(path("/events"), String.class);

		// then
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	private String path(String context) {
		return String.format("http://localhost:%d%s", port, context);
	}

	private static Date getDate(int year, int month, int day) {
		return new GregorianCalendar(year, month, day).getTime();
	}
}
