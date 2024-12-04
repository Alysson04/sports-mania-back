package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.demo.model.Funcionario;
import com.example.demo.model.Produto;
import com.example.demo.model.Venda;
import com.example.demo.service.VendaService;

@RestController
@RequestMapping("api/venda")
public class VendaController {
    @Autowired
    private VendaService vendaService;
   
    
    @GetMapping("/{id}")
    public java.util.Optional<Venda> findById(Long id){
        return vendaService.getById(id);
    }

    @GetMapping 
    public List<Venda> findAll(){
        return vendaService.findAll();
    }

    @PostMapping(value ="/add")
    public Venda insertVenda(@RequestBody Venda venda) {
        return vendaService.insertVenda(venda);
    }

    @DeleteMapping("{id}/delete")
    public void deleteVendaById(@PathVariable Long id){
        vendaService.deleteById(id);
    }

     @GetMapping("/{clienteId}")
    public List<Venda> getVendasPorCliente(@PathVariable Long clienteId) {
        Cliente cliente = new Cliente(); 
        return vendaService.buscarVendasPorCliente(cliente);
    }


     @GetMapping("/funcionario/{funcionarioId}")
    public List<Venda> getVendasPorFuncionario(@PathVariable Long funcionarioId) {
        Funcionario funcionario = new Funcionario(); 
        funcionario.setId(funcionarioId);            
        return vendaService.buscarVendasPorFuncionario(funcionario);
    }

       @GetMapping("/produto/{produtoId}")
    public List<Venda> getVendasPorProduto(@PathVariable Long produtoId) {
        Produto produto = new Produto();
        produto.setId(produtoId);   
        return vendaService.buscarVendasPorProduto(produto);
    }

    @GetMapping("/valor/{valor}")
    public List<Venda> getVendasPorValor(@PathVariable Double valor) {
        return vendaService.buscarVendasPorValor(valor);
    }


    @GetMapping("/desconto/{desconto}")
    public List<Venda> getVendasPorDesconto(@PathVariable Double desconto) {
        return vendaService.buscarVendasPorDesconto(desconto);
    }

    @DeleteMapping("/cliente/{clienteId}")
    public void deletarVendasPorCliente(@PathVariable Long clienteId) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteId);
        vendaService.deletarVendasPorCliente(cliente);
    }

    @PutMapping("/id/{id}")
    public Venda updateById(@PathVariable Long id, @RequestBody Venda updatedVenda) throws ResourceNotFoundException {
        return vendaService.updateById(id, updatedVenda);
    }
}
