package com.sharying.desafio_2.app.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharying.desafio_2.app.models.Usuario;
import com.sharying.desafio_2.app.models.dtos.UsuarioGetDTO;
import com.sharying.desafio_2.app.models.dtos.UsuarioInsertDTO;
import com.sharying.desafio_2.app.repos.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioGetDTO getUsuario(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return new UsuarioGetDTO(usuario.get());
        }
        return null;
    }

    public List<UsuarioGetDTO> getUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                        .map(UsuarioGetDTO::new)
                        .collect(Collectors.toList());
    }

    public UsuarioGetDTO createUsuario(UsuarioInsertDTO dto) {
        Usuario usuario = new Usuario(
            dto.getNome(),
            dto.getEmail(),
            dto.getSenha(),
            dto.getTelefone(),
            dto.getCpf()
            );
        Usuario usuarioCriado = usuarioRepository.save(usuario);
        return new UsuarioGetDTO(usuarioCriado);
    }

    public UsuarioGetDTO updateUsuario(Long id, UsuarioInsertDTO dto) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            Usuario usuarioAtualizado = usuario.get();
            usuarioAtualizado.setNome(dto.getNome());
            usuarioAtualizado.setEmail(dto.getEmail());
            usuarioAtualizado.setSenha(dto.getSenha());
            usuarioAtualizado.setTelefone(dto.getTelefone());
            usuarioAtualizado.setCpf(dto.getCpf());
            usuarioRepository.save(usuarioAtualizado);
            return new UsuarioGetDTO(usuarioAtualizado);
        }
        return null;
    }

    public void deleteUsuario(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            usuarioRepository.delete(usuario.get());
        }
    }

}
