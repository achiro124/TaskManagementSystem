package com.example.TaskManagementSystem.dtos;

import com.example.TaskManagementSystem.entity.RoleEntity;
import com.example.TaskManagementSystem.entity.TodoEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
@Schema(description = "DTO for representing a user")
public class UserDto {

    @Schema(description = "Unique identifier of the user", example = "1")
    private Long id;

    @Schema(description = "User email", example = "user@mail.ru")
    private String email;

    @Schema(description = "User password", example = "qwerty123")
    private String password;
}