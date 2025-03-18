package com.example.TaskManagementSystem.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "DTO for user authentication")
public class JwtRequest {

    @Schema(description = "User email used for authentication", example = "user@mail.com")
    private String email;

    @Schema(description = "User password used for authentication", example = "qwerty123")
    private String password;
}
