package io.pivotal.poc.einvoice.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.channel.interceptor.ChannelInterceptorAdapter;
import org.springframework.integration.support.MessageBuilder;

public class MessageSupportInterceptor extends ChannelInterceptorAdapter {

	@Autowired
	private MessageSupport messageSupport;
	
	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		messageSupport.addMessage(message);
		return message;
	}

	@Override
	public Message<?> postReceive(Message<?> message, MessageChannel channel) {
		Message<?> lastMessage = messageSupport.getLastAdded();
		message = MessageBuilder.fromMessage(message).setHeader("fileMessageId", lastMessage.getPayload()).build();
		return super.postReceive(message, channel);
	}

}
