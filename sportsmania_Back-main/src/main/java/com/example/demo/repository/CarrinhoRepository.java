package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Carrinho;
import com.example.demo.model.Cliente;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
    Carrinho findByCliente(Cliente cliente);  // Retorna um único carrinho
}
