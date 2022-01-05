package com.example.workflow.Delegates;

import com.example.workflow.GenericSender;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component(value = "SayHelloDelegate")
public class SayHelloDelegate implements JavaDelegate {
    private GenericSender sender;

    @Autowired
    public SayHelloDelegate(GenericSender sender) {
        this.sender = sender;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Random rand = new Random();
        int rint = rand.nextInt();
        String helloText = "Now is the time!!!";
        String text = "SayHelloDelegate (random " + rint + ") setting variable hello: " + helloText;
        delegateExecution.setVariable("hello", text);
        System.out.println(text);
        System.out.println("SayHelloDelegate sending to Kafka text: " + text);
        sender.send(text);

        // Randomly pick a path
        boolean doIt = rand.nextBoolean();
        System.out.println("SayHelloDelegate setting doIt= and random" + doIt);
        delegateExecution.setVariable("doIt", doIt);
    }
}
