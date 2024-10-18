package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Student {
    private int id;
    private String name;
    private String surname;
    private int id_house;
    private int courseYear;
    private LocalDate birthDate;

    //private List<Pet> petList;
}
