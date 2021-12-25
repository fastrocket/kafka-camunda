package com.example.workflow;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {
    public static final String USER_TOPIC = "users";

    @Bean
    public NewTopic businessProcessEngineError() {
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
