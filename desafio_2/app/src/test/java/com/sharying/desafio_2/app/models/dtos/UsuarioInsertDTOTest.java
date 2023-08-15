package com.sharying.desafio_2.app.models.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@TestInstance(Lifecycle.PER_CLASS)
public class UsuarioInsertDTOTest {
    
    private Validator validator;
    private UsuarioInsertDTO usuario;

    @BeforeAll
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();     
    }

    @BeforeEach
    void init() {
        usuario = new UsuarioInsertDTO();
    }

    @Test
    @DisplayName("Teste de inserção de usuário")
    void casosInsercaoFunciona() {
        usuario.setNome("Lucas");
        usuario.setCpf("70762328479");
        usuario.setEmail("lucas.email@algumacoisa.com");
        usuario.setTelefone("84987018512");
        usuario.setSenha("123456");
        Set<ConstraintViolation<UsuarioInsertDTO>> violacoes = validator.validate(usuario);
        assertEquals(0, violacoes.size());
    }
        
    @Nested
    @DisplayName("Teste de anomalias para inserção de usuário")
    class Anomalias {

        @ParameterizedTest
        @DisplayName("Casos de nome inválido")
        @ValueSource(strings = {"Lu", "L"})
        @NullAndEmptySource
        void nomeInvalidoDeveLancarExcecao(String nome) {
            usuario.setNome(nome);
            Set<ConstraintViolation<UsuarioInsertDTO>> violacoes = validator.validate(usuario);
            assertFalse(violacoes.isEmpty());
        }

        @ParameterizedTest
        @DisplayName("Casos de CPF inválido")
        @ValueSource(strings = {"12345678901", "123456789012", "1234567890", "1234567890a"})
        @NullAndEmptySource
        void cpfInvalidoDeveLancarExcecao(String cpf) {
            usuario.setCpf(cpf);
            Set<ConstraintViolation<UsuarioInsertDTO>> violacoes = validator.validate(usuario);
            assertFalse(violacoes.isEmpty());
        }

        @ParameterizedTest
        @DisplayName("Casos de email inválido")
        @ValueSource(strings = {"lu", "lu@", "lu@lu", "lu@lu."})
        @NullAndEmptySource
        void emailInvalidoDeveLancarExcecao(String email) {
            usuario.setEmail(email);
            Set<ConstraintViolation<UsuarioInsertDTO>> violacoes = validator.validate(usuario);
            assertFalse(violacoes.isEmpty());
        }

        @ParameterizedTest
        @DisplayName("Casos de senha inválida")
        @ValueSource(strings = {"12345"})
        @NullAndEmptySource
        void senhaInvalidaDeveLancarExcecao(String senha) {
            usuario.setSenha(senha);
            Set<ConstraintViolation<UsuarioInsertDTO>> violacoes = validator.validate(usuario);
            assertFalse(violacoes.isEmpty());
        }

    }

}
