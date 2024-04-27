package com.example.tutorials.authentification.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationUserRequest {

    @NotEmpty(message = "Email is mandatory")
    @NotBlank(message = "Email is a mandatory")
    private String username;

    @NotEmpty(message = "Password is mandatory")
    @NotBlank(message = "Password is a mandatory")
    private String password;
}