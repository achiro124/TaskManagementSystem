package com.example.TaskManagementSystem.repository;

import com.example.TaskManagementSystem.entity.StatusEntity;
import org.springframework.data.repository.CrudRepository;

public interface StatusRepo extends CrudRepository<StatusEntity, Integer> {
}
