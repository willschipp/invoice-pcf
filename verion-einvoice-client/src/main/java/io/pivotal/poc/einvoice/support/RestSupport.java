package io.pivotal.poc.einvoice.support;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.crypto.codec.Base64;

public class RestSupport extends SimpleClientHttpRequestFactory {

	@Override
	protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
		super.prepareConnection(connection, httpMethod);
		String authorization = "user:e0973b83-911d-45e5-9d38-b3fe8c52e695";
		byte[] authorizedBytes = Base64.encode(authorization.getBytes());
		connection.setRequestProperty("Authorization", "Basic " + new String(authorizedBytes));
	}

}
