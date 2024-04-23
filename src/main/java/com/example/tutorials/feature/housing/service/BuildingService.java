package com.example.tutorials.feature.housing.service;


import com.example.tutorials.feature.housing.entity.Building;

import java.util.List;

public interface BuildingService {


    Building findById(Integer id);
    Building create (Building building);
    Building update ( Building building, Integer id );

    void delete(Integer id);
    List<Building> findAll();
}
