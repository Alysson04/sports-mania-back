package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Cliente;
import com.example.demo.model.Estoque;
import com.example.demo.model.Funcionario;
import com.example.demo.model.Produto;
import com.example.demo.model.Venda;
import com.example.demo.repository.EstoqueRepository;
import com.example.demo.repository.ProdutoRepository;
import com.example.demo.repository.VendaRepository;


@Service
public class  VendaService {
    @Autowired
    private VendaRepository vendaRepository;

      @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;



    public Venda insertVenda(Venda venda) {
        return vendaRepository.save(venda);
    } 
    

    public void removerProdutoDoEstoque(Long estoqueId, Long produtoId) throws ResourceNotFoundException {
        Optional<Estoque> optionalEstoque = estoqueRepository.findById(estoqueId);
        
        if (optionalEstoque.isEmpty()) {
            throw new ResourceNotFoundException("Estoque com id " + estoqueId + " não encontrado.");
        }
    
        Estoque estoque = optionalEstoque.get();
    
        // olha se o produto existe no estoque
        Optional<Produto> optionalProduto = produtoRepository.findById(produtoId);
        
        if (optionalProduto.isEmpty() || !optionalProduto.get().getEstoque().getId().equals(estoque.getId())) {
            throw new ResourceNotFoundException("Produto com id " + produtoId + " não encontrado no estoque.");
        }
    
        Produto produto = optionalProduto.get();
        
        // remover
        produtoRepository.delete(produto);
    }


    public List<Venda> buscarVendasPorCliente(Cliente cliente) {
        return vendaRepository.findByCliente(cliente);
    }

      public List<Venda> buscarVendasPorFuncionario(Funcionario funcionario) {
        return vendaRepository.findByFuncionario(funcionario);
    }


    public List<Venda> buscarVendasPorProduto(Produto produto) {
        return vendaRepository.findByProduto(produto);
    }


    public List<Venda> buscarVendasPorValor(Double valor) {
        return vendaRepository.findByValor(valor);
    }

    public List<Venda> buscarVendasPorDesconto(Double desconto) {
        return vendaRepository.findByDesconto(desconto);
    }

    public void deletarVendasPorCliente(Cliente cliente) {
        vendaRepository.deleteByCliente(cliente);
    }


    public List<Venda> findAll(){
        return vendaRepository.findAll();
    }
    

     public Optional<Venda> getById(Long Id){
        return vendaRepository.findById(Id);
    }

    public void deleteById(Long Id){
        vendaRepository.deleteById(Id);
    }
    

    

    public Venda updateById(Long id, Venda venda) throws ResourceNotFoundException {
        if (vendaRepository.existsById(id)) {
            venda.setId(id);
            return vendaRepository.save(venda);
        } else {
            throw new ResourceNotFoundException("Venda com o " + id + " não existe.");
        }
    }
}
