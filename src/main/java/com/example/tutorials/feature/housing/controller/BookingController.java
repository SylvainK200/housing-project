package com.example.tutorials.feature.housing.controller;


import com.example.tutorials.feature.housing.entity.Booking;
import com.example.tutorials.feature.housing.service.BookingService;
import com.example.tutorials.util.entity.response.ListObjectResponse;
import com.example.tutorials.util.entity.response.OperationResponse;
import com.example.tutorials.util.entity.response.SimpleObjectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.tutorials.util.entity.response.OperationResponse.ResponseStatusEnum.SUCCESS;

@RequiredArgsConstructor
@RestController
@RequestMapping("app")
public class BookingController {
    private final BookingService bookingService;


    @GetMapping("/bookings")
    public ResponseEntity<ListObjectResponse<Booking>> findAll(){
        return ResponseEntity.ok(new ListObjectResponse<>(SUCCESS, bookingService.findAll()));
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<SimpleObjectResponse<Booking>> findById(@PathVariable Integer id ){
        SimpleObjectResponse<Booking> appUser = new SimpleObjectResponse<>(SUCCESS, bookingService.findById(id));
        return ResponseEntity.ok(appUser);
    }

    @PutMapping("/bookings/{id}")
    public ResponseEntity<SimpleObjectResponse<Booking>> update (@PathVariable Integer id , @RequestBody Booking appUserUpdate){
        SimpleObjectResponse<Booking> appUser = new SimpleObjectResponse<>(SUCCESS, bookingService.update(appUserUpdate, id));
        return ResponseEntity.ok(appUser);
    }

    @PostMapping("/bookings")
    public ResponseEntity<SimpleObjectResponse<Booking>> create(@RequestBody Booking appUser){
        SimpleObjectResponse<Booking> createdUser = new SimpleObjectResponse<>(SUCCESS, bookingService.create(appUser));
        return ResponseEntity.ok(createdUser);
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<OperationResponse> delete(@PathVariable Integer id){
        bookingService.delete(id);
        return ResponseEntity.ok(new OperationResponse(SUCCESS, "deleted"));
    }
}
