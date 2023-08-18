package com.sharying.taskin.app.api;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharying.taskin.app.api.error.ApiResponseException;
import com.sharying.taskin.app.domain.dtos.InnerTaskControllerDTO;
import com.sharying.taskin.app.domain.dtos.InnerTaskServiceDTO;
import com.sharying.taskin.app.domain.dtos.InsertTask;
import com.sharying.taskin.app.domain.dtos.OutTaskControllerDTO;
import com.sharying.taskin.app.domain.dtos.UpdateTaskControllerDTO;
import com.sharying.taskin.app.domain.entities.Task;
import com.sharying.taskin.app.domain.error.ParamInvalid;
import com.sharying.taskin.app.domain.error.TaskNotFound;
import com.sharying.taskin.app.domain.repository.TaskRepo;
import com.sharying.taskin.app.service.CreateTask;
import com.sharying.taskin.app.service.DeleteTask;
import com.sharying.taskin.app.service.FindTask;
import com.sharying.taskin.app.service.UpdateTask;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final TaskRepo repo;

    public TaskController(TaskRepo repo) {
        this.repo = repo;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody InnerTaskControllerDTO dto) {
        CreateTask createTask = new CreateTask(repo);

        try {
            createTask.execute(new InsertTask(dto.title(), dto.description()));
        } catch (ParamInvalid e) {
            throw new ApiResponseException(e);
        }

        return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutTaskControllerDTO> findById(@PathVariable String id) throws TaskNotFound {
        FindTask findTask = new FindTask(repo);
        Task task = findTask.byId(id);
        return ResponseEntity.ok(
                new OutTaskControllerDTO(
                        task.id(),
                        task.title(),
                        task.description(),
                        task.status().toString()));
    }

    @GetMapping
    public ResponseEntity<Collection<OutTaskControllerDTO>> findAll() {
        FindTask findTask = new FindTask(repo);
        List<OutTaskControllerDTO> result = findTask.all()
                .stream()
                .map(obj -> new OutTaskControllerDTO(
                        obj.id(),
                        obj.title(),
                        obj.description(),
                        obj.status().toString()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        DeleteTask deleteTask = new DeleteTask(repo);
        deleteTask.byId(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTask(@PathVariable String id, @RequestBody UpdateTaskControllerDTO dto) {
        UpdateTask updateTask = new UpdateTask(repo);

        try {
            updateTask.execute(
                    new InnerTaskServiceDTO(id, dto.title(), dto.description(), dto.status()));
        } catch (ParamInvalid e) {
            throw new ApiResponseException(e);
        }

        return ResponseEntity.ok().build();
    }

    

}
