package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class House {
    private int id;
    private String name;
    private String founder;
    private String hoseBoss;
    private String ghost;

    //private List<Student> studentList;
}
