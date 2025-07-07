package com.flight.flightmanagement.controller;

import com.flight.flightmanagement.repository.BookingRepository;
import com.flight.flightmanagement.repository.FlightRepository;
import com.flight.flightmanagement.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        long totalFlights = flightRepository.count();
        long totalPassengers = passengerRepository.count();
        long totalBookings = bookingRepository.count();

        long paidBookings = bookingRepository.countByStatus("Paid");
        long failedBookings = bookingRepository.countByStatus("Failed");

        double totalRevenue = paidBookings * 5000; // ₹5000 per paid booking

        double successRate = totalBookings > 0 ? (paidBookings * 100.0) / totalBookings : 0;

        model.addAttribute("totalFlights", totalFlights);
        model.addAttribute("totalPassengers", totalPassengers);
        model.addAttribute("totalBookings", totalBookings);
        model.addAttribute("paidBookings", paidBookings);
        model.addAttribute("failedBookings", failedBookings);
        model.addAttribute("totalRevenue", totalRevenue);
        model.addAttribute("successRate", successRate);

        return "dashboard";
    }

    // @GetMapping("/book-flight")
    // public String showBookingForm() {
    // return "book-flight"; // loads book-flight.html
    // }

}