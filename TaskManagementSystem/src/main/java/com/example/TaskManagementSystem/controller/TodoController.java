package com.example.TaskManagementSystem.controller;

import com.example.TaskManagementSystem.dtos.CommentDto;
import com.example.TaskManagementSystem.dtos.TodoRequestDto;
import com.example.TaskManagementSystem.dtos.TodoResponseDto;
import com.example.TaskManagementSystem.entity.CommentEntity;
import com.example.TaskManagementSystem.exception.ErrorResponse;
import com.example.TaskManagementSystem.service.CommentService;
import com.example.TaskManagementSystem.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
@Tag(name = "Tasks", description = "API for managing tasks")
public class TodoController {

    private final TodoService todoService;
    private final CommentService commentService;

    @PostMapping
    @Operation(
            summary = "Create task",
            description = "Creates a new task with the specified parameters"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Task created successfully",
            content = @Content(schema = @Schema(implementation = TodoResponseDto.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Invalid data for task creation",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    public ResponseEntity<TodoResponseDto> createTodo(@Valid @RequestBody TodoRequestDto todo) {
        return ResponseEntity.ok(todoService.createTodo(todo));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update task",
            description = "Updates the task with the specified ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Task updated successfully",
            content = @Content(schema = @Schema(implementation = TodoResponseDto.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Task not found",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    public ResponseEntity<TodoResponseDto> updateTodo(
            @Parameter(description = "Task ID", example = "1") @PathVariable Long id,
            @RequestBody TodoRequestDto todoDto) {
        return ResponseEntity.ok(todoService.updateTodo(id, todoDto));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get task by ID",
            description = "Returns the task by its unique identifier"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Task found",
            content = @Content(schema = @Schema(implementation = TodoResponseDto.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Task not found",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    public ResponseEntity<TodoResponseDto> getTodo(
            @Parameter(description = "Task ID", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(todoService.getTodo(id));
    }

    @GetMapping("/author/{authorId}")
    @Operation(
            summary = "Get tasks by author",
            description = "Returns a list of tasks created by the specified author"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Task list retrieved successfully",
            content = @Content(schema = @Schema(implementation = TodoResponseDto.class))
    )
    public ResponseEntity<List<TodoResponseDto>> getTodosByAuthor(
            @Parameter(description = "Author ID", example = "1") @PathVariable Long authorId) {
        return ResponseEntity.ok(todoService.getTodosByAuthor(authorId));
    }

    @GetMapping("/assignee/{assigneeId}")
    @Operation(
            summary = "Get tasks by assignee",
            description = "Returns a list of tasks assigned to the specified assignee"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Task list retrieved successfully",
            content = @Content(schema = @Schema(implementation = TodoResponseDto.class))
    )
    public ResponseEntity<List<TodoResponseDto>> getTodosByAssigneeId(
            @Parameter(description = "Assignee ID", example = "1") @PathVariable Long assigneeId) {
        return ResponseEntity.ok(todoService.getTodosByAssignee(assigneeId));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete task",
            description = "Deletes the task with the specified ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Task deleted successfully"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Task not found",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    public ResponseEntity<String> deleteTodo(
            @Parameter(description = "Task ID", example = "1") @PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.ok("Task deleted");
    }

    @PostMapping("/{id}/comments")
    @Operation(
            summary = "Add comment to task",
            description = "Adds a comment to the task with the specified ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Comment added successfully",
            content = @Content(schema = @Schema(implementation = CommentDto.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Task not found",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    public ResponseEntity<CommentDto> addComment(
            @Parameter(description = "Task ID", example = "1") @PathVariable Long id,
            @RequestBody CommentEntity comment) {
        return ResponseEntity.ok(commentService.addComment(id, comment));
    }

    @PostMapping("/{id}/status")
    @Operation(
            summary = "Change task status",
            description = "Changes the status of the task with the specified ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Task status changed successfully",
            content = @Content(schema = @Schema(implementation = TodoResponseDto.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Task not found",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    public ResponseEntity<TodoResponseDto> changeStatus(
            @Parameter(description = "Task ID", example = "1") @PathVariable Long id,
            @Parameter(description = "New status ID", example = "2") @RequestParam Integer statusId) {
        return ResponseEntity.ok(todoService.changeStatus(id, statusId));
    }
}
