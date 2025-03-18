package com.example.TaskManagementSystem;

import com.example.TaskManagementSystem.controller.AuthController;
import com.example.TaskManagementSystem.dtos.JwtRequest;
import com.example.TaskManagementSystem.dtos.JwtResponse;
import com.example.TaskManagementSystem.dtos.RegistrationUserDto;
import com.example.TaskManagementSystem.dtos.UserDto;
import com.example.TaskManagementSystem.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void createAuthToken_ShouldReturnJwtResponse() throws Exception {

        JwtRequest jwtRequest = new JwtRequest("test@mail.ru","password");
        JwtResponse jwtResponse = new JwtResponse("test-token");
        when(authService.createAuthToken(any(JwtRequest.class))).thenReturn(jwtResponse);

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(jwtRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("test-token"));
    }

    @Test
    void createNewUser_ShouldReturnUserDto() throws Exception {

        RegistrationUserDto registrationUserDto = new RegistrationUserDto("newuser@example.com", "password", "password");
        UserDto userDto = new UserDto(1L, "newuser@example.com", "password");

        when(authService.createNewUser(any(RegistrationUserDto.class))).thenReturn(userDto);

        mockMvc.perform(post("/auth/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationUserDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.email").value("newuser@example.com"))
                .andExpect(jsonPath("$.password").value("password"));
    }
}
