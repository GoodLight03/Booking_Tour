package com.booking.location_service.controller;

import com.booking.location_service.dto.LocationDto;
import com.booking.location_service.entity.Location;
import com.booking.location_service.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @GetMapping("/getall")
    public ResponseEntity<List<Location>> getAll(){
        return new ResponseEntity<>(locationService.getAll(), HttpStatus.OK);
    }
}
