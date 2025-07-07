package com.flight.flightmanagement.controller;

import com.flight.flightmanagement.model.User;
import com.flight.flightmanagement.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // === SIGN UP ===
    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignup(@ModelAttribute User user, Model model) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            model.addAttribute("error", "Username already taken!");
            return "signup";
        }

        userRepository.save(user); // 🔒 We'll hash password later
        model.addAttribute("success", true);
        return "signup";
    }

    // === SIGN IN ===
    @GetMapping("/signin")
    public String signinForm() {
        return "signin";
    }

    @PostMapping("/signin")
    public String processSignin(@RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        User user = userRepository.findByUsername(username);

        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("error", "Invalid credentials!");
            return "signin";
        }

        // Save user info in session
        session.setAttribute("loggedInUser", user.getUsername());

        return "redirect:/home-dashboard";

    }

    // // === LOGOUT ===
    // @GetMapping("/logout")
    // public String logout(HttpSession session) {
    // session.invalidate();
    // return "redirect:/signin?logout";
    // }
}
