package com.example.TaskManagementSystem.controller;

import com.example.TaskManagementSystem.dtos.JwtRequest;
import com.example.TaskManagementSystem.dtos.JwtResponse;
import com.example.TaskManagementSystem.dtos.RegistrationUserDto;
import com.example.TaskManagementSystem.dtos.UserDto;
import com.example.TaskManagementSystem.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "API for user authentication and registration")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(
            summary = "User authentication",
            description = "Authenticates a user and returns a JWT token"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successful authentication",
            content = @Content(schema = @Schema(implementation = JwtResponse.class))
    )
    @ApiResponse(
            responseCode = "401",
            description = "Invalid credentials"
    )
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        return ResponseEntity.ok(authService.createAuthToken(authRequest));
    }

    @PostMapping("/registration")
    @Operation(
            summary = "Register new user",
            description = "Registers a new user in the system"
    )
    @ApiResponse(
            responseCode = "200",
            description = "User successfully registered",
            content = @Content(schema = @Schema(implementation = UserDto.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Incorrect registration data"
    )
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        return ResponseEntity.ok(authService.createNewUser(registrationUserDto));
    }
}
