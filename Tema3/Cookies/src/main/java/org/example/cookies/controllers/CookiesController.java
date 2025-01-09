package org.example.cookies.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.cookies.entities.Counter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CookiesController {


    @GetMapping("/")
    public String handleRequest(HttpSession session, Model model){
        Counter counter = (Counter) session.getAttribute("counter");
        if (counter == null){
            counter = new Counter();
        }
        counter.increment();
        session.setAttribute("counter", counter);

        model.addAttribute("count", counter.getCount());
        return "counter";
    }

}
