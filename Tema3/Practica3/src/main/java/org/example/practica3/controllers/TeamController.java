package org.example.practica3.controllers;

import org.example.practica3.entities.Team;
import org.example.practica3.services.EmployeeService;
import org.example.practica3.services.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/team")
public class TeamController {
    private final TeamService teamService;
    private final EmployeeService employeeService;

    public TeamController(TeamService teamService, EmployeeService employeeService) {
        this.teamService = teamService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public String listAuthors(Model model){
        model.addAttribute("teams", teamService.findAll());
        return "authors/list";
    }

    @GetMapping("/create")
    public String createForm(Model model){
        Team team = new Team();
        model.addAttribute("team", team);
        model.addAttribute("employees", employeeService.findAll());
        return "teams/form";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute Team team, BindingResult result){
        if (result.hasErrors()) return "teams/form";
        teamService.save(team);
        return "redirect:/teams";

    }
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model){
        Team team = teamService.findById(id);
        if(team == null) return "redirect:/teams";
        model.addAttribute("team", team);
        model.addAttribute("employees", employeeService.findAll());
        return "teams/form";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        teamService.deleteById(id);
        return "redirect:/teams";
    }
}
