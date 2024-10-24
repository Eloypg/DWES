package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Pet {
    private int id;
    private String name;
    private String specie;
    private int id_student;

    @Override
    public String toString() {
        return  "ID: " + id +
                "NAME: " + name +
                "ESPÉCIE: " + specie;
    }
}
