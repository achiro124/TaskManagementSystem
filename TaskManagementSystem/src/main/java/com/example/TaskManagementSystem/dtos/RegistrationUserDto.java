package com.example.TaskManagementSystem.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "DTO for registering a new user")
public class RegistrationUserDto {

    @Schema(
            description = "User email used for registration",
            example = "user@mail.com"
    )
    private String email;

    @Schema(
            description = "User password used for registration",
            example = "qwerty123"
    )
    private String password;

    @Schema(
            description = "User password confirmation",
            example = "qwerty123"
    )
    private String confirmPassword;
}
