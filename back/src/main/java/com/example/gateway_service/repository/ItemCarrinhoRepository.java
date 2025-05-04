package com.example.gateway_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gateway_service.model.ItemCarrinho;

public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Integer> {}