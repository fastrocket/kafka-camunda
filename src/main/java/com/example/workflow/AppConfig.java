package com.example.workflow;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka")
public class AppConfig {
    private String topic = "users";
}
