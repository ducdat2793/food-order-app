package com.example.order_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderCancelEventProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	private static final String TOPIC = "order.cancelled";

	public void cancelOrderEvent(Long eventId) {
		try {
			kafkaTemplate.send(TOPIC, eventId + "");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
