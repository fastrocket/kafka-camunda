package com.example.workflow;
import javax.ws.rs.ApplicationPath;

import org.camunda.bpm.engine.rest.impl.CamundaRestResources;
import org.camunda.bpm.spring.boot.starter.rest.CamundaJerseyResourceConfig;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/engine-rest")
public class JerseyConfig extends CamundaJerseyResourceConfig {

    @Override
    protected void registerAdditionalResources() {
      register(DnsController.class);
      CamundaRestResources.getResourceClasses().add(DnsController.class);
    }

}