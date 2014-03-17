package io.pivotal.poc.einvoice.support;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.tika.Tika;
import org.springframework.core.io.FileSystemResource;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.channel.interceptor.ChannelInterceptorAdapter;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class MultiPartSupport extends ChannelInterceptorAdapter {

	private static final Logger logger = Logger.getLogger(MultiPartSupport.class);
	
	public MultiValueMap<String,Object> getMap(File file,Long id) {
		MultiValueMap<String,Object> map = new LinkedMultiValueMap<String,Object>();
		map.add("file", new FileSystemResource(file));
		map.add("id",id);
		try {
			map.add("Content-Type",new Tika().detect(file));
		} catch (IOException e) {
			logger.error(e);
		}
		return map;
		
	}

	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		return MessageBuilder.fromMessage(message).removeHeaders("Content-Type","content-type").build();
	}
	
}
