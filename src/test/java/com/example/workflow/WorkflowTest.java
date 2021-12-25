package com.example.workflow;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WorkflowTest extends AbstractProcessEngineRuleTest {

    @Autowired
    public RuntimeService runtimeService;

    @Autowired
    TopicConfig topicConfig;

    @Test
    public void shouldExecuteHappyPath() {
        // given
        String processDefinitionKey = "linh_process";

        // when
        topicConfig.createUsersTopic();

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey);

        // then
//        assertThat(processInstance).isEnded();//.isStarted();
//        .task()
//        .hasDefinitionKey("say-hello")
//        .hasCandidateUser("demo")
//        .isNotAssigned();
    }

}
