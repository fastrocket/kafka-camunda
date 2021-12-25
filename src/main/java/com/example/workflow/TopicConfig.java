package com.example.workflow;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class TopicConfig {
    public static final String USER_TOPIC = "users";

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic createUsersTopic() {
        return createNewTopic(USER_TOPIC, 1);
    }

    private NewTopic createNewTopic(String name, int partitions) {
        return TopicBuilder
                .name(name)
                .partitions(partitions)
                .replicas(1)
                .build();
    }

    private NewTopic createNewTopic(String name) {
        return createNewTopic(name, 2);
    }
}
