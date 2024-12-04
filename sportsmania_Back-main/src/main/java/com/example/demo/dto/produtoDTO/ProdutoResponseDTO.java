package com.example.demo.dto.produtoDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProdutoResponseDTO {

    private Long id;
    private String nome;
    private Double preco;
    private int quantidade;
}
