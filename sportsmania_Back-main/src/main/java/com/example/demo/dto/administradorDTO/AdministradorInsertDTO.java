package com.example.demo.dto.administradorDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdministradorInsertDTO {

    private String nome;
    private String password;
    private String email;

}
