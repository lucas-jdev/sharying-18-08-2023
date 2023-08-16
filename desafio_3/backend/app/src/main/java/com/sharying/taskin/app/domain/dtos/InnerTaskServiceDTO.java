package com.sharying.taskin.app.domain.dtos;

public class InnerTaskServiceDTO {
    final String id;
    final String title;
    final String description;
    final String status;

    public InnerTaskServiceDTO(String id, String title, String description, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public String id() {
        return id;
    }

    public String title() {
        return title;
    }

    public String description() {
        return description;
    }

    public String status() {
        return status;
    }
}
