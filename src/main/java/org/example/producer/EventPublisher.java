package org.example.producer;

import org.example.model.AccountCreatedEvent;
import org.example.model.UserCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventPublisher {

    private final KafkaTemplate<String, UserCreatedEvent> userCreatedKafkaTemplate;
    private final KafkaTemplate<String, AccountCreatedEvent> accountCreatedKafkaTemplate;

    public EventPublisher(KafkaTemplate<String, UserCreatedEvent> userCreatedKafkaTemplate,
                          KafkaTemplate<String, AccountCreatedEvent> accountCreatedKafkaTemplate) {
        this.userCreatedKafkaTemplate = userCreatedKafkaTemplate;
        this.accountCreatedKafkaTemplate = accountCreatedKafkaTemplate;
    }

    public void publishUserCreated(UserCreatedEvent event) {
        userCreatedKafkaTemplate.send("user-created-topic", event);
    }

    public void publishAccountCreated(AccountCreatedEvent event) {
        accountCreatedKafkaTemplate.send("account-created-topic", event);
    }
}
