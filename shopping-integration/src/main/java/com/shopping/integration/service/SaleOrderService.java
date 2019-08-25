package com.shopping.integration.service;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;

@MessageEndpoint
public class SaleOrderService {

	@ServiceActivator
	public Message errorMeesage(Message message) {
		if(message.getPayload() instanceof MessagingException) {
			MessagingException messageException = (MessagingException) message.getPayload();
			return messageException.getFailedMessage();
		}
		System.out.println(message);
		return message;
	}
}
