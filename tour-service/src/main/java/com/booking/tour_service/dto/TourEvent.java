package com.booking.tour_service.dto;

import com.booking.tour_service.entity.Tour;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TourEvent {
    private String message;
    private String status;
    private CartDto cart;
    private Tour tour;
}
