package com.booking.location_service.service;

import com.booking.location_service.dto.LocationDto;
import com.booking.location_service.entity.Location;

import java.util.List;

public interface LocationService {
    List<Location> getAll();

    Location add(LocationDto locationDto );
}
