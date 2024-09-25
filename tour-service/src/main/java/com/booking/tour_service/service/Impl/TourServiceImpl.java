package com.booking.tour_service.service.Impl;

import com.booking.tour_service.dto.TourDto;
import com.booking.tour_service.entity.Tour;
import com.booking.tour_service.mapper.TourMapper;
import com.booking.tour_service.repository.TourRepository;
import com.booking.tour_service.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourServiceImpl implements TourService {
    @Autowired
    private TourRepository tourRepository;

    @Override
    public Tour add(TourDto tourDto) {
        return tourRepository.save(TourMapper.maptotour(tourDto));
    }

    @Override
    public List<Tour> getAllByLocation(Long id) {
        return tourRepository.findAllByLocationId(id);
    }

    @Override
    public Tour findById(Long id) {
        return tourRepository.findById(id).get();
    }


}
