package com.sharying.taskin.app.domain.entities;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import com.sharying.taskin.app.domain.error.ParamInvalid;

@DisplayName("Testes para Task")
public class TaskTest {

    private Task task;

    @Test
    @DisplayName("Teste para criação de uma task")
    public void testCreateTask() {
        assertDoesNotThrow(() -> {
            task = new Task("Testes Task", "Testes unitários para task");
        });
    }

    @Nested
    class TaskAnomaliesTest{

        @ParameterizedTest
        @NullSource
        @DisplayName("Teste de anomalaia na criação de uma task sem descrição")
        public void testCreateTaskWithoutDescription(String description) {
            assertThrows(ParamInvalid.class, () -> {
                task = new Task("Testes Task", description);
            });
        }

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("Teste de anomalaia na criação de uma task sem título")
        public void testCreateTaskWithoutTitle(String title) {
            assertThrows(ParamInvalid.class, () -> {
                task = new Task(title, "Testes unitários para task");
            });
        }

        @ParameterizedTest
        @NullSource
        @DisplayName("Teste de anomalaia na adição de uma subtask nula")
        public void testAddSubTaskNull(SubTask subTask) throws ParamInvalid {
            task = new Task("Testes Task", "Testes unitários para task");
            assertThrows(ParamInvalid.class, () -> {
                task.addSubTask(subTask);
            });
        }

    }
    
}
