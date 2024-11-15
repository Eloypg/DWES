package org.example.harrypotter.controllers;

import org.example.harrypotter.entities.Student;
import org.example.harrypotter.repositories.StudentRepository;
import org.example.harrypotter.services.StudentService;
import org.example.harrypotter.services.StudentServiceImplementation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {
    private StudentService studentService = new StudentServiceImplementation(new StudentRepository());

    /*@GetMapping("/student/{name}")
    public String showStudent(@PathVariable String name, Model model){
        model.addAttribute("student", studentService.getStudentByName(name));
        return "student";
    }*/
    @GetMapping("/student")
    public String ahowStudentDetail(@RequestParam String name, Model model){
        model.addAttribute("student", studentService.getStudentByName(name));
        return "student";
    }
}
