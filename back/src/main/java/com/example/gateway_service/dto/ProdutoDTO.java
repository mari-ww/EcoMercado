package com.example.gateway_service.dto;

import com.example.gateway_service.model.Product;

public class ProdutoDTO {
    private int id;
    private String nome;
    private double preco;

    public ProdutoDTO(Product produto) {
        this.id = produto.getId();
        this.nome = produto.getName();
        this.preco = produto.getPrice();
    }
}