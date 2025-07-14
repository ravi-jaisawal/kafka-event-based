package org.example.config;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.example.model.AccountCreatedEvent;
import org.example.model.UserCreatedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    private final String BOOTSTRAP_SERVERS = "localhost:9092";

    // ===== Generic Producer Factory =====
    public <T> ProducerFactory<String, T> producerFactory(Class<T> type) {
        Map<String, Object> config = new HashMap<>();
        config.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        config.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }

    public <T> KafkaTemplate<String, T> kafkaTemplate(Class<T> type) {
        return new KafkaTemplate<>(producerFactory(type));
    }

    // ===== Generic Consumer Factory =====
    public <T> ConsumerFactory<String, T> consumerFactory(Class<T> type, String groupId) {
        Map<String, Object> config = new HashMap<>();
        config.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        config.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, groupId);
        config.put(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        return new DefaultKafkaConsumerFactory<>(
                config,
                new StringDeserializer(),
                new JsonDeserializer<>(type));
    }

    public <T> ConcurrentKafkaListenerContainerFactory<String, T> kafkaListenerFactory(Class<T> type, String groupId) {
        ConcurrentKafkaListenerContainerFactory<String, T> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(type, groupId));
        return factory;
    }

    // ===== Specific Beans for Event Types =====

    @Bean
    public KafkaTemplate<String, UserCreatedEvent> userCreatedKafkaTemplate() {
        return kafkaTemplate(UserCreatedEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UserCreatedEvent> userCreatedKafkaListenerContainerFactory() {
        return kafkaListenerFactory(UserCreatedEvent.class, "user-created-group");
    }

    @Bean
    public KafkaTemplate<String, AccountCreatedEvent> accountCreatedKafkaTemplate() {
        return kafkaTemplate(AccountCreatedEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, AccountCreatedEvent> accountCreatedKafkaListenerContainerFactory() {
        return kafkaListenerFactory(AccountCreatedEvent.class, "account-created-group");
    }
}