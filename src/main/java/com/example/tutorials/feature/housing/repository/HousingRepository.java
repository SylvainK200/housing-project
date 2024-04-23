package com.example.tutorials.feature.housing.repository;

import com.example.tutorials.feature.housing.entity.Housing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HousingRepository extends JpaRepository<Housing, Integer> {
}
