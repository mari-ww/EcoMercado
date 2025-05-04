package com.example.gateway_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.gateway_service.model.Product;
import com.example.gateway_service.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository repository) {
        this.productRepository = repository;
    }

    public Product addProduct(Product product) {
        Product existe = productRepository.findByName(product.getName());
        if (existe  != null){
            existe.setQuantity(existe.getQuantity() + product.getQuantity());
            return productRepository.save(existe);
        }
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }
}