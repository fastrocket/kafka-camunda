//package com.example.workflow;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//// LINH NOTE: This does not work. It's either Camunda
//// or REST. People will use two Docker containers
//// to have both.
//@RestController
//@RequestMapping(value = "/user")
//public class UserController {
//    private final UserProducerService userProducerService;
//
//    @Autowired
//    public UserController(UserProducerService producerService) {
//        this.userProducerService = producerService;
//    }
//
//    @PostMapping(value = "/publish")
//    public void publish(@RequestParam("message") String message) {
//
//        this.userProducerService.sendMsg(message);
//    }
//
//    @PostMapping(value = "/createUser")
//    public void createUser(
//            @RequestParam("userId") long userId,
//            @RequestParam("firstName") String firstName,
//            @RequestParam("lastName") String lastName) {
//
//        User user = new User();
//        user.setUserId(userId);
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
//
//        this.userProducerService.sendUserLog(user);
//    }
//}