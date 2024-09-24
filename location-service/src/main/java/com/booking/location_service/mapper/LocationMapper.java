package com.booking.location_service.mapper;

import com.booking.location_service.dto.LocationDto;
import com.booking.location_service.entity.Location;

public class LocationMapper {
    public static LocationDto mapToLocationDto(Location location){
        LocationDto locationDto = new LocationDto(
                location.getId(),
                location.getName()
        );
        return locationDto;
    }

    public static Location mapToLocation(LocationDto locationDto){
        Location location = new Location(
                locationDto.getId(),
                locationDto.getName()
        );
        return location;
    }
}
