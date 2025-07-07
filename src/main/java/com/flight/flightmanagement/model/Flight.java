package com.flight.flightmanagement.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightNumber;
    private String airline;
    private String source;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Constructors
    public Flight() {
    }

    public Flight(String flightNumber, String airline, String source, String destination, String departureTime,
            String arrivalTime) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return String.valueOf(id); // or return id + "";
    }

    @Column(name = "booked_seats")
    private String bookedSeats; // CSV like "A1,A2,B3"

    public String getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(String bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

}
