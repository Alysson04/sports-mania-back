package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.administradorDTO.AdministradorListDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Administrador;
import com.example.demo.repository.AdministradorRepository;

@Service
public class AdministradorService {
    
    @Autowired
    private AdministradorRepository administradorRepository;

    /*POST*/
    public void insertUser(Administrador adm){
        administradorRepository.save(adm);
    }

    /*GET*/
    public List<Administrador> findAll(){
        return administradorRepository.findAll();
    }

    public Administrador  findById (Long id) throws ResourceNotFoundException{
        Optional<Administrador> opAdministrador  = administradorRepository.findById(id);

        if(opAdministrador.isEmpty()){
            throw new ResourceNotFoundException("User não encontrado"); 
        }
        return opAdministrador.get(); 
    }

    public AdministradorListDTO findByNome(String nome) throws ResourceNotFoundException {
        Administrador administrador = administradorRepository.findByNome(nome);
        
        if (administrador == null) {
            throw new ResourceNotFoundException("Nenhum administrador com o nome " + nome + " foi encontrado");
        }
        AdministradorListDTO administradorDTO = new AdministradorListDTO();
    
        return administradorDTO;
    }

    /*PUT*/
        public void updateNome(Long id, String nome) throws ResourceNotFoundException{
            Optional<Administrador> opAdministrador = administradorRepository.findById(id);
     
            if (opAdministrador.isEmpty()) {
                throw new ResourceNotFoundException("User não encontrado"); 
            }
     
            Administrador administrador = opAdministrador.get();
            administrador.setNome(nome);
            administradorRepository.save(administrador);
        }

    /*DELETE*/
        public void deleteById(Long id) throws ResourceNotFoundException{
            Optional<Administrador> opAdministrador = administradorRepository.findById(id);
     
            if (opAdministrador.isEmpty()) {
                throw new ResourceNotFoundException("User não encontrado"); 
            }
            Administrador administrador = opAdministrador.get();
            administradorRepository.delete(administrador);
        }

        public void deleteByNome(String nome) throws ResourceNotFoundException {
            administradorRepository.deleteByNome(nome);
        }
    
}

