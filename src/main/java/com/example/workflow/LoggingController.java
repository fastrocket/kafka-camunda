package com.example.workflow;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/log")
public class LoggingController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    GenericSender genericSender;

    @GetMapping(value = "/public")
    public String start(@RequestParam("text") @NotEmpty String text, HttpServletRequest request) {
        String remote = request.getRemoteAddr();
        genericSender.sendPublic(remote, text);
        return "PUBLIC API key: " + remote + " value:" + text;
    }

//    @PostMapping(value = "/private")
//    public String modify(@RequestBody("text") @NotEmpty String text, HttpServletRequest request) {
//        String remote = request.getRemoteAddr();
//        genericSender.sendPublic(remote, text);
//        return "PUBLIC published key: " + remote + " value:" + text;
//    }

}

