package com.example.gateway_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.gateway_service.model.Product;

@Service
public class ImpactoAmbientalService {

    public double calcularImpacto(List<Product> produtos) {
        return produtos.stream()
                .mapToDouble(Product::getEnvironmentalImpact).sum();
    }
}