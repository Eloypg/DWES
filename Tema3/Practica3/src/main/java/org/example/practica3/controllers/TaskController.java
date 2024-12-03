package org.example.practica3.controllers;

import org.example.practica3.entities.Task;
import org.example.practica3.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String list(Model model){
        model.addAttribute("tasks", taskService.findAll());
        return "tasks/lists";
    }

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("task", new Task());
        return "tasks/form";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute Task task){
        taskService.save(task);
        return "redirect:/tasks";
    }
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model){
        model.addAttribute("task", taskService.findById(id));
        return "tasks/form";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        taskService.deleteById(id);
        return "redirect:/tasks";
    }
}
