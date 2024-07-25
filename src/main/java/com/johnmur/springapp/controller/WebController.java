package com.johnmur.springapp.controller;

import com.johnmur.springapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WebController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           RedirectAttributes redirectAttributes) {
        if (userService.findByUsername(username) != null) {
            redirectAttributes.addFlashAttribute("message", "Username already exists");
            return "redirect:/register";
        }
        userService.saveUser(username,password);
        redirectAttributes.addFlashAttribute("message", "Registration successful.");
        return "redirect:/login";
    }
    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
