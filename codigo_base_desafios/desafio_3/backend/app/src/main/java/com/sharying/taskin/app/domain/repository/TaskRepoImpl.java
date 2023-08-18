package com.sharying.taskin.app.domain.repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sharying.taskin.app.domain.entities.StatusTask;
import com.sharying.taskin.app.domain.entities.Task;

@Repository
public class TaskRepoImpl implements TaskRepo {

    @Autowired
    private TaskJPA jpa;

    @Override
    public void create(Task task) {
        jpa.save(task);
    }

    @Override
    public void update(Task task) {
        jpa.save(task);
    }

    @Override
    public void deleteById(String id) {
        UUID uuid = UUID.fromString(id);
        jpa.deleteById(uuid);
    }

    @Override
    public void deleteAll() {
        jpa.deleteAll();
    }

    @Override
    public Collection<Task> findAll() {
        return jpa.findAll()
                .stream()
                .filter(task -> task.status() != StatusTask.ARCHIVED)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Task> findById(String id) {
        UUID uuid = UUID.fromString(id);
        return jpa.findById(uuid);
    }

}
