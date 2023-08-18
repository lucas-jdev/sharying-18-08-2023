package com.sharying.taskin.app.service;

import com.sharying.taskin.app.domain.dtos.InsertTask;
import com.sharying.taskin.app.domain.entities.Task;
import com.sharying.taskin.app.domain.error.ParamInvalid;
import com.sharying.taskin.app.domain.repository.TaskRepo;

public class CreateTask {

    private final TaskRepo repo;

    public CreateTask(TaskRepo repo) {
        this.repo = repo;
    }

    public void execute(InsertTask insert) throws ParamInvalid {
        Task task = new Task(insert.title(), insert.description());
        repo.create(task);
    }
    
}
