package org.example.consumer;


import org.example.model.UserCreatedEvent;
import org.example.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.KafkaListeners;
import org.springframework.stereotype.Service;

@Service
public class UserEventConsumer {

    @Autowired
    EmailService emailService;

    @KafkaListener(topics = "user-events", groupId = "user-events-group", containerFactory = "userKafkaListenerContainerFactory")
    public void consume(UserCreatedEvent event) {
        System.out.println("Received event: " + event.getUserId() + ", email: " + event.getEmail());
        // Handle event here
        emailService.sendWelcomeEmail(event.getEmail(), event.getName());
    }
}
