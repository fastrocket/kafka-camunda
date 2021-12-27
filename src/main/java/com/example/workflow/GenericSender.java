package com.example.workflow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.UUID;

import com.example.workflow.models.DomainName;

@Slf4j
@Service
public class GenericSender {

    @Value("${kafka.topic}")
    private String _topic;

    @Autowired
    private KafkaTemplate<String, DomainName> domainTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Big> bigTemplate;

    public void send(String topic, String payload) {

        doSend(topic, null, payload);
    }

    public void send(String payload) {

        doSend(null, null, payload);
    }

    public void sendBig(Big payload) {
        String hostname;
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ex) {
            hostname = "Unknown";
        }
        payload.setMessage(hostname + " -> " + payload.getMessage());
        log.info("SENDBIG: payload={}", payload);
        bigTemplate.send("bigger", payload.getKey(), payload);
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
        log.info("SEND: sending payload='{}' to topic='{}' with key={}", payload, topic, key);
        kafkaTemplate.send(topic, key, payload);
    }

    public void sendDns(DomainName domainName){
        log.info("sendDns sent DomainName to modify_dns: " + domainName);
        domainTemplate.send("modify_dns", UUID.randomUUID().toString(), domainName);
    }
}