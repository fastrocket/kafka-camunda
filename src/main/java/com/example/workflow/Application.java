package com.example.workflow;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

@EnableProcessApplication
@SpringBootApplication
public class  Application {

  public static void main(String... args) {
    SpringApplication.run(Application.class, args);
  }

  @Autowired
  private RuntimeService runtimeService;

  @EventListener
  private void processPostDeploy(PostDeployEvent event) {
    System.out.println("###### processPostDeploy!");
    runtimeService.startProcessInstanceByKey("process");
  }
}

// Kafka config

//@Configuration
//class KafkaProducerConfig {
//
//  @Value("${io.reflectoring.kafka.bootstrap-servers}")
//  private String bootstrapServers;
//
//  @Bean
//  public Map<String, Object> producerConfigs() {
//    Map<String, Object> props = new HashMap<>();
//    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
//            bootstrapServers);
//    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
//            StringSerializer.class);
//    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
//            StringSerializer.class);
//    return props;
//  }
//
//  @Bean
//  public ProducerFactory<String, String> producerFactory() {
//    return new DefaultKafkaProducerFactory<>(producerConfigs());
//  }
//
//  @Bean
//  public KafkaTemplate<String, String> kafkaTemplate() {
//    return new KafkaTemplate<>(producerFactory());
//  }
//}