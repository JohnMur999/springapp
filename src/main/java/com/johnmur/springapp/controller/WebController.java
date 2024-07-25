package com.johnmur.springapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WebController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String name,
                           @RequestParam String password,
                           RedirectAttributes redirectAttributes) {
        if (userService.findByUsername(username) != null) {
            redirectAttributes.addFlashAttribute("message", "")
        }
        return "redirect:/register";
    }
    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
