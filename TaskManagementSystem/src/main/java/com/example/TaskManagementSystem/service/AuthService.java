package com.example.TaskManagementSystem.service;

import com.example.TaskManagementSystem.dtos.JwtRequest;
import com.example.TaskManagementSystem.dtos.JwtResponse;
import com.example.TaskManagementSystem.dtos.RegistrationUserDto;
import com.example.TaskManagementSystem.dtos.UserDto;
import com.example.TaskManagementSystem.entity.UserEntity;
import com.example.TaskManagementSystem.exception.PasswordsNotMatchException;
import com.example.TaskManagementSystem.exception.UserAlreadyExistException;
import com.example.TaskManagementSystem.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public JwtResponse createAuthToken(JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect login or password");
        }

        UserDetails userDetails = userService.loadUserByUsername(authRequest.getEmail());
        String token = jwtTokenUtils.generateToken(userDetails);
        return new JwtResponse(token);
    }

    public UserDto createNewUser(RegistrationUserDto registrationUserDto) {
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            throw new PasswordsNotMatchException("Passwords do not match");
        }

        if (userService.findByEmail(registrationUserDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistException("User with the specified email already exists");
        }

        UserEntity user = userService.createNewUser(registrationUserDto);
        return new UserDto(user.getId(), user.getEmail(), user.getPassword());
    }
}

