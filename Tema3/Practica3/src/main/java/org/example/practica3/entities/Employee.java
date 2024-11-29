package org.example.practica3.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    //RELACIÓN
    @ManyToMany
    @JoinTable(
            name = "employee_task",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private Set<Task> tasks = new HashSet<>();

    public Employee(String name, int age, Team team) {
        this.name = name;
        this.age = age;
        this.team = team;
    }
}
