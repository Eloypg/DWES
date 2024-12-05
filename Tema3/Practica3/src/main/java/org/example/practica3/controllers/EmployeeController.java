package org.example.practica3.controllers;

import org.example.practica3.entities.Employee;
import org.example.practica3.entities.Team;
import org.example.practica3.services.EmployeeService;
import org.example.practica3.services.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final TeamService teamService;
    //Cambiar el team por las task, no tiene mas sentido que se le creen las tareas que un equipo?

    public EmployeeController(EmployeeService employeeService, TeamService teamService) {
        this.employeeService = employeeService;
        this.teamService = teamService;
    }

    @GetMapping
    public String listEmployees(Model model){
        model.addAttribute("employees", employeeService.findAll());
        return "employees/list";
    }

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("employee", new Employee());
        model.addAttribute("teams", teamService.findAll());
        return "employees/form";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute Employee employee){
        Team team = teamService.findById(employee.getTeam().getId());
        employee.setTeam(team);
        employeeService.save(employee);
        return "redirect:/employees";
    }
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model){
        model.addAttribute("employee", employeeService.findById(id));
        model.addAttribute("teams", teamService.findAll());
        return "employees/form";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        employeeService.deleteById(id);
        return "redirect:/employees";
    }

}
