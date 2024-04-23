package com.example.tutorials.authentification.repository;

import com.example.tutorials.authentification.entity.Authorization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizationRepository extends JpaRepository<Authorization, Integer> {
}
