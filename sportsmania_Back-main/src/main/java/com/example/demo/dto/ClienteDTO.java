package com.example.demo.dto;

public class ClienteDTO {


    private String cpf;
    private String nome;
    private String endereco;

    // Construtor padrão
    public ClienteDTO() {}


    public ClienteDTO(String cpf, String nome, String endereco) {

        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
    }




    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}