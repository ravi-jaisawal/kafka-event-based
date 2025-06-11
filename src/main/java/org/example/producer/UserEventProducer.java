package org.example.producer;

import org.example.model.User;
import org.example.model.UserCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserEventProducer {

    private final KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;

    public UserEventProducer(KafkaTemplate<String, UserCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUserCreatedEvent(UserCreatedEvent event) {
        kafkaTemplate.send("user-events", event.getUserId(), event);
    }

    public void sendUserRegistrationEvent(UserCreatedEvent saved) {
        kafkaTemplate.send("user-registration", new UserCreatedEvent(saved.getName(), saved.getEmail()));
    }
}
