package com.example.TaskManagementSystem.repository;

import com.example.TaskManagementSystem.entity.TodoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepo extends CrudRepository<TodoEntity, Long> {
    List<TodoEntity> findByAuthorId(Long authorId);

    List<TodoEntity> findByAssigneeId(Long assigneeId);
}
