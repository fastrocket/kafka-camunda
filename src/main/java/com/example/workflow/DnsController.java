package com.example.workflow;

import com.example.workflow.models.DomainName;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/dns")
public class DnsController {

    @Autowired
    private RuntimeService runtimeService;
    
    @Autowired
    GenericSender genericSender;

    @GetMapping(value = "/start")
    public String start(@RequestParam String name) {
        if(name == null) {
            name = "linh_process";
        }
        runtimeService.startProcessInstanceByKey(name);
        return "started " + name;
    }

    @PostMapping(value ="/modify")
    public String modify(@RequestBody DomainName domainName)
    {
        genericSender.sendDns(domainName);
        return "Sent to Kafka " + domainName.getName();
    }

}

