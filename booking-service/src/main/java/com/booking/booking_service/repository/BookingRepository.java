package com.booking.booking_service.repository;

import com.booking.booking_service.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {

    @Query("select u from Booking u where u.iduser =?1")
    List<Booking> findAllByUserId(Long id);
}
