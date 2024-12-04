package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.dto.EstoqueDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Estoque;
import com.example.demo.model.Produto;
import com.example.demo.repository.EstoqueRepository;
import com.example.demo.repository.ProdutoRepository;



@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ProdutoRepository produtoRepository;



    // GET

    public void criarEstoque(Estoque estoqueModel){
        estoqueRepository.save(estoqueModel);
    }

    public Produto findProdutosByNome(String nome) throws ResourceNotFoundException {
        Produto produtos = produtoRepository.findByNome(nome);
        
        if (produtos == null) {
            throw new ResourceNotFoundException("Nenhum produto com o nome " + nome + " foi encontrado no estoque");
        }
        
        return produtos;
    }

    // POST
    /*cadastrar produto no estoque */
    public void cadastrarProduto(Long estoqueId, Produto novoProduto) throws ResourceNotFoundException {
        Optional<Estoque> optionalEstoque = estoqueRepository.findById(estoqueId);
        if (optionalEstoque.isEmpty()) {
            throw new ResourceNotFoundException("Estoque com id " + estoqueId + " não encontrado.");
        }

        Estoque estoque = optionalEstoque.get();

     // olhar se tem produto com msm nome no estoque
        Produto produtoExistente = produtoRepository.findByNome(novoProduto.getNome());

        if (produtoExistente == null) {
            // add um produto não cadastrado no estoque
            novoProduto.setEstoque(estoque);
            produtoRepository.save(novoProduto);
        }
    }

    

    // UPDATE
   /*aumentar a quantidade de produtos*/ 
   public void addProduto(Long estoqueId, Produto novoProduto) throws ResourceNotFoundException{
        Optional<Estoque> optionalEstoque = estoqueRepository.findById(estoqueId);
        if (optionalEstoque.isEmpty()) {
            throw new ResourceNotFoundException("Estoque com id " + estoqueId + " não encontrado.");
        }

        Estoque estoque = optionalEstoque.get();

        // olhar se tem produto com msm nome no estoque
        Produto produtoExistente = produtoRepository.findByNome(novoProduto.getNome());
        if (produtoExistente != null && produtoExistente.getEstoque().getId().equals(estoque.getId())) {
            // aumentar qntd de produto se ja tiver em estoque
            produtoExistente.incrementarQuantidade(novoProduto.getQuantidade());
            produtoRepository.save(produtoExistente); 
        } 
    }

    /*reduzir quantidade de produtos */
    public void reduzirProduto(Long estoqueId, Produto novoProduto) throws ResourceNotFoundException{
        Optional<Estoque> optionalEstoque = estoqueRepository.findById(estoqueId);
        if (optionalEstoque.isEmpty()) {
            throw new ResourceNotFoundException("Estoque com id " + estoqueId + " não encontrado.");
        }

        Estoque estoque = optionalEstoque.get();

        // olhar se tem produto com msm nome no estoque
        Produto produtoExistente = produtoRepository.findByNome(novoProduto.getNome());
        if (produtoExistente != null && produtoExistente.getEstoque().getId().equals(estoque.getId())) {
            // aumentar qntd de produto se ja tiver em estoque
            produtoExistente.decrementarQuantidade(novoProduto.getQuantidade());
            produtoRepository.save(produtoExistente); 
        } 
    }
   
    // DELETE
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
}

