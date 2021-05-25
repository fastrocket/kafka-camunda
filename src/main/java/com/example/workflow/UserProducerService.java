package com.example.workflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserProducerService
{
    private static final Logger logger =
            LoggerFactory.getLogger(UserProducerService.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMsg(String message)
    {
        logger.info(String.format("Message sent -> %s", message));
        this.kafkaTemplate.send(MyConstants.TOPIC_TEST, message);
    }

    public void sendUserLog(User user)
    {
        logger.info(String.format("User created -> %s", user));
        this.kafkaTemplate.send(MyConstants.TOPIC_LOG, user);
    }
}