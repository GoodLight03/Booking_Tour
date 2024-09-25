package com.booking.booking_service.service.Impl;

import com.booking.booking_service.dto.BookingDto;
import com.booking.booking_service.entity.Booking;
import com.booking.booking_service.mapper.BookingMapper;
import com.booking.booking_service.repository.BookingRepository;
import com.booking.booking_service.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Booking add(BookingDto bookingDto) {
        return bookingRepository.save(BookingMapper.maptoBooking(bookingDto));
    }

    @Override
    public List<Booking> getAllByUser(Long id) {
        return bookingRepository.findAllByUserId(id);
    }


}
