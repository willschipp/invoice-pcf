package io.pivotal.poc.einvoice.gateway;

public interface RemoteGateway {

	boolean exists(String signature);
	
}
