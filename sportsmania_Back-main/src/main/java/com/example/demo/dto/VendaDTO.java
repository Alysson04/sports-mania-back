package com.example.demo.dto;

import java.util.List;

import com.example.demo.model.Produto;

import lombok.Data;

@Data
public class VendaDTO {

    private Double valor;
    private Double desconto;
    private List<Produto> produto;

    public VendaDTO() {
        
    }
    
    public VendaDTO(Double valor, Double desconto, List<Produto> produto) {

        this.valor = valor;
        this.desconto = desconto;
        this.produto = produto;
    }


    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public List<Produto> getProduto() {
        return produto;
    }

    public void setProduto(List<Produto> produto) {
        this.produto = produto;
    }



}