package com.sharying.taskin.app.domain.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.sharying.taskin.app.domain.error.ParamInvalid;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_TASK")
public class Task {

    @Id
    private UUID id;
    private String title;

    @Column(name = "description_task", columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_task")
    private StatusTask status;

    @OneToMany
    private Set<SubTask> subTasks;

    private Task() {
        id = UUID.randomUUID();
        this.status = StatusTask.NOT_STARTED;
        this.subTasks = new HashSet<>();
    }

    public Task(String title, String description) throws ParamInvalid {
        this();
        changeTitle(title);
        changeDescription(description);
    }

    public void changeTitle(String title) throws ParamInvalid {
        if (title == null || title.trim().length() == 0)
            throw new ParamInvalid("Title is required");
        if (this.status == StatusTask.COMPLETED || this.status == StatusTask.ARCHIVED)
            throw new ParamInvalid("task cannot be updated");

        this.title = title;
    }

    public void changeDescription(String description) throws ParamInvalid {
        if (description == null)
            throw new ParamInvalid("Description is required");
        if (this.status == StatusTask.COMPLETED || this.status == StatusTask.ARCHIVED)
            throw new ParamInvalid("Task cannot be updated");

        this.description = description;
    }

    public void changeStatus(String status) throws ParamInvalid {
        if (status == null)
            throw new ParamInvalid("Status is required");
        if (this.status == StatusTask.COMPLETED && StatusTask.valueOf(status) != StatusTask.ARCHIVED)
            throw new ParamInvalid("Task completed");
        if (!ruleChangeStatus(StatusTask.valueOf(status)))
            throw new ParamInvalid("Status invalid");

        this.status = StatusTask.valueOf(status);
    }

    private boolean ruleChangeStatus(StatusTask status) {
        return ruleToThisStatusNOT_STARTED(status) ||
                ruleToThisStatusIN_PROGRESS(status) ||
                status == StatusTask.ARCHIVED;
    }

    private boolean ruleToThisStatusNOT_STARTED(StatusTask status) {
        return this.status == StatusTask.NOT_STARTED &&
                (status == StatusTask.IN_PROGRESS ||
                        status == StatusTask.COMPLETED);
    }

    private boolean ruleToThisStatusIN_PROGRESS(StatusTask status) {
        return this.status == StatusTask.IN_PROGRESS &&
                (status == StatusTask.NOT_STARTED ||
                        status == StatusTask.COMPLETED);
    }

    public void addSubTask(SubTask subTask) throws ParamInvalid {
        if (subTask == null)
            throw new ParamInvalid("SubTask is required");
        if (this.status == StatusTask.COMPLETED || this.status == StatusTask.ARCHIVED)
            throw new ParamInvalid("Task cannot be updated");

        this.subTasks.add(subTask);
    }

    public String id() {
        return id.toString();
    }

    public String title() {
        return title;
    }

    public String description() {
        return description;
    }

    public StatusTask status() {
        return status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        Task other = (Task) obj;
        return  Objects.equals(this, other) ||
                (Objects.equals(id(), other.id()) &&
                 Objects.equals(title, other.title) &&
                 Objects.equals(description, other.description) &&
                 Objects.equals(status, other.status));
    }

    @Override
    public String toString() {
        return "Task [id=" + id() + ", title=" + title + ", description=" + description + ", status=" + status + "]";
    }

}
