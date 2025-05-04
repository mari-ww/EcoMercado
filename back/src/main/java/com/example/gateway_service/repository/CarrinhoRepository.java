package com.example.gateway_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gateway_service.model.Carrinho;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Integer> {
    Carrinho findByClienteId(int clienteId);
}