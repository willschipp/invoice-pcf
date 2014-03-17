package io.pivotal.poc.einvoice.support;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;

import org.apache.log4j.Logger;
import org.springframework.integration.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageSupport {

	private static final Logger logger = Logger.getLogger(MessageSupport.class);
	
	private Deque<Message<?>> messages;
	
	public MessageSupport() {
		messages = new LinkedBlockingDeque<Message<?>>();
	}
	
	public void addMessage(Message<?> message) {
		messages.addFirst(message);
		logger.info("message added " + messages);
	}
	
	public Message<?> getLastAdded() {
		logger.info("return messages " + messages);
		return ((LinkedList<Message<?>>)messages).pollFirst();
	}
	
}
