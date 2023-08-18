package com.sharying.taskin.app.service;

import java.util.Collection;

import com.sharying.taskin.app.domain.entities.Task;
import com.sharying.taskin.app.domain.error.TaskNotFound;
import com.sharying.taskin.app.domain.repository.TaskRepo;

public class FindTask {

    private final TaskRepo repo;

    public FindTask(TaskRepo repo) {
        this.repo = repo;
    }

    public Task byId(String id) throws TaskNotFound {
        return repo.findById(id).orElseThrow(TaskNotFound::new);
    }

    public Collection<Task> all() {
        return repo.findAll();
    }

}
