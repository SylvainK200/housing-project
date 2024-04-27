package com.example.tutorials.feature.housing.service;

import com.example.tutorials.feature.housing.entity.Building;
import com.example.tutorials.feature.housing.repository.BuildingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class BuildingServiceImpl implements BuildingService{

    private final BuildingRepository buildingRepository;

    @Override
    public Building findById(Integer id) {
        return buildingRepository.findById(id).orElse(null) ;
    }

    @Override
    public Building create(Building building) {
        return buildingRepository.save(building);
    }


    @Override
    public Building update(Building user, Integer id) {
        if(Objects.nonNull(this.findById(id))){
            return buildingRepository.save(user);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        if(Objects.nonNull(this.findById(id))){
            buildingRepository.deleteById(id);
        }
    }

    @Override
    public List<Building> findAll() {
        return buildingRepository.findAll();
    }
}
