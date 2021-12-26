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
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                        @Header(KafkaHeaders.OFFSET) String offset,
                        @Payload String message
    ) {
        log.info("RECEIVE: received topic={} key={} partition={}, offset={}, payload='{}'",
                topic, key, partition, offset, message);
    }

    @KafkaListener(topics = "bigger")
    public void receiveBig(@Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                           @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                           @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                           @Header(KafkaHeaders.OFFSET) String offset,
                           @Payload Big message
    ) {
        log.info("BIG RECEIVE: received topic={} key={} partition={}, offset={}, payload='{}'",
                topic, key, partition, offset, message);
    }
}