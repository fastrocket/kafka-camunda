package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component(value = "MakeItSoDelegate")
public class MakeItSoDelegate implements JavaDelegate {

    @Override
    public void execute(final DelegateExecution delegateExecution) {

        String myVar = (String)delegateExecution.getVariable("hello");
        System.out.println("!!!!! MakeItSoDelegate got variable 'hello' with value: " + myVar);
    }
}