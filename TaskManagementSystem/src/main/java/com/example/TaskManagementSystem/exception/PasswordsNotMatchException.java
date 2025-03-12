package com.example.TaskManagementSystem.exception;

public class PasswordsNotMatchException extends RuntimeException {
  public PasswordsNotMatchException(String message) {
    super(message);
  }
}
