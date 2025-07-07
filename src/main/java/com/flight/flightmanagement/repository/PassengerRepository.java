package com.flight.flightmanagement.repository;

import com.flight.flightmanagement.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
