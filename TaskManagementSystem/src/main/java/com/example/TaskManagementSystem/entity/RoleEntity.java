package com.example.TaskManagementSystem.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data
@Schema(description = "User role entity")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the role", example = "1")
    private Integer id;

    @Schema(description = "Role name", example = "ROLE_ADMIN")
    private String name;
}
