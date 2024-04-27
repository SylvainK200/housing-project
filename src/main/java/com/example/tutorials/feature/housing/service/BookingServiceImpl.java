package com.example.tutorials.feature.housing.service;

import com.example.tutorials.feature.housing.entity.Booking;
import com.example.tutorials.feature.housing.repository.BookingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingServiceImpl implements BookingService{

    private final BookingRepository bookingRepository;

    @Override
    public Booking findById(Integer id) {
        return bookingRepository.findById(id).orElse(null) ;
    }

    @Override
    public Booking create(Booking booking) {
        return bookingRepository.save(booking);
    }


    @Override
    public Booking update(Booking user, Integer id) {
        if(Objects.nonNull(this.findById(id))){
            return bookingRepository.save(user);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        if(Objects.nonNull(this.findById(id))){
            bookingRepository.deleteById(id);
        }
    }

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

}
