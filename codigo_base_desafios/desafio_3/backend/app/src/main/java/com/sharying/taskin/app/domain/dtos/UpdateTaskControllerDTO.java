package com.sharying.taskin.app.domain.dtos;

public class UpdateTaskControllerDTO {
    final String title;
    final String description;
    final String status;

    public UpdateTaskControllerDTO(String title, String description, String status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public String title() { return title; }
    public String description() { return description; }
    public String status() { return status; }
}
