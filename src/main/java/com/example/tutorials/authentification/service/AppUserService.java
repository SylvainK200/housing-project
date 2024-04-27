package com.example.tutorials.authentification.service;

import com.example.tutorials.authentification.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AppUserService {

    AppUser findById(Integer id);
    AppUser create (AppUser user);
    AppUser update ( AppUser user, Integer id );

    void delete(Integer id);
    List<AppUser> findAll();
}
