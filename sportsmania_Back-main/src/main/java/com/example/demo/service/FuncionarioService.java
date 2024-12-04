package com.example.demo.service;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.FuncionarioDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Funcionario;
import com.example.demo.repository.FuncionarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FuncionarioService {

    
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    private final ModelMapper modelMapper;

    FuncionarioService(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public FuncionarioDTO addFuncionario(FuncionarioDTO funcionarioDTO){
       Funcionario funcionario = modelMapper.map(funcionarioDTO,Funcionario.class);

       Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);

        return modelMapper.map(funcionarioSalvo,FuncionarioDTO.class);

    }

    public FuncionarioDTO findByNome(String nome) throws ResourceNotFoundException {

        FuncionarioDTO funcionarioDTO = funcionarioRepository.findByNome(nome);

        return modelMapper.map(funcionarioDTO,FuncionarioDTO.class);
    
        
    }

    public FuncionarioDTO findByCpf(String cpf) throws ResourceNotFoundException {
        Funcionario funcionario = funcionarioRepository.findByCpf(cpf);
        
        if (funcionario == null) {
            throw new ResourceNotFoundException("Nenhum funcionario com o cpf " + cpf + " foi encontrado");
        }

       return modelMapper.map(funcionario,FuncionarioDTO.class);
    }

    public void deleteByCpf(String cpf) throws EntityNotFoundException {
        funcionarioRepository.deleteByCpf(cpf);
    }

    public void deleteByNome(String nome) throws EntityNotFoundException {
        funcionarioRepository.deleteByNome(nome);
    }

    public List<FuncionarioDTO> getAllFuncionarios() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        return funcionarios.stream() .map(funcionario -> modelMapper.map(funcionario, FuncionarioDTO.class)).toList();
    }



   /*  public FuncionarioDTO updateByNome(String nome, Funcionario newFuncionario) throws ResourceNotFoundException {
        
        Funcionario existingFuncionario = funcionarioRepository.findByNome1(nome);
    
        if (existingFuncionario == null) {
            throw new ResourceNotFoundException("Funcionário com o nome " + nome + " não existe.");
        }
    
        existingFuncionario.setNome(newFuncionario.getNome());
        existingFuncionario.setCpf(newFuncionario.getCpf());
        
        Funcionario updatedFuncionario = funcionarioRepository.save(existingFuncionario);

        return FuncionarioDTOConverters.funcionarioListModelToDTO(updatedFuncionario);
    }
    */

}
