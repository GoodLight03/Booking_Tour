package com.booking.location_service.service.Impl;

import com.booking.location_service.dto.APIResposeDto;
import com.booking.location_service.dto.LocationDto;
import com.booking.location_service.dto.TourDto;
import com.booking.location_service.entity.Location;
import com.booking.location_service.mapper.LocationMapper;
import com.booking.location_service.repository.LocationRepository;
import com.booking.location_service.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    private APIClient apiClient;

    @Override
    public List<Location> getAll() {
        return locationRepository.findAll();
    }

    @Override
    public Location add(LocationDto locationDto) {
        Location location= LocationMapper.mapToLocation(locationDto);
        return locationRepository.save(location);
    }

    @Override
    public APIResposeDto getTourById(Long id) {
        Location location=locationRepository.findById(id).get();
        List<TourDto> tourDtos = apiClient.getTour(location.getId());
        LocationDto locationDto = LocationMapper.mapToLocationDto(location);
        APIResposeDto apiResposeDto =new APIResposeDto();
        apiResposeDto.setLocationDto(locationDto);
        apiResposeDto.setTourDto(tourDtos);
        return apiResposeDto;
    }
}
