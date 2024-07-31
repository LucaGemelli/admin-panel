package com.admin_panel.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendNotification(String message) {
        amqpTemplate.convertAndSend("notificationQueue", message);
    }
}
