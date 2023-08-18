package com.sharying.taskin.app.domain.dtos;

public class InnerTaskControllerDTO {
    final String title;
    final String description;

    public InnerTaskControllerDTO(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String title() { return title; }
    public String description() { return description; }
    
}
