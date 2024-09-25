package com.booking.booking_service.controller;

import com.booking.booking_service.dto.BookingDto;
import com.booking.booking_service.entity.Booking;
import com.booking.booking_service.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
@Slf4j
public class BookingController {
    @Autowired
    private BookingService userService;

    @PostMapping("/save")
    ResponseEntity<Booking> save(@RequestBody BookingDto bookingdto){
        return new ResponseEntity<>(userService.add(bookingdto), HttpStatus.CREATED);
    }

    @GetMapping("/getbyuser/{id}")
    ResponseEntity<List<Booking>> get(@PathVariable Long id){
        List<Booking> bookings = userService.getAllByUser(id);
        try {
            return new ResponseEntity<>(
                    bookings,
                    HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity("Booking Not Found", HttpStatus.NOT_FOUND);
        }
    }
}
