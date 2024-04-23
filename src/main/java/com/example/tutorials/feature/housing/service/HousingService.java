package com.example.tutorials.feature.housing.service;

import com.example.tutorials.feature.housing.entity.Housing;

import java.util.List;

public interface HousingService {


    Housing findById(Integer id);
    Housing create (Housing housing);
    Housing update ( Housing housing, Integer id );

    void delete(Integer id);
    List<Housing> findAll();
}
