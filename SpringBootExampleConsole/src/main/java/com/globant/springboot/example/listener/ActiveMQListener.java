package com.globant.springboot.example.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
		
@Component
public class ActiveMQListener {
	public static final Logger LOGGER = LoggerFactory.getLogger(ActiveMQListener.class);

	@JmsListener(destination = "spring.boot.console.inbound.queue")
	public void receiveMessage(final Message message) throws JMSException {
		LOGGER.info("Received message {}", message);
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
			final String messageData = textMessage.getText();
			LOGGER.info("Text message {}", messageData);
		}
	}
}
