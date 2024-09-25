package com.booking.tour_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private Long id;
    private Long idtour;
    private Long iduser;
    private Integer number;
}
