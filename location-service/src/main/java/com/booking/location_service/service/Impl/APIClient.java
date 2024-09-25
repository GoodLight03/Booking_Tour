package com.booking.location_service.service.Impl;

import com.booking.location_service.dto.TourDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "TOUR-SERVICE")
public interface APIClient {
    @GetMapping("api/tour/getbylocation/{id}")
    List<TourDto> getTour(@PathVariable("id") Long id);
}
