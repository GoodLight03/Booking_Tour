package com.booking.tour_service.repository;

import com.booking.tour_service.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour,Long> {
    @Query("select u from Tour u where u.idlocation =?1")
    List<Tour> findAllByLocationId(Long id);
}
