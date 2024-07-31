package com.admin_panel.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @RabbitListener(queues = "notificationQueue")
    public void receiveNotification(String message) {
        System.out.println("Received notification: " + message);
    }
}
