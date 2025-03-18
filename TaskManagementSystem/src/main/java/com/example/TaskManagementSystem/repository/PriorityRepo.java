package com.example.TaskManagementSystem.repository;

import com.example.TaskManagementSystem.entity.PriorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriorityRepo extends JpaRepository<PriorityEntity, Integer> {
}
