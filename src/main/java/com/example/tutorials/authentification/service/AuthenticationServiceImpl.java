package com.example.tutorials.authentification.service;

import com.example.tutorials.authentification.dto.AuthenticationResponse;
import com.example.tutorials.authentification.dto.AuthenticationUserRequest;
import com.example.tutorials.authentification.dto.RegistrationUserRequest;
import com.example.tutorials.authentification.email.EmailService;
import com.example.tutorials.authentification.email.EmailTemplateName;
import com.example.tutorials.authentification.entity.AppUser;
import com.example.tutorials.authentification.entity.Role;
import com.example.tutorials.authentification.entity.Token;
import com.example.tutorials.authentification.repository.AppUserRepository;
import com.example.tutorials.authentification.repository.RoleRepository;
import com.example.tutorials.authentification.repository.TokenRepository;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor

public class AuthenticationServiceImpl implements AuthenticationService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository appUserRepository;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;


    @Transactional
    @Override
    public void register(RegistrationUserRequest request) throws MessagingException {
        Role userRole = roleRepository.findByName("USER")
                // todo - better exception handling
                .orElseThrow(() -> new IllegalArgumentException("ROLE USER WAS NOT INITIALIZED"));

        var user = AppUser.builder()
                .name(request.getFirstname() + " " + request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .username(request.getUsername())
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();
        appUserRepository.save(user);
        userRole.getUsers().add(user);
        roleRepository.save(userRole);
        sendValidationEmail(user);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationUserRequest authenticationUserRequest) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationUserRequest.getUsername(),
                        authenticationUserRequest.getPassword()
                )
        );
        var claims = new HashMap<String, Object>();
        var user = ((AppUser) auth.getPrincipal());

        claims.put("fullName", user.getName());
        var jwtToken = jwtService.generateToken(claims, user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public void activateAccount(String token) throws MessagingException {
        Token savedToken = tokenRepository.findByToken(token)
                //Exception has to be defined
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        if (Instant.now().isAfter(savedToken.getExpiresAt())) {
            sendValidationEmail(savedToken.getUser());
            throw new RuntimeException("Activation token has expired. A new token has been sent to your email address");
        }

        var user = appUserRepository.findById(savedToken.getUser().getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setEnabled(true);
        appUserRepository.save(user);
        savedToken.setValidatedAt(Instant.now());
        tokenRepository.save(savedToken);
    }

    private void sendValidationEmail(AppUser user) throws MessagingException {

        var newToken = generateAndSaveActivationToken(user);
        // send email
        emailService.sendEmail(
                user.getEmail(),
                user.getName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                newToken,
                "Account activation"

        );


    }

    private String generateAndSaveActivationToken(AppUser user) {
        // generate a token
        String generatedToken = generateActivationCode(6);
        var token = Token.builder()
                .token(generatedToken)
                .createdAt(Instant.now())
                .expiresAt(Instant.now().plus(15, ChronoUnit.MINUTES))
                .user(user)
                .build();

        tokenRepository.save(token);
        return token.getToken();

    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));

        }
        return codeBuilder.toString();
    }
}
