package com.example.gateway_service.controller;

import com.example.gateway_service.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final HelloService service;

    // Injeta o HelloService via construtor
    public HelloController(HelloService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String hello() {
        return service.getGreeting();
    }
}
