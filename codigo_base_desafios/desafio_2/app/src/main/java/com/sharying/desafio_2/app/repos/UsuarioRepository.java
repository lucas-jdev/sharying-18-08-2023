package com.sharying.desafio_2.app.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharying.desafio_2.app.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    Usuario findByEmail(String email);

    List<Usuario> findAllByNome(String nome);

}
