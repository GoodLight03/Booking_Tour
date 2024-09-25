package com.booking.booking_service.mapper;


import com.booking.booking_service.dto.BookingDto;
import com.booking.booking_service.entity.Booking;

public class BookingMapper {
    public static Booking maptoBooking(BookingDto bookingDto){
        Booking booking = new Booking(
                bookingDto.getId(),
                bookingDto.getBookingdate(),
                bookingDto.getPayment(),
                bookingDto.getSlot(),
                bookingDto.getIdtour(),
                bookingDto.getIduser()
        );
        return booking;
    }

    public static BookingDto maptoBookingDto(Booking booking){
        BookingDto bookingDto = new BookingDto(
                booking.getId(),
                booking.getBookingdate(),
                booking.getPayment(),
                booking.getSlot(),
                booking.getIdtour(),
                booking.getIduser()
        );
        return bookingDto;
    }
}
