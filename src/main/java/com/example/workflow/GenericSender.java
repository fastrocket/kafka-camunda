package com.example.workflow;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class GenericSender {

    private static final Logger LOGGER = getLogger(GenericSender.class);

    @Value("${kafka.topic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String payload) {
        String key = UUID.randomUUID().toString();
        LOGGER.info("SEND: sending payload='{}' to topic='{}' with key={}", payload, topic, key);
        kafkaTemplate.send(topic, key, payload);

    }

    public void send(String payload) {
        String key = UUID.randomUUID().toString();
        LOGGER.info("SEND: sending payload='{}' to topic='{}' with key={}", payload, topic, key);
        kafkaTemplate.send(this.topic, key, payload);
    }
}