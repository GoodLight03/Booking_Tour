package com.booking.tour_service.controller;

import com.booking.tour_service.entity.Tour;
import com.booking.tour_service.dto.TourDto;
import com.booking.tour_service.service.TourService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tour")
@Slf4j
public class TourController {
    @Autowired
    private TourService userService;

    @PostMapping("/save")
    ResponseEntity<Tour> save(@RequestBody TourDto tourDto){
        return new ResponseEntity<>(userService.add(tourDto), HttpStatus.CREATED);
    }

    @GetMapping("/getbylocation/{id}")
    ResponseEntity<List<Tour>> get(@PathVariable Long id){
        List<Tour> tours = userService.getAllByLocation(id);
        try {
            return new ResponseEntity<>(
                    tours,
                    HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity("Tour Not Found", HttpStatus.NOT_FOUND);
        }
    }
}
