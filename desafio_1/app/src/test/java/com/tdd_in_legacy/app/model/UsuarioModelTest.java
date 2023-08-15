package com.tdd_in_legacy.app.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Testes de unidade para a classe UsuarioModel")
class UsuarioModelTest {

    private final String NOME_USUARIO = "João";
    
    @Test
    @DisplayName("Teste para verificar se o nome do usuário é válido")
    void testNomeUsuarioValido() {
        UsuarioModel usuario = new UsuarioModel();
        usuario.setNome("João");
        assertEquals(NOME_USUARIO, usuario.getNome());
    }

    @Test
    @DisplayName("Teste para verificar se o usuário é maior de idade")
    void testUsuarioMaiorIdade() {
        UsuarioModel usuario = new UsuarioModel();
        usuario.setIdade(18);
        assertEquals(18, usuario.getIdade());
    }

    @Test
    @DisplayName("Teste para verificar se o cpf do usuário é válido")
    void testCpfUsuarioValido() {
        UsuarioModel usuario = new UsuarioModel();
        usuario.setCpf("70762328479");
        assertEquals("70762328479", usuario.getCpf());
    }

    @Nested
    @DisplayName("Testes para anomalias das regras de negócio")
    class AnomaliasUsuarioModelTest {
        
        @ParameterizedTest
        @ValueSource(strings = {"Jo", "J", "Joa"})
        @NullAndEmptySource
        @DisplayName("Teste para verificar se o nome do usuário é inválido")
        void testNomeUsuarioInvalido(String valor) {
            UsuarioModel usuario = new UsuarioModel();
            assertThrows(IllegalArgumentException.class, () -> {
                usuario.setNome(valor);
            }, "Nome Inválido");
        }

        @Test
        @DisplayName("Teste para verificar se o usuário é menor de idade")
        void testUsuarioMenorIdade() {
            UsuarioModel usuario = new UsuarioModel();
            assertThrows(IllegalArgumentException.class, () -> {
                usuario.setIdade(17);
            }, "Idade inválida");
        }

        @ParameterizedTest
        @ValueSource(strings = {"11111111111", "22222222222", "33333333333", 
                                "44444444444", "55555555555", "66666666666", 
                                "77777777777", "88888888888", "99999999999",
                                "00000000000", "12345678901", "70762328470"})
        @NullSource
        @DisplayName("Teste para verificar se o cpf do usuário é inválido")
        void testCpfUsuarioInvalido(String valor) {
            UsuarioModel usuario = new UsuarioModel();
            assertThrows(IllegalArgumentException.class, () -> {
                usuario.setCpf(valor);
            }, "CPF inválido");
        }
        
    }


}
