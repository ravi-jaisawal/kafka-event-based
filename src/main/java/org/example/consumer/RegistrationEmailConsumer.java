package org.example.consumer;

import org.example.dto.UserDto;
import org.example.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class RegistrationEmailConsumer {


    @Autowired
    EmailService emailService;

    @KafkaListener(topics = "user-registration", groupId = "email-service", containerFactory = "userKafkaListenerContainerFactory")
    public void consume(UserDto userDto) {
        emailService.sendWelcomeEmail(userDto.getEmail(), userDto.getName());
    }
}
