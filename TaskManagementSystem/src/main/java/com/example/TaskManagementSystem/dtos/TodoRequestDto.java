package com.example.TaskManagementSystem.dtos;

import com.example.TaskManagementSystem.entity.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TodoResponseDto {

    private Long id;
    @NotBlank(message = "Название задачи не должно быть пустым")
    private String title;
    @NotBlank(message = "Описание задачи не должно быть пустым")
    private String description;

    @NotNull(message = "Статус задачи не должен быть пустым")
    private Integer statusId;
    @NotNull(message = "Приоритет задачи не должен быть пустым")
    private Integer priorityId;

    @NotNull(message = "Автор задачи не должно быть пустым")
    private Long authorId;
    @NotNull(message = "Ответственный задачи не должен быть пустым")
    private Long assigneeId;
    private List<CommentDto> comments;

    public TodoResponseDto() {}

    public static TodoResponseDto toModel(TodoEntity todoEntity){

        TodoResponseDto todo = new TodoResponseDto();
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
