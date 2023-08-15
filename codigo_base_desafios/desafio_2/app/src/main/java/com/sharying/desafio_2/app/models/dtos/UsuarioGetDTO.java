package com.sharying.desafio_2.app.models.dtos;

import com.sharying.desafio_2.app.models.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UsuarioGetDTO {
    
    @NotNull
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    private String telefone;

    @NotBlank
    private String cpf;

    public UsuarioGetDTO(Usuario entidade) {
        this.id = entidade.getId();
        this.nome = entidade.getNome();
        this.email = entidade.getEmail();
        this.telefone = entidade.getTelefone();
        this.cpf = entidade.getCpf();
    }

}
