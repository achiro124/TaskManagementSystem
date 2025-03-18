package com.example.TaskManagementSystem.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Entity
@Table(name = "users")
@Data
@Schema(description = "User entity")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the user", example = "1")
    private Long id;

    @Schema(description = "User password", example = "qwerty123")
    private String password;

    @Schema(description = "User email", example = "user@mail.ru")
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assignee")
    @Schema(description = "List of tasks assigned to the user")
    private Collection<TodoEntity> todos;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Schema(description = "List of user roles")
    private Collection<RoleEntity> roles;
}
