package com.booking.tour_service.mapper;

import com.booking.tour_service.dto.TourDto;
import com.booking.tour_service.entity.Tour;

public class TourMapper {
    public static Tour maptotour(TourDto tourDto){
        Tour tour=new Tour(
                tourDto.getId(),
                tourDto.getName(),
                tourDto.getPrice(),
                tourDto.getSchedule(),
                tourDto.getSlot(),
                tourDto.getStartdate(),
                tourDto.getIdlocation()
        );
        return tour;
    }

    public static TourDto maptotourDto(Tour tour){
        TourDto tourDto=new TourDto(
                tour.getId(),
                tour.getName(),
                tour.getPrice(),
                tour.getSchedule(),
                tour.getSlot(),
                tour.getStartdate(),
                tour.getIdlocation()
        );
        return tourDto;
    }
}
