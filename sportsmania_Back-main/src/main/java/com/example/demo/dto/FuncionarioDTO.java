package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FuncionarioDTO {

    private String nome;
    private String cpf;


    public void Funcionario(String nome, String cpf) {

        this.nome = nome;
        this.cpf = cpf;

    }

}

