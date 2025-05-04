package com.example.gateway_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "itens_carrinho")
public class ItemCarrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Product produto;

    private int quantidade;
    private double precoNaCompra;

    @ManyToOne
    // @OnDelete(name = "FK_CARRINHO_ID", action = null)
    @JoinColumn(name= "carrinho_id")
    private Carrinho carrinho;


    public ItemCarrinho() {}

    public ItemCarrinho(Product produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoNaCompra = produto.getPrice();
    }

    public double getTotal() {
        return produto.getPrice() * quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduto() {
        return produto;
    }

    public void setProduto(Product produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoNaCompra() {
        return precoNaCompra;
    }

    public void setPrecoNaCompra(double precoNaCompra) {
        this.precoNaCompra = precoNaCompra;
    }

    // Getters e Setters

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }
}