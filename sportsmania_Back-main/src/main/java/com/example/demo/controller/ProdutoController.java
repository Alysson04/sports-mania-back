package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.produtoDTO.ProdutoRequestDTO;
import com.example.demo.dto.produtoDTO.ProdutoResponseDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.ProdutoService;


@RestController
@RequestMapping(value="/api/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;


//Response
    @GetMapping("/all")
    public List<ProdutoResponseDTO> getAllProdutos() {
        return produtoService.findAllProdutos();
    }
 

    @GetMapping("/{id}")
    public ProdutoResponseDTO getProdutoById(@PathVariable("id") Long id) {
        try {
            return produtoService.findById(id);
        } catch (ResourceNotFoundException e) {
            return null; 
        }
    }

    @GetMapping("/{nome}")
    public ResponseEntity<ProdutoResponseDTO> getProdutoByNome(@PathVariable String nome) throws ResourceNotFoundException {
        ProdutoResponseDTO produtoResponseDTO = produtoService.findProdutoByNome(nome);
        return ResponseEntity.ok(produtoResponseDTO);
    }

// Request
    @PostMapping("/add")
    public void insertProduto(@RequestBody ProdutoRequestDTO produtoRequestDTO) {
        produtoService.InsertProduto(produtoRequestDTO);
    }

    @PutMapping("/{id}")
    public ProdutoRequestDTO updateById(@PathVariable Long id, @RequestBody ProdutoRequestDTO updatedProduto) throws ResourceNotFoundException {
        return produtoService.updateById(id, updatedProduto);
    }

    @PutMapping("/update/{name}")
    public ProdutoRequestDTO updateByNome(@PathVariable String nome, @RequestBody ProdutoRequestDTO updatedProduto) throws ResourceNotFoundException {
        return produtoService.updateByNome(nome, updatedProduto);
    }

// Delete
    @DeleteMapping("/{id}/delete")
    public void deleteProdutoById(@PathVariable("id") Long id) {
        try {
            produtoService.deleteById(id);
        } catch (ResourceNotFoundException e) {

        }
    }
    
    @DeleteMapping("/{nome}/delete")
    public void deleteProdutoByNome(@PathVariable("nome") String nome) {
        try {
            produtoService.deleteByNome(nome);
        } catch (ResourceNotFoundException e) {
        }
    }

}
