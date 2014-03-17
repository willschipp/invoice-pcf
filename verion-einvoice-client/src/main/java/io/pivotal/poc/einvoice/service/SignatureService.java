package io.pivotal.poc.einvoice.service;

import java.io.File;

public interface SignatureService {

	String generateSignature(File file);
	
}
