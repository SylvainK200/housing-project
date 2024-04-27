package com.example.tutorials.authentification.service;

import com.example.tutorials.authentification.entity.AppUser;
import com.example.tutorials.authentification.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService{

    private final AppUserRepository appUserRepository;
    @Override
    public AppUser findById(Integer id) {
        return appUserRepository.findById(id).orElse(null);
    }

    @Override
    public AppUser create(AppUser user) {
        return appUserRepository.save(user);
    }

    @Override
    public AppUser update(AppUser user, Integer id) {
        if(Objects.nonNull(this.findById(id))){
            return appUserRepository.save(user);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        if(Objects.nonNull(this.findById(id))){
            appUserRepository.deleteById(id);
        }
    }

    @Override
    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }


}
