package org.example.login.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/private")
    public String privateZone(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String userrol = authentication.getAuthorities().toString();

        model.addAttribute("username", username);
        model.addAttribute("userrol", userrol);
        return "private";
    }

    @GetMapping("/admin")
    public String adminZone(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String userrol = authentication.getAuthorities().toString();

        model.addAttribute("username", username);
        model.addAttribute("userrol", userrol);
        return "admin";

    }

}
