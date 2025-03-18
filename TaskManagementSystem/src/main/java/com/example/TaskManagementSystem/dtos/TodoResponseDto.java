package com.example.TaskManagementSystem.dtos;

import com.example.TaskManagementSystem.entity.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Schema(description = "DTO for representing a task")
public class TodoResponseDto {

    @Schema(description = "Unique identifier of the task", example = "1")
    private Long id;

    @Schema(description = "Task title", example = "Complete the project")
    private String title;

    @Schema(description = "Task description", example = "Need to complete the project by the end of the week")
    private String description;

    @Schema(description = "Task status", example = "IN_WAITING")
    private String status;

    @Schema(description = "Task priority", example = "HIGH")
    private String priority;

    @Schema(description = "Email of the task author", example = "author@mail.ru")
    private String authorEmail;

    @Schema(description = "Email of the task assignee", example = "assignee@mail.ru")
    private String assigneeEmail;

    @Schema(description = "List of comments for the task")
    private List<CommentDto> comments;

    public TodoResponseDto() {}

    public static TodoResponseDto toModel(TodoEntity todoEntity) {
        TodoResponseDto todo = new TodoResponseDto();
        todo.setId(todoEntity.getId());
        todo.setTitle(todoEntity.getTitle());
        todo.setDescription(todoEntity.getDescription());
        todo.setStatus(todoEntity.getStatus().getName());
        todo.setPriority(todoEntity.getPriority().getName());
        todo.setAuthorEmail(todoEntity.getAuthor().getEmail());
        todo.setAssigneeEmail(todoEntity.getAssignee().getEmail());
        todo.setComments(todoEntity.getComments().stream().map(CommentDto::toModel).toList());

        return todo;
    }
}
