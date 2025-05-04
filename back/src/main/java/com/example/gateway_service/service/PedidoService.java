package com.example.gateway_service.service;

import com.example.gateway_service.model.Carrinho;
import com.example.gateway_service.model.Pedido;
import com.example.gateway_service.repository.CarrinhoRepository;
import com.example.gateway_service.repository.ItemCarrinhoRepository;
import com.example.gateway_service.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final CarrinhoService carrinhoService;
    private final CarrinhoRepository carrinhoRepository;

    public PedidoService(PedidoRepository repository,
                         CarrinhoService carrinhoService,
                         CarrinhoRepository carrinhoRepo,
                         ItemCarrinhoRepository itemRepo) {
        this.pedidoRepository = repository;
        this.carrinhoService = carrinhoService;
        this.carrinhoRepository = carrinhoRepo;
    }

    public Pedido criarPedido(int clienteId) {
        Carrinho carrinho = carrinhoService.getCarrinho(clienteId);

        if (carrinho == null || carrinho.getItens().isEmpty()) {
            return null;
        }

        Pedido pedido = new Pedido();
        pedido.setUser(carrinho.getUser());
        pedido.setItens(new ArrayList<>(carrinho.getItens()));
        pedido.setStatus("pendente");

        pedido = pedidoRepository.save(pedido); // Salva o pedido

        // Opcional: limpar o carrinho após o pedido ser criado
        carrinho.getItens().clear();
        carrinhoRepository.save(carrinho);

        return pedido;
    }

    public Pedido atualizarStatus(int pedidoId, String novoStatus) {
        Pedido pedido = pedidoRepository.findById(pedidoId).orElse(null);

        if (pedido != null) {
            pedido.atualizarStatus(novoStatus);
            return pedidoRepository.save(pedido);
        }

        return null;
    }

    public List<Pedido> getPedidosDoUsuario(int clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }
}