package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SayHelloDelegate implements JavaDelegate {
    private GenericSender sender;

    @Autowired
    public SayHelloDelegate(GenericSender sender){
        this.sender = sender;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String helloText = "Now is the time!!!";
        String text = "SayHelloDelegate setting variable hello: " + helloText;
        delegateExecution.setVariable("hello", text);
        System.out.println(text);
        System.out.println("SayHelloDelegate sending to Kafka text: " + text);
        sender.send(text);
    }
}
