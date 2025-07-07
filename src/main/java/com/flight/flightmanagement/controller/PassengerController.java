package com.flight.flightmanagement.controller;

import com.flight.flightmanagement.model.Passenger;
import com.flight.flightmanagement.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PassengerController {

    @Autowired
    private PassengerRepository passengerRepository;

    @GetMapping("/add-passenger")
    public String showAddPassengerForm(Model model) {
        model.addAttribute("passenger", new Passenger());
        return "add-passenger";
    }

    @PostMapping("/add-passenger")
    public String savePassenger(@ModelAttribute Passenger passenger) {
        passengerRepository.save(passenger);
        return "redirect:/add-passenger?success";
    }

    @GetMapping("/view-passengers")
    public String viewPassengers(Model model) {
        model.addAttribute("passengers", passengerRepository.findAll());
        return "view-passengers";
    }

    @GetMapping("/delete-passenger/{id}")
    public String deletePassenger(@PathVariable Long id) {
        passengerRepository.deleteById(id);
        return "redirect:/passengers";
    }

    @GetMapping("/edit-passenger/{id}")
    public String editPassenger(@PathVariable Long id, Model model) {
        Passenger passenger = passengerRepository.findById(id).orElse(null);
        model.addAttribute("passenger", passenger);
        return "edit-passenger";
    }

    @PostMapping("/update-passenger")
    public String updatePassenger(@ModelAttribute Passenger passenger) {
        passengerRepository.save(passenger);
        return "redirect:/passengers";
    }
}
