package com.sharying.desafio_2.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sharying.desafio_2.app.models.dtos.UsuarioGetDTO;
import com.sharying.desafio_2.app.models.dtos.UsuarioInsertDTO;
import com.sharying.desafio_2.app.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/{id}")
    public UsuarioGetDTO getUsuario(@RequestParam Long id) {
        return usuarioService.getUsuario(id);
    }

    @GetMapping("")
    public List<UsuarioGetDTO> getUsuarios() {
        return usuarioService.getUsuarios();
    }

    @PostMapping
    public UsuarioGetDTO createUsuario(@Valid @RequestBody UsuarioInsertDTO usuario, 
                                        BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            throw new IllegalArgumentException(errors.toString());
        }
        return usuarioService.createUsuario(usuario);
    }

    @PutMapping("/{id}")
    public UsuarioGetDTO updateUsuario(@RequestParam Long id, @RequestBody UsuarioInsertDTO usuario,
                                        BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            throw new IllegalArgumentException(errors.toString());
        }
        return usuarioService.updateUsuario(id, usuario);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@RequestParam Long id) {
        usuarioService.deleteUsuario(id);
    }

}
