package com.sharying.taskin.app.domain.entities;

import java.util.UUID;

import com.sharying.taskin.app.domain.error.ParamInvalid;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_SUBTASK")
public class SubTask {

    @Id
    private UUID id;
    private String title;

    @Column(name = "description_subtask", columnDefinition = "TEXT")
    private String description;

    @Column(name = "time_subtask")
    private Long time;

    private SubTask() {
        id = UUID.randomUUID();
    }

    public SubTask(String title, String description, Long time) throws ParamInvalid {
        this();
        changeTitle(title);
        changeDescription(description);
        changeTime(time);
    }

    public void changeTitle(String title) throws ParamInvalid {
        if (title == null || title.trim().length() == 0)
            throw new ParamInvalid("Title is required");
        this.title = title;
    }

    public void changeDescription(String description) throws ParamInvalid {
        if (description == null)
            throw new ParamInvalid("Description is required");
        this.description = description;
    }

    public void changeTime(Long time) throws ParamInvalid {
        if (time == null || time < 0)
            throw new ParamInvalid("Time is required");
        this.time = time;
    }

    public UUID id() {
        return id;
    }

    public String title() {
        return title;
    }

    public String description() {
        return description;
    }

    public Long time() {
        return time;
    }

}
