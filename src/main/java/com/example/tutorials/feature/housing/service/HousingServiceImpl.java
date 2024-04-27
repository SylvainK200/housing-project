package com.example.tutorials.feature.housing.service;

import com.example.tutorials.feature.housing.entity.Housing;
import com.example.tutorials.feature.housing.repository.HousingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Transactional
@RequiredArgsConstructor
@Service
public class HousingServiceImpl implements HousingService {
    private final HousingRepository housingRepository;

    @Override
    public Housing findById(Integer id) {
        return housingRepository.findById(id).orElse(null) ;
    }

    @Override
    public Housing create(Housing housing) {
        return housingRepository.save(housing);
    }


    @Override
    public Housing update(Housing user, Integer id) {
        if(Objects.nonNull(this.findById(id))){
            return housingRepository.save(user);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        if(Objects.nonNull(this.findById(id))){
            housingRepository.deleteById(id);
        }
    }

    @Override
    public List<Housing> findAll() {
        return housingRepository.findAll();
    }
}
