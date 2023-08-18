package com.sharying.taskin.app.domain.dtos;

public class InsertTask {
    final String title;
    final String description;

    public InsertTask(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String title() { return title; }
    public String description() { return description; }
}
