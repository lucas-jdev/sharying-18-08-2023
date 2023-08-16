package com.sharying.taskin.app.domain.entities;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.sharying.taskin.app.domain.error.ParamInvalid;

@DisplayName("Testes para SubTask")
public class SubTaskTest {
    
    private SubTask subTask;

    @Test
    @DisplayName("Teste para criação de SubTask")
    public void testCreateSubTask() {
        assertDoesNotThrow(() -> {
            subTask = new SubTask("SubTask 1", "SubTask 1 Description", 2L);
        });
    }

    @Nested
    @DisplayName("Testes para anomalias de subtask")
    class SubTaskAnomaliesTest {

        @ParameterizedTest
        @NullSource
        @DisplayName("Teste de anomalaia na criação de uma subtask sem descrição")
        public void testCreateSubTaskWithoutDescription(String description) {
            assertThrows(ParamInvalid.class, () -> {
                subTask = new SubTask("SubTask 1", description, 2L);
            });
        }

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("Teste de anomalaia na criação de uma subtask sem título")
        public void testCreateSubTaskWithoutName(String title) {
            assertThrows(ParamInvalid.class, () -> {
                subTask = new SubTask(title, "SubTask 1 Description", 2L);
            });
        }

        @ParameterizedTest
        @ValueSource(longs = { -1, -2, Long.MIN_VALUE })
        @DisplayName("Teste de anomalaia na criação de uma subtask com time invalido")
        public void testCreateSubTaskWithInvalidTime(Long time) {
            assertThrows(ParamInvalid.class, () -> {
                subTask = new SubTask("SubTask 1", "SubTask 1 Description", time);
            });
        }

    }

}
