package com.example.gateway_service.service;

import org.springframework.stereotype.Service;

import com.example.gateway_service.model.Carrinho;
import com.example.gateway_service.model.ItemCarrinho;
import com.example.gateway_service.model.Product;
import com.example.gateway_service.repository.CarrinhoRepository;
import com.example.gateway_service.repository.ItemCarrinhoRepository;
import com.example.gateway_service.repository.ProductRepository;
import com.example.gateway_service.repository.UserRepository;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ItemCarrinhoRepository itemCarrinhoRepository;
    public CarrinhoService(CarrinhoRepository repository,
                           ProductRepository productRepo,
                           UserRepository userRepo,
                           ItemCarrinhoRepository itemCarrinhoRepo
                           ) {
        this.carrinhoRepository = repository;
        this.productRepository = productRepo;
        this.userRepository = userRepo;
        this.itemCarrinhoRepository = itemCarrinhoRepo;
    }

    public Carrinho adicionarProduto(int clienteId, int produtoId) {
        Carrinho carrinho = carrinhoRepository.findByClienteId(clienteId);
    
        if (carrinho == null) {
            carrinho = new Carrinho();
            carrinho.setClienteId(clienteId);
            carrinho.setUser(userRepository.findById(clienteId).orElse(null));
            carrinho = carrinhoRepository.save(carrinho);
        }
    
        Product produto = productRepository.findById(produtoId).orElse(null);
    
        if (produto != null && produto.estaDisponivel()) {
            boolean itemExiste = false;
    
            for (ItemCarrinho item : carrinho.getItens()) {
                if (item.getProduto().getId() == produto.getId()) {
                    item.setQuantidade(item.getQuantidade() + 1);
                    itemExiste = true;
                    break;
                }
            }
    
            if (!itemExiste) {
                ItemCarrinho item = new ItemCarrinho(produto, 1);
                item.setCarrinho(carrinho);
                carrinho.addProduto(item);
            }
    
            carrinhoRepository.save(carrinho);
        }
    
        return carrinho;
    }
    public Carrinho getCarrinho(int clienteId) {
        return carrinhoRepository.findByClienteId(clienteId);
    }
}