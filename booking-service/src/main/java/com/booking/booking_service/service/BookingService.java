package com.booking.booking_service.service;

import com.booking.booking_service.dto.BookingDto;
import com.booking.booking_service.entity.Booking;

import java.util.List;

public interface BookingService {
    Booking add(BookingDto bookingDto);
    List<Booking> getAllByUser(Long id);
}
