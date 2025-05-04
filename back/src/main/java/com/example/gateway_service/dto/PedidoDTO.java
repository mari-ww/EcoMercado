package com.example.gateway_service.dto;

import com.example.gateway_service.model.Pedido;

import java.time.LocalDateTime;
import java.util.*;

public class PedidoDTO {
    private int id;
    private LocalDateTime data;
    private String status;
    private double total;
    private double impactoAmbiental;
    private List<ItemCarrinhoDTO> itens;

    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.data = pedido.getData();
        this.status = pedido.getStatus();
        this.total = pedido.calcularTotal();
        this.impactoAmbiental = pedido.calcularImpactoAmbiental();
        this.itens = pedido.getItens().stream()
                .map(ItemCarrinhoDTO::new)
                .toList();
    }

    // getters
}