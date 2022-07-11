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
package com.taotao.cloud.sys.biz.websockt.netty;

import com.taotao.cloud.netty.annotation.BeforeHandshake;
import com.taotao.cloud.netty.annotation.OnBinary;
import com.taotao.cloud.netty.annotation.OnClose;
import com.taotao.cloud.netty.annotation.OnError;
import com.taotao.cloud.netty.annotation.OnEvent;
import com.taotao.cloud.netty.annotation.OnMessage;
import com.taotao.cloud.netty.annotation.OnOpen;
import com.taotao.cloud.netty.annotation.PathVariable;
import com.taotao.cloud.netty.annotation.RequestParam;
import com.taotao.cloud.netty.annotation.ServerEndpoint;
import com.taotao.cloud.netty.pojo.Session;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.timeout.IdleStateEvent;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.Map;

/**
 * MyWebSokcet
 *
 * @author shuigedeng
 * @version 2022.04 1.0.0
 * @since 2021/09/06 11:51
 */
@ServerEndpoint(path = "/netty/websocket", port = "8989", host = "0.0.0.0")
public class MyWebSocket {

	@BeforeHandshake
	public void handshake(Session session, HttpHeaders headers, @RequestParam String req,
		@RequestParam MultiValueMap reqMap, @PathVariable String arg, @PathVariable Map pathMap) {
		session.setSubprotocols("stomp");
		if (!"ok".equals(req)) {
			System.out.println("Authentication failed!");
			session.close();
		}
	}

	@OnOpen
	public void onOpen(Session session, HttpHeaders headers, @RequestParam String req,
		@RequestParam MultiValueMap reqMap, @PathVariable String arg, @PathVariable Map pathMap) {
		System.out.println("new connection");
		System.out.println(req);
	}

	@OnClose
	public void onClose(Session session) throws IOException {
		System.out.println("one connection closed");
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		throwable.printStackTrace();
	}

	@OnMessage
	public void onMessage(Session session, String message) {
		System.out.println(message);
		session.sendText("Hello Netty!");
	}

	@OnBinary
	public void onBinary(Session session, byte[] bytes) {
		for (byte b : bytes) {
			System.out.println(b);
		}
		session.sendBinary(bytes);
	}

	@OnEvent
	public void onEvent(Session session, Object evt) {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
			switch (idleStateEvent.state()) {
				case READER_IDLE:
					System.out.println("read idle");
					break;
				case WRITER_IDLE:
					System.out.println("write idle");
					break;
				case ALL_IDLE:
					System.out.println("all idle");
					break;
				default:
					break;
			}
		}
	}
}
