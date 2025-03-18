package com.example.TaskManagementSystem.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "priorities")
@Data
@Schema(description = "Task priority entity")
public class PriorityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the priority", example = "1")
    private int id;

    @Column(unique = true, nullable = false)
    @Schema(description = "Priority name", example = "High")
    private String name;
}
