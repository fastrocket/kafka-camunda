package com.example.workflow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GenericReceiver {

    @KafkaListener(topics = "${kafka.topic}")
    public void receive(@Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                        @Payload String message) {
        log.info("received topic={} key={} payload='{}'", topic, key, message);
    }
}