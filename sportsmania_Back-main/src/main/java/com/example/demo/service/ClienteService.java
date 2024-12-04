package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;



@Service
public class ClienteService {


    @Autowired
    private ClienteRepository clienteRepository;

 
    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Cliente  getById (Long id) throws ResourceNotFoundException{
        Optional<Cliente> opCliente  = clienteRepository.findById(id);

        if(opCliente.isEmpty()){
            throw new ResourceNotFoundException("User não encontrado"); 
        }
        return opCliente.get(); 
    }

    public void deleteById(Long id) throws ResourceNotFoundException{
        Optional<Cliente> opcliente = clienteRepository.findById(id);
 
        if (opcliente.isEmpty()) {
            throw new ResourceNotFoundException("User não encontrado"); 
        }
        Cliente cliente = opcliente.get();
        clienteRepository.delete(cliente);
    }

    public Cliente updateById(Long id, Cliente clienteAtualizado) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if (clienteOptional.isPresent()) {
            Cliente clienteExistente = clienteOptional.get();
            clienteExistente.setNome(clienteAtualizado.getNome());
            clienteExistente.setCpf(clienteAtualizado.getCpf());
            clienteExistente.setEndereco(clienteAtualizado.getEndereco());
            return clienteRepository.save(clienteExistente);
        } else {
            throw new RuntimeException("Cliente com o ID " + id + " não encontrado.");
        }
    }
    

    public Cliente findByNome(String nome) {
        return clienteRepository.findByNome(nome);
    }

    public Cliente findByCpf(String cpf) throws EntityNotFoundException {
        return clienteRepository.findByCpf(cpf);
    }

    public void deleteByCpf(String cpf) throws EntityNotFoundException {
        clienteRepository.deleteByCpf(cpf);
    }

    public void deleteByNome(String nome) throws EntityNotFoundException {
        clienteRepository.deleteByNome(nome);
    }

    public void insertBycliente(Cliente f) {
        clienteRepository.save(f);
    }

    public Cliente updateByNome(String nome, Cliente newCliente)throws ResourceNotFoundException {
        Cliente existingCliente = clienteRepository.findByNome(nome);
        if (existingCliente != null) {
            newCliente.setId(existingCliente.getId());
            return clienteRepository.save(newCliente);
        } else {
            throw new ResourceNotFoundException("Cliente com o nome " + nome + " não existe.");
        }
    }
}
    

