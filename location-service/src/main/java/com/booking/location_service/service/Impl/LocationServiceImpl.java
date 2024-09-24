package com.booking.location_service.service.Impl;

import com.booking.location_service.dto.LocationDto;
import com.booking.location_service.entity.Location;
import com.booking.location_service.mapper.LocationMapper;
import com.booking.location_service.repository.LocationRepository;
import com.booking.location_service.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;
    @Override
    public List<Location> getAll() {
        return locationRepository.findAll();
    }

    @Override
    public Location add(LocationDto locationDto) {
        Location location= LocationMapper.mapToLocation(locationDto);
        return locationRepository.save(location);
    }
}
