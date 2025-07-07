package com.flight.flightmanagement.controller;

import com.flight.flightmanagement.model.Booking;
import com.flight.flightmanagement.model.Flight;
import com.flight.flightmanagement.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    private final BookingRepository bookingRepository;

    @Autowired
    public PaymentController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    /**
     * Redirects user to payment page with flight and passenger details
     */
    @PostMapping("/payment/book")
    public String redirectToPayment(
            @RequestParam("flightId") String flightId,
            @RequestParam("passengerName") String passengerName,
            @RequestParam("seatClass") String seatClass,
            Model model) {
        // Add booking details to model for confirmation
        model.addAttribute("flightId", flightId);
        model.addAttribute("passengerName", passengerName);
        model.addAttribute("seatClass", seatClass);

        return "payment"; // payment.html template
    }

    /**
     * Processes the payment and saves booking
     */
    @PostMapping("/process")
    public String processPayment(
            @RequestParam("flightId") Flight flightId,
            @RequestParam("passengerName") String passengerName,
            @RequestParam("seatClass") String seatClass) {
        try {
            Booking booking = new Booking();
            booking.setFlight(flightId);
            booking.setPassengerName(passengerName);
            booking.setSeatClass(seatClass);
            booking.setStatus("PAID");

            bookingRepository.save(booking);

            return "redirect:/booking-history?paymentSuccess";
        } catch (Exception e) {
            // Log the error (ideally with a logger)
            e.printStackTrace();
            return "redirect:/error?paymentFailed";
        }
    }
}
