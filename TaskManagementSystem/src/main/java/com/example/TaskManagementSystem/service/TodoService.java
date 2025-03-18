package com.example.TaskManagementSystem.service;

import com.example.TaskManagementSystem.dtos.CommentDto;
import com.example.TaskManagementSystem.dtos.TodoRequestDto;
import com.example.TaskManagementSystem.dtos.TodoResponseDto;
import com.example.TaskManagementSystem.entity.*;
import com.example.TaskManagementSystem.exception.*;
import com.example.TaskManagementSystem.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepo todoRepo;
    private final UserRepo userRepo;
    private final StatusRepo statusRepo;
    private final PriorityRepo priorityRepo;
    private final CommentRepo commentRepo;

    private final UserService userService;

    public TodoResponseDto createTodo(TodoRequestDto todoDto) {

        if(!userService.isAdmin())
            throw new AccessDeniedException("Only administrator can create todos");

        UserEntity author = userRepo.findById(todoDto.getAuthorId())
                .orElseThrow(() -> new UsersNotFoundException("Author with ID " + todoDto.getAuthorId() + " not found"));

        StatusEntity status = statusRepo.findById(todoDto.getStatusId())
                .orElseThrow(() -> new StatusNotFoundException("Status with ID " + todoDto.getStatusId() + " not found"));

        PriorityEntity priority = priorityRepo.findById(todoDto.getPriorityId())
                .orElseThrow(() -> new PriorityNotFoundException("Priority with ID " + todoDto.getPriorityId() + " not found"));

        UserEntity assignee = userRepo.findById(todoDto.getAssigneeId())
                .orElseThrow(() -> new UsersNotFoundException("Assignee with ID " + todoDto.getAssigneeId() + " not found"));

        TodoEntity todoEntity = new TodoEntity();

        todoEntity.setAuthor(author);
        todoEntity.setPriority(priority);
        todoEntity.setDescription(todoDto.getDescription());
        todoEntity.setStatus(status);
        todoEntity.setAssignee(assignee);
        todoEntity.setTitle(todoDto.getTitle());

        List<CommentEntity> commentEntityList = new ArrayList<>();
        for(CommentDto commentDto : todoDto.getComments()){

            CommentEntity commentEntity = new CommentEntity();
            commentEntity.setCreatedAt(LocalDateTime.now());

            UserEntity authorComment = userRepo.findById(commentDto.getAuthorId())
                    .orElseThrow(() -> new UsersNotFoundException("Author with ID " + todoDto.getAuthorId() + " not found"));

            commentEntity.setAuthor(authorComment);
            commentEntity.setText(commentDto.getText());
            commentEntity.setTodo(todoEntity);

            commentEntityList.add(commentEntity);
        }

        todoEntity.setComments(commentEntityList);

        return TodoResponseDto.toModel(todoRepo.save(todoEntity));
    }

    public TodoResponseDto updateTodo(Long id, TodoRequestDto todoDto) {

        if (!userService.isAdmin()) {
            throw new AccessDeniedException("Only administrator can edit todos");
        }

        TodoEntity existingTodo = todoRepo.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo with ID " + id + " not found"));

        if (todoDto.getTitle() != null) {
            existingTodo.setTitle(todoDto.getTitle());
        }
        if (todoDto.getDescription() != null) {
            existingTodo.setDescription(todoDto.getDescription());
        }

        if (todoDto.getStatusId() != null) {
            StatusEntity status = statusRepo.findById(todoDto.getStatusId())
                    .orElseThrow(() -> new StatusNotFoundException("Status with ID " + todoDto.getStatusId() + " not found"));
            existingTodo.setStatus(status);
        }

        if (todoDto.getPriorityId() != null) {
            PriorityEntity priority = priorityRepo.findById(todoDto.getPriorityId())
                    .orElseThrow(() -> new PriorityNotFoundException("Priority with ID " + todoDto.getPriorityId() + " not found"));
            existingTodo.setPriority(priority);
        }

        if (todoDto.getAssigneeId() != null) {
            UserEntity assignee = userRepo.findById(todoDto.getAssigneeId())
                    .orElseThrow(() -> new UsersNotFoundException("Assignee with ID " + todoDto.getAssigneeId() + " not found"));
            existingTodo.setAssignee(assignee);
        } else {
            existingTodo.setAssignee(null);
        }

        TodoEntity updatedTodo = todoRepo.save(existingTodo);

        return TodoResponseDto.toModel(updatedTodo);
    }

    public TodoResponseDto getTodo(Long id){

        TodoEntity todo = todoRepo.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo with ID " + id + " not found"));
        return TodoResponseDto.toModel(todoRepo.save(todo));

    }

    public void deleteTodo(Long id){

        if(!userService.isAdmin())
            throw new AccessDeniedException("Only administrator can delete todos");

        if (!todoRepo.existsById(id)) {
            throw new TodoNotFoundException("Todo with ID " + id + " not found");
        }

        todoRepo.deleteById(id);
    }

    public TodoResponseDto changeStatus(Long todoId, Integer statusId){
        TodoEntity todo = todoRepo.findById(todoId)
                .orElseThrow(() -> new TodoNotFoundException("Todo with ID " + todoId + " not found"));

        StatusEntity status = statusRepo.findById(statusId)
                .orElseThrow(() -> new StatusNotFoundException("Status with ID " + statusId + " not found"));

        todo.setStatus(status);
        return TodoResponseDto.toModel(todo);
    }

    public List<TodoResponseDto>getTodosByAuthor(Long authorId){
        List<TodoEntity> todos = todoRepo.findByAuthorId(authorId);
        return todos.stream()
                .map(TodoResponseDto::toModel)
                .toList();
    }

    public List<TodoResponseDto>getTodosByAssignee(Long assigneeId){
        List<TodoEntity> todos = todoRepo.findByAssigneeId(assigneeId);
        return todos.stream()
                .map(TodoResponseDto::toModel)
                .toList();
    }

}
