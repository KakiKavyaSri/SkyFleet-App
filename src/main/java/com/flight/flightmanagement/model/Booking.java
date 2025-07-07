package com.flight.flightmanagement.model;

import jakarta.persistence.*;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relationships
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = true)
    private Passenger passenger;

    // Fields
    private String passengerName; // Optional if you’re saving names directly
    private String seatClass;
    private String status; // e.g., Confirmed, Cancelled, etc.
    private String paymentStatus; // e.g., Paid, Failed

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
