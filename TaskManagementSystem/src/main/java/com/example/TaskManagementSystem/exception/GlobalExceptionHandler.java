package com.example.TaskManagementSystem.exception;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.slf4j.Logger;
import org.springframework.web.context.request.WebRequest;

import java.util.Locale;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Обработка AccessDeniedException
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e, WebRequest request) {
        logger.error("AccessDeniedException: {}", e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                "Access Denied",
                e.getMessage(),
                HttpStatus.FORBIDDEN
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    // Обработка PasswordsNotMatchException
    @ExceptionHandler(PasswordsNotMatchException.class)
    public ResponseEntity<ErrorResponse> handlePasswordsNotMatchException(PasswordsNotMatchException e, WebRequest request) {
        logger.error("PasswordsNotMatchException: {}", e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                "Passwords do not match",
                e.getMessage(),
                HttpStatus.BAD_REQUEST
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // Обработка PriorityNotFoundException
    @ExceptionHandler(PriorityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePriorityNotFoundException(PriorityNotFoundException e, WebRequest request) {
        logger.error("PriorityNotFoundException: {}", e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                "Priority not found",
                e.getMessage(),
                HttpStatus.NOT_FOUND
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    // Обработка StatusNotFoundException
    @ExceptionHandler(StatusNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleStatusNotFoundException(StatusNotFoundException e, WebRequest request) {
        logger.error("StatusNotFoundException: {}", e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                "Status not found",
                e.getMessage(),
                HttpStatus.NOT_FOUND
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    // Обработка TodoNotFoundException
    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTodoNotFoundException(TodoNotFoundException e, WebRequest request) {
        logger.error("TodoNotFoundException: {}", e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                "Task not found",
                e.getMessage(),
                HttpStatus.NOT_FOUND
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    // Обработка UserAlreadyExistException
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistException(UserAlreadyExistException e, WebRequest request) {
        logger.error("UserAlreadyExistException: {}", e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                "User already exists",
                e.getMessage(),
                HttpStatus.CONFLICT
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    // Обработка UserNotFoundException
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e, WebRequest request) {
        logger.error("UserNotFoundException: {}", e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                "User not found",
                e.getMessage(),
                HttpStatus.NOT_FOUND
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    // Обработка UsersNotFoundException
    @ExceptionHandler(UsersNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsersNotFoundException(UsersNotFoundException e, WebRequest request) {
        logger.error("UsersNotFoundException: {}", e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                "Users not found",
                e.getMessage(),
                HttpStatus.NOT_FOUND
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    // Обработка общих исключений
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception e, WebRequest request) {
        logger.error("Exception: {}", e.getMessage(), e);
        ErrorResponse errorResponse = new ErrorResponse(
                "Internal server error",
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
