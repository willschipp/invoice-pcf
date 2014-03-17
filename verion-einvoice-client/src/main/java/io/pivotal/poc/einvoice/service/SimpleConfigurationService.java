package io.pivotal.poc.einvoice.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class SimpleConfigurationService implements ConfigurationService {

	private Map<String,String> map;
	
	public SimpleConfigurationService() {
		map = new HashMap<String,String>();
		map.put("url","http://localhost:8080/eInvoice");
		map.put("user","user");
		map.put("password","hello world");
	}
	
	@Override
	public Map<String, String> getConfiguration() {
		return map;
	}

}
