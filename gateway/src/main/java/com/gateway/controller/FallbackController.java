package com.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {
    

    @GetMapping("/users")
    public ResponseEntity<String> userFallback(){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("User service está indisponível. Tente novamente mais tarde.");
    }
    @GetMapping("/products")
    public ResponseEntity<String> productFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Serviço de produtos está temporariamente indisponível. Por favor, tente mais tarde.");
    }

    @GetMapping("/entrega")
    public ResponseEntity<String> entregaFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Serviço de entrega está temporariamente indisponível. Por favor, tente mais tarde.");
    }

}
