package com.example.workflow;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class GenericSender {

    private static final Logger LOGGER = getLogger(GenericSender.class);

    @Value("${kafka.topic}")
    private String _topic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String payload) {

        doSend(topic, null, payload);
    }

    public void send(String payload) {

        doSend(null, null, payload);
    }

    private void doSend(String topic, String key, String payload) {
        String hostname;
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ex) {
            hostname = "Unknown";
        }

        if (topic == null) {
            topic = _topic;
        }

        if (key == null) {
            key = UUID.randomUUID().toString();
        }
        String now = LocalDateTime.now().toString();
        payload = hostname + ": " + now + " ->" + payload;
        LOGGER.info("SEND: sending payload='{}' to topic='{}' with key={}", payload, topic, key);
        kafkaTemplate.send(topic, key, payload);
    }
}