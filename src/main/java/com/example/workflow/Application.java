package com.example.workflow;

import com.example.workflow.models.DnsCsv;
import com.example.workflow.models.DomainName;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.event.EventListener;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@EnableConfigurationProperties
@EnableProcessApplication
@SpringBootApplication
public class Application {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private GenericSender genericSender;

    @Autowired
    private Globals globals;

    @EventListener
    public void postDeployEvent(PostDeployEvent event) {
//        final String processName = "linh_process";
//        log.info("###### postDeployEvent starting Camunda process: {}", processName);
//        runtimeService.startProcessInstanceByKey(processName);
//
//        log.info("###### postDeployEvent starting timer");

        if (true) {
            log.info("postDeployEvent doing nothing...");
            return;
        }

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                genericSender.send("Timer beep");
            }
        }, 1000, 5 * 60 * 1000); // delay of 1 second, repeat every minute

        Timer timer2 = new Timer();
        timer2.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                final String key = UUID.randomUUID().toString();
                Big big = Big.builder()
                        .id(ThreadLocalRandom.current().nextInt())
                        .message("BIG data data data ")
                        .created(LocalDateTime.now())
                        .key(key)
                        .build();
                genericSender.sendBig(big);
            }
        }, 500, Math.abs(ThreadLocalRandom.current().nextInt()) % 60000 + 5000);  // delay of 1 second, repeat every minute

        Timer timer3 = new Timer();
        timer3.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
//                final String key = UUID.randomUUID().toString();
                DnsCsv dns = globals.getRandomDomain();
                DomainName domainName = DomainName.builder()
                        .id(ThreadLocalRandom.current().nextInt())
                        .name(dns.getName())
                        .build();
                genericSender.sendDns(domainName);
            }
        }, 500, Math.abs(ThreadLocalRandom.current().nextInt()) % 30000 + 10000);
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