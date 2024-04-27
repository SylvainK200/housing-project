package com.example.tutorials.authentification.service;

import com.example.tutorials.authentification.dto.AuthenticationResponse;
import com.example.tutorials.authentification.dto.AuthenticationUserRequest;
import com.example.tutorials.authentification.dto.RegistrationUserRequest;
import jakarta.mail.MessagingException;

public interface AuthenticationService {
    void register(RegistrationUserRequest request) throws MessagingException;

    AuthenticationResponse authenticate(AuthenticationUserRequest authenticationUserRequest);

    void activateAccount(String token) throws MessagingException;
}
