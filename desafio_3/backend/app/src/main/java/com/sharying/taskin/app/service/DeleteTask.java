package com.sharying.taskin.app.service;

import com.sharying.taskin.app.domain.repository.TaskRepo;

public class DeleteTask {

    private final TaskRepo repo;

    public DeleteTask(TaskRepo repo) {
        this.repo = repo;
    }

    public void byId(String id) {
        repo.deleteById(id);
    }

}
