package com.example.demo.dto;

import java.util.List;

import com.example.demo.model.Cliente;
import com.example.demo.model.Produto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor 
public class CarrinhoDTO {
    

    private Cliente cliente;
    private List<Produto> produtos;

}
