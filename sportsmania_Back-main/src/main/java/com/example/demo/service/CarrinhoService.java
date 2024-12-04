package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CarrinhoDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Carrinho;
import com.example.demo.model.Cliente;
import com.example.demo.model.Produto;
import com.example.demo.repository.CarrinhoRepository;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ClienteService clienteService; 

    // Buscar carrinho por cliente
    public CarrinhoDTO findCarrinhoByCliente(Long clienteId) throws ResourceNotFoundException {
        Cliente cliente = clienteService.getById(clienteId);
        if (cliente == null) {
            throw new ResourceNotFoundException("Cliente não encontrado com ID: " + clienteId);
        }

        Carrinho carrinho = carrinhoRepository.findByCliente(cliente);
        if (carrinho == null) {
            throw new ResourceNotFoundException("Nenhum carrinho encontrado para o cliente " + cliente.getNome());
        }

        return new CarrinhoDTO(carrinho.getCliente(), carrinho.getProdutos());
    }

    // (POST) Adicionar produto ao carrinho de um cliente
    public Carrinho adicionarProdutoCarrinho(Long clienteId, Produto produto) throws ResourceNotFoundException {
        Cliente cliente = clienteService.getById(clienteId);
        if (cliente == null) {
            throw new ResourceNotFoundException("Cliente não encontrado com ID: " + clienteId);
        }

        Carrinho carrinho = carrinhoRepository.findByCliente(cliente);
        if (carrinho == null) {
            carrinho = new Carrinho();
            carrinho.setCliente(cliente);
        }

        if (produto == null) {
            throw new ResourceNotFoundException("Produto inválido");
        }

        carrinho.getProdutos().add(produto); // Adiciona o produto ao carrinho
        return carrinhoRepository.save(carrinho);
    }

    // (DELETE) Remover produto do carrinho de um cliente
    public Carrinho removerProdutoCarrinho(Long clienteId, Produto produto) throws ResourceNotFoundException {
        Cliente cliente = clienteService.getById(clienteId);
        if (cliente == null) {
            throw new ResourceNotFoundException("Cliente não encontrado com ID: " + clienteId);
        }

        Carrinho carrinho = carrinhoRepository.findByCliente(cliente);
        if (carrinho == null) {
            throw new ResourceNotFoundException("Nenhum carrinho encontrado para o cliente " + cliente.getNome());
        }

        if (produto == null || !carrinho.getProdutos().contains(produto)) {
            throw new ResourceNotFoundException("Produto não encontrado no carrinho");
        }

        carrinho.getProdutos().remove(produto); // Remove o produto do carrinho
        return carrinhoRepository.save(carrinho);
    }

    // (PUT) Finalizar compra do carrinho de um cliente
    public Carrinho finalizarCompra(Long clienteId) throws ResourceNotFoundException {
        Cliente cliente = clienteService.getById(clienteId);
        if (cliente == null) {
            throw new ResourceNotFoundException("Cliente não encontrado com ID: " + clienteId);
        }

        Carrinho carrinho = carrinhoRepository.findByCliente(cliente);
        if (carrinho == null) {
            throw new ResourceNotFoundException("Nenhum carrinho encontrado para o cliente " + cliente.getNome());
        }

        // Limpa os produtos do carrinho após a finalização
        carrinho.getProdutos().clear(); 
        return carrinhoRepository.save(carrinho); // Salva o carrinho "limpo"
    }
}