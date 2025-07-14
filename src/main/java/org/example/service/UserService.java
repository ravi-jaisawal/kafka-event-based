package org.example.service;

import org.example.entity.User;
import org.example.model.UserCreatedEvent;
import org.example.producer.UserEventProducer;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;

    @Autowired
    UserEventProducer producer;

    @Autowired
    UserRepository userRepository;


    public UserService(KafkaTemplate<String, UserCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public UserCreatedEvent createUser(User dto) {
        userRepository.save(dto);
        UserCreatedEvent user = new UserCreatedEvent(null, dto.getName(), dto.getEmail());
        producer.sendUserCreatedEvent(user);
        return user;
    }

    public UserCreatedEvent registerUser(User dto) {
        userRepository.save(dto);
        UserCreatedEvent user = new UserCreatedEvent(null, dto.getName(), dto.getEmail());
        producer.sendUserRegistrationEvent(user);
        return user;
    }


}
