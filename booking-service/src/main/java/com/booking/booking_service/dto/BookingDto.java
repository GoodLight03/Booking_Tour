package com.booking.booking_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    private Long id;
    private Date bookingdate;
    private Boolean payment;
    private Integer slot;
    private Long idtour;
    private Long iduser;
}
