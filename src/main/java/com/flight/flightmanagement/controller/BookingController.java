package com.flight.flightmanagement.controller;

import com.flight.flightmanagement.model.Booking;
import com.flight.flightmanagement.model.Flight;
import com.flight.flightmanagement.model.Passenger;
import com.flight.flightmanagement.repository.BookingRepository;
import com.flight.flightmanagement.repository.FlightRepository;
import com.flight.flightmanagement.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private FlightRepository flightRepository;

    @GetMapping("/add-booking")
    public String showAddBookingForm(Model model) {
        model.addAttribute("booking", new Booking());
        model.addAttribute("passengers", passengerRepository.findAll());
        model.addAttribute("flights", flightRepository.findAll());
        return "add-booking";
    }

    @PostMapping("/add-booking")
    public String saveBooking(@ModelAttribute Booking booking) {
        booking.setStatus("Confirmed");

        // Dummy payment simulation (80% chance Paid, 20% Failed)
        if (Math.random() < 0.8) {
            booking.setPaymentStatus("Paid");
        } else {
            booking.setPaymentStatus("Failed");
        }

        bookingRepository.save(booking);
        return "redirect:/add-booking?success";
    }

    @GetMapping("/view-bookings")
    public String viewAllBookings(Model model) {
        model.addAttribute("bookings", bookingRepository.findAll());
        return "view-bookings";
    }

    @GetMapping("/booking-history")
    public String showBookingHistory(Model model) {
        model.addAttribute("bookings", bookingRepository.findAll()); // Optional: filter by user later
        return "booking-history";
    }

}
