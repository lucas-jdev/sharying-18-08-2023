package com.sharying.taskin.app.service;

import java.util.Optional;

import com.sharying.taskin.app.domain.dtos.InnerTaskServiceDTO;
import com.sharying.taskin.app.domain.entities.Task;
import com.sharying.taskin.app.domain.error.ParamInvalid;
import com.sharying.taskin.app.domain.repository.TaskRepo;

public class UpdateTask {

    private final TaskRepo repo;

    public UpdateTask(TaskRepo repo) {
        this.repo = repo;
    }

    public void execute(final InnerTaskServiceDTO dto) throws ParamInvalid {
        Optional<Task> task = repo.findById(dto.id());
        if (task.isPresent()) {
            Task taskUpdate = task.get();
            validateInputsToChangeAttributes(dto, taskUpdate);
            repo.update(taskUpdate);
        }
    }

    private void validateInputsToChangeAttributes(InnerTaskServiceDTO dto, Task taskUpdate) throws ParamInvalid {
        if (dto.title() != null)
            taskUpdate.changeTitle(dto.title());
        if (dto.description() != null)
            taskUpdate.changeDescription(dto.description());
        if (dto.status() != null)
            taskUpdate.changeStatus(dto.status());
    }
    
}
