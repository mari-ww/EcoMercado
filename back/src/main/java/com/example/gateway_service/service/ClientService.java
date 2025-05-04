package com.example.gateway_service.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.gateway_service.model.Client;

@Service
public class ClientService {

    private final Map<Integer, Client> clientes = new HashMap<>();
    private int nextId = 1;
    private final PasswordEncoder passwordEncoder;

    public ClientService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Client createClient(Client cliente) {
        cliente.setId(nextId++);
        cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
        clientes.put(cliente.getId(), cliente);
        return cliente;
    }

    public Client getClientById(int id) {
        return clientes.get(id);
    }

    public boolean authenticate(String email, String password) {
        return clientes.values().stream()
                .anyMatch(cliente -> cliente.getEmail().equals(email) &&
                                    passwordEncoder.matches(password, cliente.getPassword()));
    }
}