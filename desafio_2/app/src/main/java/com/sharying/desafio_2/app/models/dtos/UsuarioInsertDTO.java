package com.sharying.desafio_2.app.models.dtos;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioInsertDTO {
    
    @NotNull(message = "O nome não pode ser nulo")
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caractéres")
    private String nome;

    @Email(message = "Email inválido")
    private String email;

    @CPF(message = "CPF inválido")
    private String cpf;
    
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caractéres")
    private String senha;
    
    private String telefone;

}
