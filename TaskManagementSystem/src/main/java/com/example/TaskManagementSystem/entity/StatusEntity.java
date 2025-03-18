package com.example.TaskManagementSystem.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "statuses")
@Data
@Schema(description = "Task status entity")
public class StatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the status", example = "1")
    private Integer id;

    @Column(unique = true, nullable = false)
    @Schema(description = "Status name", example = "In Progress")
    private String name;
}
