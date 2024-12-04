package com.example.demo.controller;

import com.example.demo.dto.administradorDTO.AdministradorListDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Administrador;
import com.example.demo.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adm")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @GetMapping("/all")
    public List<Administrador> getAdministrador() {
        return administradorService.findAll();
    }

    @GetMapping("/id/{id}")
    public Administrador getAdministradorById(@PathVariable("id") Long id) {
        try {
            return administradorService.findById(id);
        } catch (ResourceNotFoundException e) {
            return null;
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<AdministradorListDTO> getAdministradorByNome(@PathVariable String nome) throws ResourceNotFoundException {
        AdministradorListDTO administradorDTO = administradorService.findByNome(nome);
        return ResponseEntity.ok(administradorDTO);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')") // Apenas ADMIN pode criar outros administradores
    public void insertAdministrador(@RequestBody Administrador administrador) {
        administradorService.insertUser(administrador);
    }

    @PutMapping("/update/{id}")
    public void updateByNome(@PathVariable("id") Long id, @RequestBody String nome) {
        try {
            administradorService.updateNome(id, nome);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteAdministrador(@PathVariable Long id) {
        try {
            administradorService.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/nome/{nome}")
    public void deleteAdministradorByNome(@PathVariable String nome) throws ResourceNotFoundException {
        administradorService.deleteByNome(nome);
    }
}
