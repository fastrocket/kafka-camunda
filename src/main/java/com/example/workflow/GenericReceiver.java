package com.example.workflow;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static org.slf4j.LoggerFactory.getLogger;

@Slf4j
@Service
public class GenericReceiver {

    @KafkaListener(topics = "${kafka.topic}")
    public void receive(String message) {
        log.info("received payload='{}'", message);
    }
}