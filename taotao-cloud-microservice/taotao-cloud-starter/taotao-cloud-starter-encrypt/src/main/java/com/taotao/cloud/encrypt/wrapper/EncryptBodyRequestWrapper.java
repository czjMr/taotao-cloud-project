package com.taotao.cloud.encrypt.wrapper;


import com.taotao.cloud.encrypt.handler.EncryptHandler;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * 主体请求加密包装器
 *
 * @author shuigedeng
 * @version 2022.07
 * @since 2022-07-06 15:12:00
 */
public class EncryptBodyRequestWrapper extends HttpServletRequestWrapper {

	private byte[] body;
	private EncryptHandler encryptService;

	public EncryptBodyRequestWrapper(HttpServletRequest request, EncryptHandler encryptService) throws IOException, ServletException {
		super(request);
		this.encryptService = encryptService;
		ServletInputStream inputStream = request.getInputStream();
		String header = request.getHeader("Content-Length");
		if (header == null) {
			return;
		}
		int contentLength = Integer.parseInt(header);
		byte[] bytes = new byte[contentLength];
		int readCount = 0;
		while (readCount < contentLength) {
			readCount += inputStream.read(bytes, readCount, contentLength - readCount);
		}
		body = bytes;
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		byte[] decode = encryptService.decode(body);
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decode);
		return new ServletInputStream() {
			@Override
			public boolean isFinished() {
				return byteArrayInputStream.available() == 0;
			}

			@Override
			public boolean isReady() {
				return true;
			}

			@Override
			public void setReadListener(ReadListener readListener) {
			}

			@Override
			public int read() throws IOException {
				return byteArrayInputStream.read();
			}
		};
	}
}
