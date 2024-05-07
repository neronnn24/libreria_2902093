package com.app.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class TestAuthController {

    @GetMapping("/Hello")
    public String hello(){
        return "Hello World";

    }

    @GetMapping("/Hello-secured")
    public String helloSecured(){
        return "Hello World Secured";

    }
}