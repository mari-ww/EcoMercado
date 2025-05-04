package com.example.gateway_service.service;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String getGreeting() {
        // Aqui poderia ter regras mais complexas
        return "Hello World!";
    }
}
