package com.flight.flightmanagement;

import com.flight.flightmanagement.model.Booking;
import com.flight.flightmanagement.model.Flight;
import com.flight.flightmanagement.model.Passenger;
import com.flight.flightmanagement.repository.BookingRepository;
import com.flight.flightmanagement.repository.FlightRepository;
import com.flight.flightmanagement.repository.PassengerRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @GetMapping("/")
    public String home() {
        return "welcome";
    }

    @GetMapping("/landing")
    public String showLandingPage() {
        return "landing";
    }

    @GetMapping("/home-dashboard")
    public String dashboard(HttpSession session, Model model) {
        String loggedInUser = (String) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return "redirect:/signin?unauthorized";
        }

        model.addAttribute("username", loggedInUser);

        // Mock data for dashboard
        int totalFlights = 25;
        int totalPassengers = 320;
        int totalBookings = 150;
        int paidBookings = 130;
        int failedBookings = totalBookings - paidBookings;
        double successRate = totalBookings > 0 ? (paidBookings * 100.0) / totalBookings : 0;
        double totalRevenue = 130000.00;

        model.addAttribute("totalFlights", totalFlights);
        model.addAttribute("totalPassengers", totalPassengers);
        model.addAttribute("totalBookings", totalBookings);
        model.addAttribute("paidBookings", paidBookings);
        model.addAttribute("failedBookings", failedBookings);
        model.addAttribute("successRate", successRate);
        model.addAttribute("totalRevenue", totalRevenue);

        return "dashboard";
    }

    @GetMapping("/book-flight")
    public String bookFlightPage(HttpSession session) {
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/signin?unauthorized";
        }
        return "book-flight";
    }

    @PostMapping("/book-flight")
    public String processFlightBooking(
            @RequestParam String flightId,
            @RequestParam String passengerName,
            @RequestParam String seatClass,
            HttpSession session,
            Model model) {

        String loggedInUser = (String) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/signin?unauthorized";
        }

        try {
            Flight flight = flightRepository.findById(Long.parseLong(flightId)).orElse(null);
            if (flight == null) {
                model.addAttribute("error", "Flight not found.");
                return "book-flight";
            }

            Passenger passenger = new Passenger();
            passenger.setName(passengerName);
            passengerRepository.save(passenger);

            Booking booking = new Booking();
            booking.setFlight(flight);
            booking.setPassenger(passenger);
            booking.setSeatClass(seatClass);
            booking.setStatus("Confirmed");

            // Simulate payment
            if (Math.random() < 0.8) {
                booking.setPaymentStatus("Paid");
            } else {
                booking.setPaymentStatus("Failed");
            }

            bookingRepository.save(booking);
            return "redirect:/booking-history";
        } catch (Exception e) {
            model.addAttribute("error", "Booking failed: " + e.getMessage());
            return "book-flight";
        }
    }

    // Flight Management Methods

    @GetMapping("/add-flight")
    public String showAddFlightForm(Model model) {
        model.addAttribute("flight", new Flight());
        return "add-flight";
    }

    @PostMapping("/add-flight")
    public String saveFlight(@ModelAttribute Flight flight) {
        flightRepository.save(flight);
        return "redirect:/add-flight?success";
    }

    @GetMapping("/flights")
    public String showAllFlights(Model model) {
        model.addAttribute("flights", flightRepository.findAll());
        return "view-flights";
    }

    @GetMapping("/delete-flight/{id}")
    public String deleteFlight(@PathVariable Long id) {
        flightRepository.deleteById(id);
        return "redirect:/flights";
    }

    @GetMapping("/edit-flight/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Flight flight = flightRepository.findById(id).orElse(null);
        model.addAttribute("flight", flight);
        return "edit-flight";
    }

    @PostMapping("/update-flight")
    public String updateFlight(@ModelAttribute Flight flight) {
        flightRepository.save(flight);
        return "redirect:/flights";
    }

    @GetMapping("/home-logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/booking-history-alt")
    public String bookingHistoryPage(Model model) {
        model.addAttribute("bookings", bookingRepository.findAll());
        return "booking-history";
    }
}
