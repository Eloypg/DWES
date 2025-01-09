package org.example.cookies.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.cookies.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("/form")
    public String form(){
        return "form";
    }

    @PostMapping("save-name")
    public String save(String name, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
        }
        user.setName(name);
        session.setAttribute("user", user);
        return "redirect:/show-name";
    }

    @GetMapping("/show-name")
    public String showName(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        if (user != null && user.getName() != null){
            model.addAttribute("name", user.getName());
            return "show-name";
        } else {
            return "redirect:/form";
        }
    }

}
