package com.example.workflow;

import com.example.workflow.models.DomainName;
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

//    @Bean
//    public ConcurrentKafkaListenerContainerFactory kafkaListenerContainerFactory(
//            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
//            ConsumerFactory<Object, Object> kafkaConsumerFactory,
//            KafkaTemplate<Object, Object> template) {
//        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        configurer.configure(factory, kafkaConsumerFactory);
//        factory.setErrorHandler(new SeekToCurrentErrorHandler(
//                new DeadLetterPublishingRecoverer(template), () -> 3));
//        return factory;
//    }

//    @DltHandler
//    void handler(Message<?> msg,
//                 @Header(KafkaHeaders.ORIGINAL_OFFSET) byte[] offset,
//                 @Header(KafkaHeaders.EXCEPTION_FQCN) String descException,
//                 @Header(KafkaHeaders.EXCEPTION_STACKTRACE) String stacktrace,
//                 @Header(KafkaHeaders.EXCEPTION_MESSAGE) String errorMessage) {
//        System.out.println(msg);
//        System.out.println(ByteBuffer.wrap(offset).getLong());
//        System.out.println(descException);
//        System.out.println(stacktrace);
//        System.out.println(errorMessage);
//    }

//    @KafkaListener(topics = "public")
//    public void receivePublic(@Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
//                              @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
//                              @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
//                              @Header(KafkaHeaders.OFFSET) String offset,
//                              @Payload String message
//    ) {
//        log.info("PUBLIC RECEIVE: received topic={} key={} partition={}, offset={}, payload='{}'",
//                topic, key, partition, offset, message);
//    }

    @KafkaListener(topics = "modify_dns")
    public void receiveModifyDns(@Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                 @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                                 @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                                 @Header(KafkaHeaders.OFFSET) String offset,
                                 @Payload DomainName message
    ) {
        log.info("REC_MODIFY_DNS LISTENER: received topic={} key={} partition={}, offset={}, payload='{}'",
                topic, key, partition, offset, message);
    }
}