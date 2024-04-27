package com.example.tutorials.authentification.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationUserRequest {
    @NotEmpty(message = "Firstname is mandatory")
    @NotBlank(message = "Firstname is a mandatory")
    private String firstname;

    @NotEmpty(message = "Lastname is mandatory")
    @NotBlank(message = "Lastname is a mandatory")
    private String lastname;

    @NotEmpty(message = "Email is mandatory")
    @NotBlank(message = "Email is a mandatory")
    @Email(message = "Email is not well formatted ")
    private String email;

    @NotEmpty(message = "Password is mandatory")
    @NotBlank(message = "Password is a mandatory")
    @Size(min=8, message = "Password should be 8 character long minimum")
    private String password;


    @NotEmpty(message = "Username is mandatory")
    @NotBlank(message = "Username is a mandatory")
    private String username;
}
