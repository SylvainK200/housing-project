package com.example.tutorials.feature.housing.service;

import com.example.tutorials.authentification.entity.AppUser;
import com.example.tutorials.feature.housing.entity.Booking;

import java.util.List;

public interface BookingService {

    Booking findById(Integer id);
    Booking create (Booking booking);
    Booking update ( Booking booking, Integer id );

    void delete(Integer id);
    List<Booking> findAll();
}
