package com.booking.booking_service.service.Impl;

import com.booking.booking_service.dto.BookingDto;
import com.booking.booking_service.entity.Booking;
import com.booking.booking_service.mapper.BookingMapper;
import com.booking.booking_service.repository.BookingRepository;
import com.booking.booking_service.service.BookingService;
import com.booking.cart_service.dto.CartEvent;
import com.booking.cart_service.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    @Override
    public void saveAll(CartEvent cartEvent) {
        List<Item> items=cartEvent.getItems();
        BookingDto bookingDto=new BookingDto();
        bookingDto.setBookingdate(new Date());
        bookingDto.setPayment(true);
        bookingDto.setIduser(cartEvent.getIdUser());
        if(cartEvent.getItems().size()>0){
            for (Item i: items){
                bookingDto.setIdtour(i.getId_tour());
                bookingDto.setSlot(i.getNumber());
                add(bookingDto);
            }
        }

    }


}
