package com.example.gateway_service.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.gateway_service.model.User;
import com.example.gateway_service.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository,PasswordEncoder passwordEncoder) {
        this.userRepository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    // Salva usuário no banco
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    // Retorna todos os usuários do banco
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Busca usuário por ID
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    // Autenticação via banco
    public boolean authenticate(String email, String senha) {
        User user = userRepository.findByEmail(email);
        return user != null && passwordEncoder.matches(senha, user.getPassword());
    }
}