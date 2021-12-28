package com.example.workflow.Delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component(value = "TimerExpiredDelegate")
public class TimerExpiredDelegate implements JavaDelegate {

    @Override
    public void execute(final DelegateExecution delegateExecution) {
        boolean doIt = (boolean) delegateExecution.getVariable("doIt");
        String myVar = (String) delegateExecution.getVariable("hello");
        System.out.println("!!!!! TimerExpiredDelegate got variable 'hello' with value: " + myVar + " and doIt: " + doIt);
    }
}