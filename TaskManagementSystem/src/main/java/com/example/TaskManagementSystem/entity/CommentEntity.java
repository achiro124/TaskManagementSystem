package com.example.TaskManagementSystem.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Data
@Schema(description = "Comment entity")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the comment", example = "1")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Text of the comment", example = "This is an example comment")
    private String text;

    @Column(nullable = false)
    @Schema(description = "Date and time of comment creation", example = "2025-03-11T21:22:23")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "todo_id", nullable = false)
    @Schema(description = "Task to which the comment belongs")
    private TodoEntity todo;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    @Schema(description = "Author of the comment")
    private UserEntity author;
}
