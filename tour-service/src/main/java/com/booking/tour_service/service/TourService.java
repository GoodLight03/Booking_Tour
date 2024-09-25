package com.booking.tour_service.service;

import com.booking.tour_service.dto.TourDto;
import com.booking.tour_service.entity.Tour;

import java.util.List;

public interface TourService {
    Tour add(TourDto userDto);
    List<Tour> getAllByLocation(Long id);
}
