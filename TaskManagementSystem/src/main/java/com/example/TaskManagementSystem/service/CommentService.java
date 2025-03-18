package com.example.TaskManagementSystem.service;

import com.example.TaskManagementSystem.dtos.CommentDto;
import com.example.TaskManagementSystem.entity.CommentEntity;
import com.example.TaskManagementSystem.entity.TodoEntity;
import com.example.TaskManagementSystem.entity.UserEntity;
import com.example.TaskManagementSystem.exception.AccessDeniedException;
import com.example.TaskManagementSystem.exception.TodoNotFoundException;
import com.example.TaskManagementSystem.repository.CommentRepo;
import com.example.TaskManagementSystem.repository.TodoRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class CommentService {

    private final TodoRepo todoRepo;
    private final CommentRepo commentRepo;
    private final UserService userService;

    public CommentDto addComment(Long todoId, CommentEntity comment) {

        TodoEntity todo = todoRepo.findById(todoId)
                .orElseThrow(() -> new TodoNotFoundException("Task with ID " + todoId + " not found"));

        UserEntity currentUser = userService.getCurrentUser();

        if (!todo.getAssignee().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("You cannot comment on this task");
        }

        comment.setTodo(todo);
        comment.setAuthor(currentUser);
        comment.setCreatedAt(LocalDateTime.now());

        return CommentDto.toModel(commentRepo.save(comment));

    }

}
