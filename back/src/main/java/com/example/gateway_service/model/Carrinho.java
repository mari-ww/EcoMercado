package com.example.gateway_service.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "carrinhos")


public class Carrinho {

    @Id
    private int clienteId;

    // 🔗 Vincula diretamente com a entidade User
    @OneToOne
    @MapsId
    @JoinColumn(name = "clienteId") // Nome da coluna FK na tabela 'carrinhos'
    private User user;

    // 📦 Lista de itens no carrinho
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "carrinho_id") // Coluna nas tabelas filhas que apontam para este carrinho
    private List<ItemCarrinho> itens = new ArrayList<>();

    public Carrinho() {
        // Necessário para o JPA
    }

    public Carrinho(User user) {
        this.user = user;
        this.clienteId = user.getId(); // O ID do carrinho é o mesmo do usuário
    }

    // Adiciona um item ao carrinho
    public void addProduto(ItemCarrinho item) {
        this.itens.add(item);
    }

    // Remove um produto do carrinho
    public void removeProduto(int produtoId) {
        this.itens.removeIf(item -> item.getProduto().getId() == produtoId);
    }

    // Calcula o total do carrinho
    public double totalizar() {
        return itens.stream()
                .mapToDouble(ItemCarrinho::getTotal)
                .sum();
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ItemCarrinho> getItens() {
        return itens;
    }

    public void setItens(List<ItemCarrinho> itens) {
        this.itens = itens;
    }
}