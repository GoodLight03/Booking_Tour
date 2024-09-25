package com.booking.tour_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourDto {
    private Long id;
    private String name;
    private Long price;
    private String Schedule;
    private Integer slot;
    private Date startdate;
    private Long idlocation;
}
