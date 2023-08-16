package com.sharying.taskin.app.domain.repository;

import java.util.Collection;
import java.util.Optional;

import com.sharying.taskin.app.domain.entities.Task;

public interface TaskRepo {

    void create(Task task);

    void update(Task task);

    void deleteById(String id);

    void deleteAll();

    Collection<Task> findAll();

    Optional<Task> findById(String id);

}
