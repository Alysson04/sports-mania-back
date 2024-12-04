package com.example.demo.dto.CompraDTO;

import java.util.List;

import com.example.demo.model.Administrador;
import com.example.demo.model.Produto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraRequestDTO {
    private Double valor;
    private Administrador administrador;
    private List<Produto> produtos;
}
