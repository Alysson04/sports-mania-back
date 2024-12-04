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

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Cliente;
import com.example.demo.service.ClienteService;

import jakarta.persistence.EntityNotFoundException;

@RequestMapping("api/cliente")
@RestController
public class ClienteController {
    
  @Autowired
    ClienteService clienteService;
    // Endpoint para listar todos os clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> findAllClientes() {
        List<Cliente> clientes = clienteService.findAll();
        return ResponseEntity.ok(clientes);
    }

    // Endpoint para buscar cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) throws ResourceNotFoundException {
        Cliente cliente = clienteService.getById(id);
        return ResponseEntity.ok(cliente);
    }

    // Endpoint para deletar cliente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClienteById(@PathVariable Long id) throws ResourceNotFoundException {
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build(); // Retorna um 204 No Content após a remoção
    }

    // Endpoint para atualizar cliente por ID
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateClienteById(@PathVariable Long id, @RequestBody Cliente clienteAtualizado) {
        Cliente cliente = clienteService.updateById(id, clienteAtualizado);
        return ResponseEntity.ok(cliente);
    }

    // Buscar cliente por nome
    @GetMapping("/{nome}")
    public Cliente getClienteByNome(@PathVariable("nome") String nome) {
        return clienteService.findByNome(nome);
    }

    // Buscar cliente por CPF
    @GetMapping("/{cpf}")
    public Cliente getClienteByCpf(@PathVariable("cpf") String cpf) {
        return clienteService.findByCpf(cpf);
    }

    // Inserir cliente
    @PostMapping("/add")
    public void insertCliente(@RequestBody Cliente cliente) {
        clienteService.insertBycliente(cliente);
    }

    // Atualizar cliente por nome
     @PutMapping("/update/nome/{name}")
    public Cliente updateByNome(@PathVariable String nome, @RequestBody Cliente updatedCliente) throws ResourceNotFoundException {
        return clienteService.updateByNome(nome, updatedCliente);
    }

    // Deletar cliente por nome
    @DeleteMapping("/nome/{nome}/delete")
    public void deleteClienteByNome(@PathVariable("nome") String nome) {
        try {
            clienteService.deleteByNome(nome);
        } catch (EntityNotFoundException e) {
            // Trate a exceção adequadamente (pode registrar ou responder com uma mensagem)
        }
    }

    // Deletar cliente por CPF
    @DeleteMapping("/cpf/{cpf}/delete")
    public void deleteClienteByCpf(@PathVariable("cpf") String cpf) {
        try {
            clienteService.deleteByCpf(cpf);
        } catch (EntityNotFoundException e) {
            // Trate a exceção adequadamente (pode registrar ou responder com uma mensagem)
        }
    }
}
