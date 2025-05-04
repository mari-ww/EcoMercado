package com.example.gateway_service.dto;

import com.example.gateway_service.model.ItemCarrinho;

public class ItemCarrinhoDTO {

    private int produtoId;
    private String nome;
    private double preco;
    private int quantidade;

    public ItemCarrinhoDTO(ItemCarrinho item) {
        this.produtoId = item.getProduto().getId();
        this.nome = item.getProduto().getName();
        this.preco = item.getProduto().getPrice();
        this.quantidade = item.getQuantidade();
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // getters
}