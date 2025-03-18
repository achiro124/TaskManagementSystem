package com.example.TaskManagementSystem.dtos;

import com.example.TaskManagementSystem.entity.TodoEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Schema(description = "DTO for creating or updating a task")
public class TodoRequestDto {

    @Schema(description = "Unique identifier of the task", example = "1")
    private Long id;

    @NotBlank(message = "Task title cannot be empty")
    @Schema(description = "Task title", example = "Complete the project")
    private String title;

    @NotBlank(message = "Task description cannot be empty")
    @Schema(description = "Task description", example = "Need to complete the project by the end of the week")
    private String description;

    @NotNull(message = "Task status cannot be empty")
    @Schema(description = "Task status identifier", example = "2")
    private Integer statusId;

    @NotNull(message = "Task priority cannot be empty")
    @Schema(description = "Task priority identifier", example = "1")
    private Integer priorityId;

    @NotNull(message = "Task author cannot be empty")
    @Schema(description = "Task author identifier", example = "123")
    private Long authorId;

    @NotNull(message = "Task assignee cannot be empty")
    @Schema(description = "Task assignee identifier", example = "456")
    private Long assigneeId;

    @Schema(description = "List of comments for the task")
    private List<CommentDto> comments;

    public TodoRequestDto() {}

    public static TodoRequestDto toModel(TodoEntity todoEntity) {
        TodoRequestDto todo = new TodoRequestDto();
        todo.setId(todoEntity.getId());
        todo.setTitle(todoEntity.getTitle());
        todo.setDescription(todoEntity.getDescription());
        todo.setStatusId(todoEntity.getStatus().getId());
        todo.setPriorityId(todoEntity.getPriority().getId());
        todo.setAuthorId(todoEntity.getAuthor().getId());
        todo.setAssigneeId(todoEntity.getAssignee().getId());
        todo.setComments(todoEntity.getComments().stream().map(CommentDto::toModel).toList());

        return todo;
    }
}
