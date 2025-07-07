package com.flight.flightmanagement.repository;

import com.flight.flightmanagement.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    long countByStatus(String status);
}
