package com.example.TaskManagementSystem.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private String path;
    private HttpStatus status;
}