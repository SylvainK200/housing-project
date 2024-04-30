package com.example.tutorials;

import com.example.tutorials.authentification.entity.AppUser;
import com.example.tutorials.authentification.entity.Authorization;
import com.example.tutorials.authentification.entity.Privilege;
import com.example.tutorials.authentification.entity.Role;
import com.example.tutorials.authentification.repository.AppUserRepository;
import com.example.tutorials.authentification.repository.AuthorizationRepository;
import com.example.tutorials.authentification.repository.PrivilegeRepository;
import com.example.tutorials.authentification.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.GrantedAuthority;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class TutorialsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutorialsApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(AppUserRepository appUserRepository, RoleRepository roleRepository, AuthorizationRepository authorizationRepository, PrivilegeRepository privilegeRepository) {

        return args -> {
            if (roleRepository.findByName("USER").isEmpty()) {
                Privilege privilege = Privilege.builder()
                        .name("SIMPLE_USER")
                        .description("Access to public information")
                        .code("SIMPLE_USER")
                        .enabled(true)
                        .feature("simple utilisateur")
                        .subFeature("public informations")
                        .build();
                privilegeRepository.save(privilege);

                Role role = Role.builder()
                        .name("USER")
                        .enabled(true)
                        .build();
                roleRepository.save(role);
                Authorization authorization = Authorization.builder()
                        .privilege(privilege)
                        .role(role)
                        .enabled(true)
                        .build();

                authorizationRepository.save(authorization);

            }
        };
    }
}
