package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.FuncionarioDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.FuncionarioService;

import jakarta.persistence.EntityNotFoundException;


@RestController
@RequestMapping(value = "api/funcionario")
public class FuncionarioController {
    @Autowired
    FuncionarioService funcionarioService;

    @GetMapping("/{nome}")
    @PreAuthorize("hasAuthority('FUNCIONARIO')")
    public FuncionarioDTO getFuncionarioByNome(@PathVariable String nome) throws ResourceNotFoundException {
        return funcionarioService.findByNome(nome);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')") // Apenas admin pode adicionar funcionários
    public void insertByFuncionario(@RequestBody FuncionarioDTO funcionarioDTO) {
        funcionarioService.addFuncionario(funcionarioDTO);
    }
    
    @GetMapping("/{cpf}")
    public FuncionarioDTO getFuncionarioByCpf(@PathVariable("cpf") String cpf) {
        try{
        return funcionarioService.findByCpf(cpf);
        } catch (ResourceNotFoundException e) {
            return null;}

    }

   /*@GetMapping("/{nome}")
    public FuncionarioDTO getFuncionarioByNome(@PathVariable String nome) throws ResourceNotFoundException {
        
        return funcionarioService.findByNome(nome);
    }

   
    @PostMapping("/add")
    public void insertByFuncionario(@RequestBody FuncionarioDTO funcionarioDTO) {
        funcionarioService.addFuncionario(funcionarioDTO);
    }
    

/* @PutMapping("/update/{name}")
    public FuncionarioDTO updateByNome(@PathVariable String nome, @RequestBody FuncionarioDTO updatedFuncionario) throws ResourceNotFoundException {
        return funcionarioService.updateByNome(nome, updatedFuncionario); 
    }
*/

    @DeleteMapping("/{nome}/delete")
    public void deleteFuncionarioByNome(@PathVariable("nome") String nome) {
        try {
            funcionarioService.deleteByNome(nome);
        } catch (EntityNotFoundException e) {
        }
    }

    @DeleteMapping("/{cpf}/delete")
    public void deleteFuncionarioByCpf(@PathVariable("cpf") String cpf) {
        try {
            funcionarioService.deleteByCpf(cpf);
        } catch (EntityNotFoundException e) {
        }
    }
}
