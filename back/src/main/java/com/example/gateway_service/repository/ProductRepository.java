package com.example.gateway_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gateway_service.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    public Product findByName(String name);
}