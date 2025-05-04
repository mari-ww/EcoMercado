package com.example.gateway_service.controller;

import com.example.gateway_service.dto.PedidoDTO;
import com.example.gateway_service.model.Pedido;
import com.example.gateway_service.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService service) {
        this.pedidoService = service;
    }

    // Criar pedido com base no carrinho do usuário
    @PostMapping("/usuario/{clienteId}")
    public ResponseEntity<Pedido> criar(@PathVariable int clienteId) {
        Pedido pedido = pedidoService.criarPedido(clienteId);
        return pedido != null ? ResponseEntity.status(201).body(pedido) : ResponseEntity.notFound().build();
    }

    // Atualizar status do pedido
    @PatchMapping("/{pedidoId}/status")
    public ResponseEntity<Pedido> atualizarStatus(@PathVariable int pedidoId,
                                                  @RequestParam String status) {
        Pedido pedido = pedidoService.atualizarStatus(pedidoId, status);
        return pedido != null ? ResponseEntity.ok(pedido) : ResponseEntity.notFound().build();
    }

    // Buscar todos os pedidos de um usuário
    @GetMapping("/usuario/{clienteId}")
    public ResponseEntity<List<PedidoDTO>> getPedidos(@PathVariable int clienteId) {
        List<Pedido> pedidos = pedidoService.getPedidosDoUsuario(clienteId);

        List<PedidoDTO> dtos = pedidos.stream()
                .map(PedidoDTO::new)
                .toList();

        return ResponseEntity.ok(dtos);
    }
}