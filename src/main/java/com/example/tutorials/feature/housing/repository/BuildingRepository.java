package com.example.tutorials.feature.housing.repository;

import com.example.tutorials.feature.housing.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Integer> {
}
