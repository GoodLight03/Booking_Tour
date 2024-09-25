package com.booking.tour_service.controller;

import com.booking.tour_service.dto.CartDto;
import com.booking.tour_service.dto.TourEvent;
import com.booking.tour_service.entity.Tour;
import com.booking.tour_service.dto.TourDto;
import com.booking.tour_service.kafka.TourProcducer;
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
    private TourService tourService;

    @Autowired
    private TourProcducer tourProcducer;

    @PostMapping("/save")
    ResponseEntity<Tour> save(@RequestBody TourDto tourDto){
        return new ResponseEntity<>(tourService.add(tourDto), HttpStatus.CREATED);
    }

    @GetMapping("/getbylocation/{id}")
    ResponseEntity<List<Tour>> get(@PathVariable Long id){
        List<Tour> tours = tourService.getAllByLocation(id);
        try {
            return new ResponseEntity<>(
                    tours,
                    HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity("Tour Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/orders")
    public String addtocart(@RequestBody CartDto order){

        //order.setOrderId(UUID.randomUUID().toString());

        TourEvent orderEvent = new TourEvent();
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("order status is in pending state");
        orderEvent.setCart(order);
        orderEvent.setTour(tourService.findById(order.getIdtour()));
        tourProcducer.sendMessage(orderEvent);

        return "Order placed successfully ...";
    }

}
