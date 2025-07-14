package org.example.consumer;

import org.example.model.AccountCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class AccountCreatedListener {

    @KafkaListener(
            topics = "account-created-topic",
            groupId = "account-created-group",
            containerFactory = "accountCreatedKafkaListenerContainerFactory"
    )
    public void handleAccountCreated(AccountCreatedEvent event) {
        System.out.println("Received AccountCreatedEvent: " + event);
    }
}
