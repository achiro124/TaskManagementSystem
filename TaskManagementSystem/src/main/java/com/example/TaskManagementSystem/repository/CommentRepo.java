package com.example.TaskManagementSystem.repository;

import com.example.TaskManagementSystem.entity.CommentEntity;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepo extends CrudRepository<CommentEntity, Long> {
}
