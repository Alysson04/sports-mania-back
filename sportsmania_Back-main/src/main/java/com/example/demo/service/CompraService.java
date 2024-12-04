package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Compra;
import com.example.demo.model.Produto;
import com.example.demo.model.Administrador;
import com.example.demo.dto.CompraDTO.CompraRequestDTO;
import com.example.demo.dto.CompraDTO.CompraResponseDTO;

import java.util.stream.Collectors;

import com.example.demo.repository.CompraRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    private final ModelMapper modelMapper;

    
    CompraService(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }


    // GET
    public List<CompraRequestDTO> findAllCompras() {
        List<Compra> compras = compraRepository.findAll();
        return compras.stream().map(compra -> modelMapper.map(compra,CompraRequestDTO.class)).collect(Collectors.toList());
    }

    public CompraRequestDTO findById (Long id) throws ResourceNotFoundException{
        Optional<Compra> opCompra  = compraRepository.findById(id);
    
        if(opCompra.isEmpty()){
            throw new ResourceNotFoundException("Compra não encontrada");
        }
        return modelMapper.map(opCompra,CompraRequestDTO.class);
    }

    public CompraRequestDTO findByCnpj(String cnpj) {
        Compra comprasCnpj = compraRepository.findByCnpj(cnpj);
        return modelMapper.map(comprasCnpj,CompraRequestDTO.class);
    }

    public CompraRequestDTO findByValor(Double valor) {
        Compra comprasValor = compraRepository.findByValor(valor);
        return modelMapper.map(comprasValor,CompraRequestDTO.class);
    }

    public CompraRequestDTO findCompraByAdministrador(Administrador administrador) throws ResourceNotFoundException {
        Compra compra = compraRepository.findByAdministrador(administrador);
        
        if (compra == null) {
            throw new ResourceNotFoundException("Nenhuma compra feita pelo admnistrador " + administrador + " foi encontrada no sistema");
        }

        return modelMapper.map(compra,CompraRequestDTO.class);
    }

    public List<CompraRequestDTO> findByProduto(List<Produto> produto) {
        List<Compra> compras = compraRepository.findByProduto(produto);
        return compras.stream().map(compra -> modelMapper.map(compra,CompraRequestDTO.class)).collect(Collectors.toList());
    }



    // POST
    public CompraResponseDTO InsertCompra(CompraResponseDTO compraResponseDTO) {
        Compra compra = modelMapper.map(compraResponseDTO,Compra.class);
        Compra compraSalva = compraRepository.save(compra);

        return modelMapper.map(compraSalva,CompraResponseDTO.class);
    }



    // PUT
    public CompraResponseDTO updateById(Long id, CompraResponseDTO compraResponseDTO) throws ResourceNotFoundException {
    
        Optional<Compra> existingCompra = compraRepository.findById(id);
        if (existingCompra.isEmpty()) {
            throw new ResourceNotFoundException("Compra com o id " + id + " não existe.");
        }
        
        Compra compra = existingCompra.get();
        modelMapper.map(compraResponseDTO,compra);
        Compra updatedCompra = compraRepository.save(compra);
        return modelMapper.map(updatedCompra,CompraResponseDTO.class);
    }



    
    // DELETE
    public void deleteById(Long id) throws ResourceNotFoundException{
    
        Optional<Compra> opCompra = compraRepository.findById(id);
    
        if (opCompra.isEmpty()){
            throw new ResourceNotFoundException("Compra com " + id + " não encontrada.");
        }
    
        Compra compra = opCompra.get();
        compraRepository.delete(compra);
        
    }

    public void deleteByCnpj(String cnpj) throws ResourceNotFoundException {
        compraRepository.deleteByCnpj(cnpj);
    }

}
