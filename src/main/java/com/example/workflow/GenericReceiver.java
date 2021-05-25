package com.example.workflow;

import org.slf4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class GenericReceiver {

    private static final Logger LOGGER = getLogger(GenericReceiver.class);

    @KafkaListener(topics = "${kafka.topic}")
    public void receive(String message) {
        LOGGER.info("received payload='{}'", message);
    }
}