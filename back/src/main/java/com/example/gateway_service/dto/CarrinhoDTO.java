package com.example.gateway_service.dto;

import java.util.List;

import com.example.gateway_service.model.Carrinho;

public class CarrinhoDTO {
    private int clienteId;
    // private String clienteNome;
    private List<ItemCarrinhoDTO> itens;
    private double total;

    public CarrinhoDTO(Carrinho carrinho) {
        this.clienteId = carrinho.getClienteId();
        // this.clienteNome = carrinho.get;
        this.itens = carrinho.getItens().stream().map(ItemCarrinhoDTO::new).toList();
        this.total = carrinho.totalizar();
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    // public String getClienteNome() {
    //     return clienteNome;
    // }

    // public void setClienteNome(String clienteNome) {
    //     this.clienteNome = clienteNome;
    // }

    public List<ItemCarrinhoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemCarrinhoDTO> itens) {
        this.itens = itens;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}