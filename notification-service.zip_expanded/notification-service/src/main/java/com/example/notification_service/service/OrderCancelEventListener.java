package com.example.notification_service.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderCancelEventListener {

private static final String TOPIC = "order.cancelled";
	
	@KafkaListener(topics = TOPIC, groupId = "notification-group")
	public void handleOrderEvent(String message) {
		System.out.println("🔔 Notification Received (cancel order): " + message);
		/*
		 * After recieve message, conver json smg to order object, send notificate for
		 * customer, also send order to other microservice like kitchen-service or
		 * deliver-service
		 */
	}
	
}
