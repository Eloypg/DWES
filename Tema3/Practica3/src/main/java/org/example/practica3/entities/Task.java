package org.example.practica3.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "tasks")
public class Task {
    /*enum Type {
        Mejora, Bug, Refactorización
    }
    enum Estado {
        Abierta, En_Progreso, Cerrada
    }*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "openingDate", nullable = false)
    private LocalDate openingDate;
    @Column(name = "closingDate", nullable = false)
    private LocalDate closingDate;
    @Column(name = "type", nullable = false)
    private String type; //mejora, bug, refactorizacion
    @Column(name = "state", nullable = false)
    private String state; //abierta, enProceso, cerrada

    //RELACIÓN
    @ManyToMany(mappedBy = "tasks")
    private Set<Employee> employees = new HashSet<>();

    public Task(String name, LocalDate openingDate, LocalDate closingDate, String type, String state) {
        this.name = name;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.type = type;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", openingDate=" + openingDate +
                ", closingDate=" + closingDate +
                ", type='" + type + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
