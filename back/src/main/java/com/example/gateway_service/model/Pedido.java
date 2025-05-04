package com.example.gateway_service.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    private List<ItemCarrinho> itens = new ArrayList<>();

    @Column(name = "data_pedido")
    private LocalDateTime data;

    @Column(name = "status")
    private String status; // Ex: "pendente", "pago", "enviado"

    public Pedido() {
        this.data = LocalDateTime.now();
        this.status = "pendente";
    }

    public Pedido(User user, List<ItemCarrinho> itens) {
        this.user = user;
        this.itens = itens;
        this.data = LocalDateTime.now();
        this.status = "pendente";
    }

    // Calcula o impacto ambiental total do pedido
    public double calcularImpactoAmbiental() {
        return itens.stream()
                .mapToDouble(item -> item.getProduto().getEnvironmentalImpact() * item.getQuantidade())
                .sum();
    }

    // Atualiza o status do pedido
    public void atualizarStatus(String novoStatus) {
        this.status = novoStatus;
    }

    // Calcula o preço total do pedido
    public double calcularTotal() {
        return itens.stream()
                .mapToDouble(ItemCarrinho::getTotal)
                .sum();
    }

    // Getters e setters
    public int getId() {
        return id;
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

    public LocalDateTime getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}