package com.booking.location_service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
public class APIResposeDto {

    private LocationDto locationDto;
    private List<TourDto> tourDto;

}
