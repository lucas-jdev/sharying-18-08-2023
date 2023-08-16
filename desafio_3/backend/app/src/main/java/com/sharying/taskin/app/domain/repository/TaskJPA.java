package com.sharying.taskin.app.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharying.taskin.app.domain.entities.Task;

public interface TaskJPA extends JpaRepository<Task, UUID> {

}
