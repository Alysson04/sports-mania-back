package com.example.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Cliente {

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 100)
    private String nome;

    private String cpf;

    private String endereco;
    
       @OneToMany(mappedBy = "cliente")
        @JsonIgnore
        private List<Venda> venda; // Relacionamento com Venda
    
}
