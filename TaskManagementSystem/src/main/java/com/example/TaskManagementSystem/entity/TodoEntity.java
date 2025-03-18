package com.example.TaskManagementSystem.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "todo")
@Data
@Schema(description = "Task entity")
public class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the task", example = "1")
    private Long id;

    @Schema(description = "Task title", example = "Complete the project")
    private String title;

    @Schema(description = "Task description", example = "Need to complete the project by the end of the week")
    private String description;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    @Schema(description = "Task status")
    private StatusEntity status;

    @ManyToOne
    @JoinColumn(name = "priority_id", nullable = false)
    @Schema(description = "Task priority")
    private PriorityEntity priority;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    @Schema(description = "Task author")
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    @Schema(description = "Task assignee")
    private UserEntity assignee;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "List of comments for the task")
    private List<CommentEntity> comments;
}
