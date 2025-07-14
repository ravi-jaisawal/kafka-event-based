package org.example.consumer;

import org.example.model.UserCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserCreatedListener {

    @KafkaListener(
            topics = "user-created-topic",
            groupId = "user-created-group",
            containerFactory = "userCreatedKafkaListenerContainerFactory"
    )
    public void handleUserCreated(UserCreatedEvent event) {
        System.out.println("Received UserCreatedEvent: " + event);
    }
}
